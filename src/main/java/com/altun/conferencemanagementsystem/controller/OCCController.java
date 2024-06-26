package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.service.OCCService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/occ")
@RequiredArgsConstructor
public class OCCController {
    private final OCCService occService;


    @GetMapping("/papers/{paperId}/suggested-referees")
    @PreAuthorize("hasAuthority('OCC')")
    public ResponseEntity<List<User>> getSuggestedReferees(@PathVariable Long paperId) {
        Optional<Paper> optionalPaper = occService.findPaperById(paperId);
        if (!optionalPaper.isPresent()){
            throw new EntityNotFoundException("Paper not found with id: " + paperId);
        }
        Paper paper = optionalPaper.get();
        List<User> referees = occService.suggestReferees(paper);
        return ResponseEntity.ok(referees);
    }

    @PostMapping("/papers/{paperId}/assign-referees")
    @PreAuthorize("hasAuthority('OCC')")
    public ResponseEntity<Paper> assignReferees(@PathVariable Long paperId, @RequestBody List<Long> refereeIds) {
        Paper paper = occService.assignReferees(paperId, refereeIds);
        return ResponseEntity.ok(paper);
    }
}
