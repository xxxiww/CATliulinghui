package com.llh.entity;

import java.util.Date;

public class Complain {

    private int id;
    private int userId;
    private String content;
    private String answer;
    private String status;
    private String complainTime ;
    private String answerTime;

    public Complain(int id, int userId, String content, String answer, String status, String complainTime, String answerTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.answer = answer;
        this.status = status;
        this.complainTime = complainTime;
        this.answerTime = answerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplainTime() {
        return complainTime;
    }

    public void setComplainTime(String complainTime) {
        this.complainTime = complainTime;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }
}
