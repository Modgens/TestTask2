package com.example.testingsystem.models.dto.requests;

import com.example.testingsystem.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class TableRequest {
    @Id
    private Long id;

    private Date entryDate;
    private Integer itemCode;
    private String itemName;
    private Integer itemQuantity;
    private Status status;

}
