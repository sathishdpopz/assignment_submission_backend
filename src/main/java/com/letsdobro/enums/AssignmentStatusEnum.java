package com.letsdobro.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum AssignmentStatusEnum {
    PENDING_SUBMISSION("Pending Submission", 1),
    SUBMITTED("Submitted", 2),
    IN_REVIEW("In Review", 3),
    NEEDS_UPDATE("Needs Update", 4),
    COMPLETED("Completed", 5),
    RESUBMITTED("Resubmitted", 6);

    private String status;
    private Integer step;

    AssignmentStatusEnum(String status, Integer step) {
        this.status = status;
        this.step = step;
    }
}
