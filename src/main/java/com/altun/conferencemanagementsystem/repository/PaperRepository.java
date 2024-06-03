package com.altun.conferencemanagementsystem.repository;

import com.altun.conferencemanagementsystem.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper,Long> {
}
