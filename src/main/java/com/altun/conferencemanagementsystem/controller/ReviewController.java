package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.Review;
import com.altun.conferencemanagementsystem.service.PaperService;
import com.altun.conferencemanagementsystem.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final PaperService paperService;

   // @PreAuthorize("hasAuthority('REFEREE')")
    @PostMapping
    public ResponseEntity<Review> submitReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.save(review));
    }

 //   @PreAuthorize("hasAuthority('REFEREE')")
    @GetMapping("/paper/{paperId}")
    public ResponseEntity<List<Review>> getReviewsForPaper(@PathVariable Long paperId) {
        Paper paper = paperService.findById(paperId).orElseThrow(() -> new EntityNotFoundException("Paper not found"));
        return ResponseEntity.ok(reviewService.findByPaper(paper));
    }
}
