package com.example.testingsystem.services;

import com.example.testingsystem.models.Exam;
import com.example.testingsystem.models.Question;
import com.example.testingsystem.models.dto.requests.ExamRequest;
import com.example.testingsystem.models.dto.requests.QuestionRequest;
import com.example.testingsystem.models.dto.responses.ExamForPassing;
import com.example.testingsystem.models.dto.responses.ExamInfo;
import com.example.testingsystem.models.dto.responses.QuestionForPassing;
import com.example.testingsystem.repositories.ExamRepository;
import com.example.testingsystem.repositories.QuestionRepository;
import com.example.testingsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository repository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public void save(ExamRequest request) {
        repository.save(convertExamDtoToEntity(request));
    }

    private Exam convertExamDtoToEntity(ExamRequest dto) {
        return Exam.builder()
                .list(dto.getQuestionBlocks().stream().map(this::covertQuestionDtoToEntity).toList())
                .teacher(userRepository.findById(dto.getTeacherId()).orElseThrow())
                .name(dto.getTestName())
                .maxPoint(dto.getMaxPoints())
                .build();
    }
    private Question covertQuestionDtoToEntity(QuestionRequest dto) {
        Question question = Question.builder()
                .question(dto.getQuestion())
                .option1(dto.getAnswers().get(0))
                .option2(dto.getAnswers().get(1))
                .option3(dto.getAnswers().get(2))
                .answer(dto.getCorrectAnswer())
                .point(dto.getPoints())
                .build();
        questionRepository.save(question);
        return question;
    }
    private ExamInfo covertQuestionEntityToResponseDto(Exam entity) {
        return ExamInfo.builder()
                .id(entity.getId())
                .size(entity.getList().size())
                .teacherName(entity.getTeacher().getFirstname() + " " + entity.getTeacher().getLastname())
                .name(entity.getName())
                .maxPoint(entity.getMaxPoint())
                .build();
    }

    public List<ExamInfo> getAllForTeacher(Long teacherId) {
        return repository.findAllByTeacherId(teacherId).stream().map(this::covertQuestionEntityToResponseDto).collect(Collectors.toList());
    }

    public Page<ExamInfo> getAll(int currentPage, int examsPerPage, String search) {
        Page<Exam> page;
        Pageable pageable = PageRequest.of(currentPage, examsPerPage);
        if (search==null || search.trim().isEmpty()){
            page = repository.findAll(pageable);
        } else {
            page = repository.findAllByNameIsContainingIgnoreCase(pageable, search);
        }

        List<ExamInfo> examsInfo = page.getContent().stream()
                .map(this::covertQuestionEntityToResponseDto)
                .collect(Collectors.toList());

        return new PageImpl<>(examsInfo, pageable, page.getTotalElements());
    }

    private List<String> getOptions(Question question) {
        List<String> list = new ArrayList<>();
        list.add(question.getOption1());
        list.add(question.getOption2());
        list.add(question.getOption3());
        list.add(question.getAnswer());

        Collections.shuffle(list);

        return list;
    }
    private QuestionForPassing convertQuestionToDto(Question question) {
        return QuestionForPassing.builder()
                .options(getOptions(question))
                .point(question.getPoint())
                .question(question.getQuestion())
                .build();
    }

    public ExamForPassing getById(Long id) {
        Exam exam = repository.findById(id).orElseThrow();
        return ExamForPassing.builder()
                .id(exam.getId())
                .teacherName(exam.getTeacher().getFirstname() + ' ' + exam.getTeacher().getLastname())
                .name(exam.getName())
                .maxPoint(exam.getMaxPoint())
                .list(exam.getList().stream().map(this::convertQuestionToDto).collect(Collectors.toList()))
                .build();
    }
}
