package com.altun.conferencemanagementsystem.service.impl;

import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.entity.User;
import com.altun.conferencemanagementsystem.repository.RefereeExpertiseRepository;
import com.altun.conferencemanagementsystem.repository.UserRepository;
import com.altun.conferencemanagementsystem.service.RefereeExpertiseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefereeExpertiseServiceImpl implements RefereeExpertiseService {
    private final RefereeExpertiseRepository refereeExpertiseRepository;
    private final UserRepository userRepository;
    @Override
    public RefereeExpertise addExpertise(String username, String expertiseArea) {
        Optional<User> userOp = userRepository.findByUsername(username);

        if (!userOp.isPresent()){
            throw new EntityNotFoundException("User not found");
        }

        User user = userOp.get();

        RefereeExpertise expertise = new RefereeExpertise();
        expertise.setExpertiseArea(expertiseArea);
        expertise.setReferee(user);
        return refereeExpertiseRepository.save(expertise);
    }
    @Override
    public List<RefereeExpertise> getExpertiseByReferee(String username) {
        Optional<User> userOp = userRepository.findByUsername(username);

        if (!userOp.isPresent()){
            throw new EntityNotFoundException("User not found");
        }
        User user = userOp.get();
        return refereeExpertiseRepository.findByReferee(user);
    }
}
