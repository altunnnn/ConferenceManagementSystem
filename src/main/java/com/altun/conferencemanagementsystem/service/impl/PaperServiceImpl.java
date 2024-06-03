package com.altun.conferencemanagementsystem.service.impl;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.entity.Review;
import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.repository.RefereeExpertiseRepository;
import com.altun.conferencemanagementsystem.service.EmailService;
import com.altun.conferencemanagementsystem.service.PaperService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.altun.conferencemanagementsystem.repository.PaperRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {
    private final PaperRepository paperRepository;
    private final RefereeExpertiseRepository refereeExpertiseRepository;
    private final EmailService emailService;
    @Override
    @Transactional
    public Paper save(Paper paper){
        List<User> matchedReferees = matchReferees(paper);
        paper.setReviewers(new HashSet<>(matchedReferees));
        return paperRepository.save(paper);
    }
    @Override
    public Paper simulatePaperSubmission(Paper paper) {
        Paper savedPaper = save(paper);
        simulateRefereeEvaluations(savedPaper.getId());
        return savedPaper;
    }


    private List<User> matchReferees(Paper paper) {
        List<RefereeExpertise> expertises = refereeExpertiseRepository.findAll();
        return expertises.stream()
                .filter(expertise -> expertise.getExpertiseArea().equalsIgnoreCase(paper.getTopic()))
                .map(RefereeExpertise::getReferee)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public void simulateRefereeEvaluations(Long paperId)  {
        Paper paper = paperRepository.findById(paperId).orElseThrow(() -> new EntityNotFoundException("Paper not found"));
        Random random = new Random();
        StringBuilder evaluations = new StringBuilder("Evaluation Results:\n");
        for (User referee : paper.getReviewers()) {
            int score = random.nextInt(5) + 1;
            Review review = new Review();
            review.setPaper(paper);
            review.setReviewer(referee);
            review.setScore(score);
            evaluations.append(String.format("Referee: %s, Score: %d\n", referee.getUsername(), score));
        }
        emailService.sendEvaluationResults(paper.getAuthor().getEmail(), "Paper Evaluation Results", evaluations.toString());
    }
    @Override
    public List<Paper> getTopPapers(int n) {
        List<Paper> allPapers = paperRepository.findAll();
        return allPapers.stream()
                .sorted((p1, p2) -> Double.compare(p2.getAverageScore(), p1.getAverageScore()))
                .limit(n)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<Paper> findById(Long id) {
        return paperRepository.findById(id);
    }


}
