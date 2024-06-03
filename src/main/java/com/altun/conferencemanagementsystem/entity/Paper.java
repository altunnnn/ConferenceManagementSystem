package com.altun.conferencemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String abstractText;
    private String keywords;
    private String topic;
    private String authorInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "paper_reviewer",
            joinColumns = @JoinColumn(name = "paper_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> reviewers = new HashSet<>();

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    public double getAverageScore() {
        return reviews.stream()
                .mapToInt(Review::getScore)
                .average()
                .orElse(0);
    }
}
