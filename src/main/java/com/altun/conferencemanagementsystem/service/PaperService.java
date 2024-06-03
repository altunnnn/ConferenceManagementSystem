package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.altun.conferencemanagementsystem.repository.PaperRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperService {
    private final PaperRepository paperRepository;
    private final OCCService occService;

    @Transactional
    public Paper save(Paper paper){
        List<User> matchedReferees = occService.suggestReferees(paper);
        paper.setReviewers(new HashSet<>(matchedReferees));
        return paperRepository.save(paper);
    }

    public Optional<Paper> findById(Long id) {
        return paperRepository.findById(id);
    }


}
