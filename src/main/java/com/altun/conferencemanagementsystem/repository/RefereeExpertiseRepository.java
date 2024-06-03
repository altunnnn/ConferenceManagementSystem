package com.altun.conferencemanagementsystem.repository;

import com.altun.conferencemanagementsystem.entity.RefereeExpertise;
import com.altun.conferencemanagementsystem.entity.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeExpertiseRepository extends JpaRepository<RefereeExpertise,Long> {
    List<RefereeExpertise> findByReferee(User referee);
}
