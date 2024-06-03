package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.Paper;
import com.altun.conferencemanagementsystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface OCCService {
    List<User> suggestReferees(Paper paper);

    Paper assignReferees(Long paperId, List<Long> refereeIds);

    Optional<Paper> findPaperById(Long paperId);
}
