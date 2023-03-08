package com.letsdobro.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum AssignmentEnum {
    ASSIGNMENT_1(1,"Html Assignment"),
    ASSIGNMENT_2(2, "Guessing Game"),
    ASSIGNMENT_3(3,"Unit Testing"),
    ASSIGNMENT_4(4,"Build Stack with Thymeleaf"),
    ASSIGNMENT_5(5,"Online Bank");

    private int assignmentNum;
    private String  assignmentName;

    AssignmentEnum(int assignmentNum, String assignmentName) {
        this.assignmentNum = assignmentNum;
        this.assignmentName = assignmentName;
    }

}
