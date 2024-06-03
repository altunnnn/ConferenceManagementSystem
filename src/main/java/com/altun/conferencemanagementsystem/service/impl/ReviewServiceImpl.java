package com.altun.conferencemanagementsystem.service.impl;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.Review;
import com.altun.conferencemanagementsystem.repository.ReviewRepository;
import com.altun.conferencemanagementsystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
    @Override
    public List<Review> findByPaper(Paper paper) {
        return reviewRepository.findByPaper(paper);
    }
}
