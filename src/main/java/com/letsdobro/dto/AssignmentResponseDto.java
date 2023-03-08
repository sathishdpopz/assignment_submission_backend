package com.letsdobro.dto;

import com.letsdobro.enums.AssignmentEnum;
import com.letsdobro.enums.AssignmentStatusEnum;
import com.letsdobro.model.Assignment;

public class AssignmentResponseDto {
    private Assignment assignment;
    private final AssignmentEnum[] assignmentEnums = AssignmentEnum.values();
    private final AssignmentStatusEnum[] statusEnums = AssignmentStatusEnum.values();
    public AssignmentResponseDto(Assignment assignment) {
        this.assignment = assignment;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public AssignmentEnum[] getAssignmentEnum() {
        return assignmentEnums;
    }

    public AssignmentStatusEnum[] getStatusEnum() {
        return statusEnums;
    }
}
