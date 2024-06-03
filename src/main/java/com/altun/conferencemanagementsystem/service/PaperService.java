package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Paper;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface PaperService {
    @Transactional
    Paper save(Paper paper);

    Paper simulatePaperSubmission(Paper paper);

    void simulateRefereeEvaluations(Long paperId);

    List<Paper> getTopPapers(int n);

    Optional<Paper> findById(Long id);
}
