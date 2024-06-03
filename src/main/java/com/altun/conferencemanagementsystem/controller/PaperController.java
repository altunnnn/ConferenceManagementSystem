package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.Paper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.altun.conferencemanagementsystem.service.PaperService;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
@RequiredArgsConstructor
public class PaperController {
    private final PaperService paperService;
    @PostMapping
     @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<Paper> submitPaper(@RequestBody Paper paper){
        return ResponseEntity.ok(paperService.save(paper));
    }

    @PreAuthorize("hasAnyAuthority('AUTHOR', 'REFEREE', 'OCC')")
    @GetMapping("/{id}")
    public ResponseEntity<Paper> getPaper(@PathVariable Long id) {
        return paperService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('AUTHOR')")
    @PostMapping("/simulate")
    public ResponseEntity<Paper> simulatePaperSubmission(@RequestBody Paper paper) {
        return ResponseEntity.ok(paperService.simulatePaperSubmission(paper));
    }

    @PreAuthorize("hasAuthority('OCC')")
    @GetMapping("/top/{n}")
    public ResponseEntity<List<Paper>> getTopPapers(@PathVariable int n) {
        return ResponseEntity.ok(paperService.getTopPapers(n));
    }


}
