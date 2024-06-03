package com.altun.conferencemanagementsystem.service;

public interface EmailService {
    void sendEvaluationResults(String to, String subject, String text);
}
