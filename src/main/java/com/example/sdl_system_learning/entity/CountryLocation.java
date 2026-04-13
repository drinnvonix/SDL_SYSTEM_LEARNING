package com.example.sdl_system_learning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CountryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iso2;

    @OneToMany(mappedBy = "country")
    private List<State> states;
}