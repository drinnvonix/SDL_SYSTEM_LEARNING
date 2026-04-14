package com.example.sdl_system_learning.dto;

import com.example.sdl_system_learning.entity.Location.Address;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class UnionRequest {

    @NotBlank(message = "Union Name is required.")
    @Size(max = 100, message = "Union Name cannot exceed 100 characters.")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\-\\s]+$",
            message = "Union Name can only include letters, numbers, spaces, and hyphens(-)."
    )
    private String unionName;

    @Size(max = 20, message = "Union Code cannot exceed 20 characters.")
    @Pattern(
            regexp = "^[a-zA-Z0-9]*$",
            message = "Union Code can only include letters and numbers."
    )
    private String shortName;

    @NotBlank(message = "Headquarter City is required.")
    @Size(max = 100, message = "City name cannot exceed 100 characters.")
    @Pattern(
            regexp = "^[a-zA-Z\\s\\-'.]+$",
            message = "City name can only contain letters, spaces, hyphens, apostrophes, and periods(.)."
    )
    private String headquarterCity;

    @Email(message = "Enter a valid email(e.g., user@example.com).")
    @Size(max = 250)
    private String email;

    @Size(max = 250, message = "Website URL cannot exceed 250 characters.")
    @Pattern(
            regexp = "^(https?://)?(www\\.)?.+\\..+$",
            message = "Enter a valid website URL."
    )
    private String websiteUrl;

    @NotNull(message = "Please select the established date.")
    private String establishDate;

    private PhoneRequest phone;

    private AddressRequest address;
}