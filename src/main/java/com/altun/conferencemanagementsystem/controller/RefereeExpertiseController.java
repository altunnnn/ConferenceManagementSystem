package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.service.RefereeExpertiseService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expertise")
@RequiredArgsConstructor
public class RefereeExpertiseController {

    private final RefereeExpertiseService refereeExpertiseService;

    @PreAuthorize("hasAuthority('REFEREE')")
    @PostMapping
    public ResponseEntity<RefereeExpertise> addExpertise(@RequestParam String username, @RequestParam String expertiseArea) {
        return ResponseEntity.ok(refereeExpertiseService.addExpertise(username, expertiseArea));
    }

    @PreAuthorize("hasAuthority('REFEREE')")
    @GetMapping("/{username}")
    public ResponseEntity<List<RefereeExpertise>> getExpertiseByReferee(@PathVariable String username) {
        return ResponseEntity.ok(refereeExpertiseService.getExpertiseByReferee(username));
    }
}
