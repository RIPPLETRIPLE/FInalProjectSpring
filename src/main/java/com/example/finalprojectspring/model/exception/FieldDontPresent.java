package com.example.finalprojectspring.model.exception;


public class FieldDontPresent extends Exception {
    @Override
    public String getMessage() {
        return "Field don`t present";
    }
}
