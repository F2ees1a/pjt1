package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty
    private String userName;

    @JsonIgnore
    private String password;

    @ElementCollection
    private Map<String, Double> typeWeights;

    @ElementCollection
    private Map<String, Double> typeScores;

    @ElementCollection
    private List<Integer> similarUsers;

    private Integer totalBrowses;
}
