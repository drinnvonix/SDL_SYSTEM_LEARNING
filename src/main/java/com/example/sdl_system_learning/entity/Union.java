package com.example.sdl_system_learning.entity;

import com.example.sdl_system_learning.entity.Location.Address;
import com.example.sdl_system_learning.entity.Phone.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String unionName;

    private String shortName;

    @NotNull
    private String headquarterCity;

    @Email
    @UniqueElements
    private String email;

    @UniqueElements
    private Phone phone;

    @NotNull
    private String establishDate;

    @UniqueElements
    private String websiteUrl;

    private Address address;
}