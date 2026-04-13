package com.example.sdl_system_learning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Union")
public class Union {

    private String id;

    private String logo;

    private String UnionName;

    private String ShortName;

    private String HeadquarterCity;

    private String email;

    private Phone phone;

    private String EstablishDate;

    private String WebsiteUrl;

    private Address address;
}
