package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.altun.conferencemanagementsystem.repository.PaperRepository;
import com.altun.conferencemanagementsystem.repository.RefereeExpertiseRepository;
import com.altun.conferencemanagementsystem.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OCCService {
    private final PaperRepository paperRepository;

    private final UserRepository userRepository;

    private final RefereeExpertiseRepository refereeExpertiseRepository;

    public List<User> suggestReferees(Paper paper){
        List<RefereeExpertise> expertises = refereeExpertiseRepository.findAll();
        return expertises.stream()
                .filter(expertise -> expertise.getExpertiseArea().equalsIgnoreCase(paper.getTopic()))
                .map(RefereeExpertise::getReferee)
                .distinct()
                .collect(Collectors.toList());
    }

    public Paper assignReferees(Long paperId, List<Long> refereeIds) {
        Optional<Paper> optionalPaper = paperRepository.findById(paperId);

        if (!optionalPaper.isPresent()) {
            throw new EntityNotFoundException("Paper not found with id " + paperId);
        }

        Paper paper = optionalPaper.get();
        List<User> referees = userRepository.findAllById(refereeIds);
        paper.setReviewers(new HashSet<>(referees));
        return paperRepository.save(paper);
    }

    public Optional<Paper> findPaperById(Long paperId) {
        return paperRepository.findById(paperId);
    }


}
