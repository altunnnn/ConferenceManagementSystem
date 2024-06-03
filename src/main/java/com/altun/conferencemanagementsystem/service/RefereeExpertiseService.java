package com.altun.conferencemanagementsystem.service;

import com.altun.conferencemanagementsystem.entity.RefereeExpertise;

import java.util.List;

public interface RefereeExpertiseService {
    RefereeExpertise addExpertise(String username, String expertiseArea);

    List<RefereeExpertise> getExpertiseByReferee(String username);
}
