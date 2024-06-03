package com.altun.conferencemanagementsystem.controller;

import com.altun.conferencemanagementsystem.entity.Paper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.altun.conferencemanagementsystem.service.PaperService;

@RestController
@RequestMapping("/api/papers")
@RequiredArgsConstructor
public class PaperController {
    private final PaperService paperService;
    @PostMapping
    // TODO: @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<Paper> submitPaper(@RequestBody Paper paper){
        System.out.println("asdad");
        return ResponseEntity.ok(paperService.save(paper));
    }

    //TODO: @PreAuthorize("hasAnyAuthority('AUTHOR', 'REFEREE', 'OCC')")
    @GetMapping("/{id}")
    public ResponseEntity<Paper> getPaper(@PathVariable Long id) {
        return paperService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
