package com.altun.conferencemanagementsystem.service.impl;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.service.OCCService;
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
public class OCCServiceImpl implements OCCService {
    private final PaperRepository paperRepository;

    private final UserRepository userRepository;

    private final RefereeExpertiseRepository refereeExpertiseRepository;
    @Override
    public List<User> suggestReferees(Paper paper){
        List<RefereeExpertise> expertises = refereeExpertiseRepository.findAll();
        return expertises.stream()
                .filter(expertise -> expertise.getExpertiseArea().equalsIgnoreCase(paper.getTopic()))
                .map(RefereeExpertise::getReferee)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
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
    @Override
    public Optional<Paper> findPaperById(Long paperId) {
        return paperRepository.findById(paperId);
    }


}
