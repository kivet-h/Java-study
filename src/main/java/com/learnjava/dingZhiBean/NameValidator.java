package com.learnjava.dingZhiBean;

import org.springframework.stereotype.Component;

@Component
public class NameValidator implements Validator {
    public void validate(String email, String password, String name) {
        // if (name == null || name.isBlank() || name.length() > 20) {

        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("invalid name: " + name);
        }
    }
}