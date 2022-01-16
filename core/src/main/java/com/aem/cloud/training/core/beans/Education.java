package com.aem.cloud.training.core.beans;

import java.util.List;

public class Education {
    private String subject;

    private List<String> courseList;

    public Education(String subject, List<String> courseList) {
        this.subject = subject;
        this.courseList = courseList;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> getCourseList() {
        return courseList;
    }
}
