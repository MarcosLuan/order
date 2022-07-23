package com.order.Enum;

public enum SituationEnum {

    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private String description;

    private SituationEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
