package com.example.sdl_system_learning.entity;

import com.example.sdl_system_learning.entity.Location.Address;
import com.example.sdl_system_learning.entity.Phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "union")
public class Union {

    private String id;

    private String logo;

    private String unionName;

    private String shortName;

    private String headquarterCity;

    private String email;

    @UniqueElements
    private Phone phone;

    private String establishDate;

    @UniqueElements
    private String websiteUrl;

    private Address address;
}