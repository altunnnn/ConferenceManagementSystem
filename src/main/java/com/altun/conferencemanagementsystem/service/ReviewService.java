package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review);

    List<Review> findByPaper(Paper paper);
}
