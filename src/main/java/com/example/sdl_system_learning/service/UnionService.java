package com.example.sdl_system_learning.service;

import com.example.sdl_system_learning.dto.*;
import com.example.sdl_system_learning.entity.Location.Address;
import com.example.sdl_system_learning.entity.Location.CountryLocation;
import com.example.sdl_system_learning.entity.Phone.Phone;
import com.example.sdl_system_learning.entity.Union;
import com.example.sdl_system_learning.repository.CountryLocationRepository;
import com.example.sdl_system_learning.repository.UnionRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class UnionService {

    private final PhoneValidationService phoneValidationService;
    private final UnionRepository unionRepository;
    private final CountryLocationRepository countryRepository;


    public UnionService(UnionRepository unionRepository, PhoneValidationService phoneValidationService, CountryLocationRepository countryRepository) {

        this.unionRepository = unionRepository;
        this.phoneValidationService = phoneValidationService;
        this.countryRepository = countryRepository;
    }

    private Phone mapToPhone(PhoneRequest request) {

        if (request == null) return null;

        return Phone.builder().countryCode(request.getCountryIso()).phoneNumber(request.getPhoneNumber()).build();
    }

    private Address mapToAddress(AddressRequest request) {

        if (request == null) return null;

        Address address = new Address();

        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());

        CountryLocation country = countryRepository.findByIso2(request.getCountryIso()).orElseThrow(() -> new RuntimeException("Invalid country"));

        address.setCountryIso(request.getCountryIso());
        address.setStateIso(request.getStateIso());
        address.setCity(request.getCity());
        address.setZipCode(request.getZipCode());

        return address;
    }

    private PhoneResponse mapToPhoneResponse(Phone phone) {

        if (phone == null) return null;

        return PhoneResponse.builder().countryCode(phone.getCountryCode()).phoneNumber(phone.getPhoneNumber()).build();
    }

    private AddressResponse mapToAddressResponse(Address address) {

        if (address == null) return null;

        return AddressResponse.builder().addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2()).countryIso(address.getCountryIso()).stateIso(address.getStateIso()).city(address.getCity()).zipCode(address.getZipCode()).build();
    }


    public UnionResponse createUnion(UnionRequest request, MultipartFile file) throws IOException {

        if (request.getPhone() != null) {
            phoneValidationService.validatePhone(request.getPhone());
        }

        if (request.getEmail() != null && unionRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        LocalDate date = LocalDate.parse(request.getEstablishDate());
        if (date.isAfter(LocalDate.now())) {
            throw new RuntimeException("Established date cannot be in the future.");
        }

        Union union = Union.builder().unionName(request.getUnionName()).shortName(request.getShortName()).headquarterCity(request.getHeadquarterCity()).email(request.getEmail()).websiteUrl(request.getWebsiteUrl()).establishDate(request.getEstablishDate()).phone(mapToPhone(request.getPhone())).address(mapToAddress(request.getAddress())).build();

        union = unionRepository.save(union);

        String logoPath = null;

        if (file != null && !file.isEmpty()) {

            String originalName = file.getOriginalFilename();

            if (originalName == null || !(originalName.endsWith(".jpg") || originalName.endsWith(".jpeg") || originalName.endsWith(".png"))) {

                throw new RuntimeException("Only JPG, JPEG, and PNG formats are allowed.");
            }

            long maxSize = 25 * 1024 * 1024;

            if (file.getSize() > maxSize) {
                throw new RuntimeException("Image must be less than 25 MB.");
            }


            String extension = originalName.substring(originalName.lastIndexOf("."));

            String fileName = "UnionLogo_" + union.getId() + extension;

            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            File dest = new File(uploadDir + fileName);
            file.transferTo(dest);

            logoPath = fileName;
        }

        System.out.println("FILE: " + file);

        union.setLogo(logoPath);

        unionRepository.save(union);

        return UnionResponse.builder().id(union.getId()).unionName(union.getUnionName()).shortName(union.getShortName()).email(union.getEmail()).headquarterCity(union.getHeadquarterCity()).websiteUrl(union.getWebsiteUrl()).establishDate(union.getEstablishDate()).logo(union.getLogo()).phone(mapToPhoneResponse(union.getPhone())).address(mapToAddressResponse(union.getAddress())).build();
    }

    public UnionResponse updateUnion(String id, UnionRequest request, MultipartFile file) throws IOException {

        Union union = unionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UNION_NOT_FOUND"));

        if (request.getPhone() != null) {
            phoneValidationService.validatePhone(request.getPhone());
        }

        LocalDate date = LocalDate.parse(request.getEstablishDate());
        if (date.isAfter(LocalDate.now())) {
            throw new RuntimeException("INVALID_ESTABLISH_DATE");
        }

        union.setUnionName(request.getUnionName());
        union.setShortName(request.getShortName());
        union.setHeadquarterCity(request.getHeadquarterCity());
        union.setEmail(request.getEmail());
        union.setWebsiteUrl(request.getWebsiteUrl());
        union.setEstablishDate(request.getEstablishDate());
        union.setPhone(mapToPhone(request.getPhone()));
        union.setAddress(mapToAddress(request.getAddress()));

        if (file != null && !file.isEmpty()) {

            System.out.println("Uploading file: " + file.getOriginalFilename());

            String originalName = file.getOriginalFilename();
            String lower = originalName.toLowerCase();

            if (!(lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png"))) {
                throw new RuntimeException("Only JPG, JPEG, PNG allowed");
            }

            long maxSize = 25 * 1024 * 1024;
            if (file.getSize() > maxSize) {
                throw new RuntimeException("File must be < 25MB");
            }

            String extension = originalName.substring(originalName.lastIndexOf("."));

            String fileName = "UnionLogo_" + union.getId() + "_" + System.currentTimeMillis() + extension;

            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dest = new File(dir, fileName);
            file.transferTo(dest);

            System.out.println("Saved at: " + dest.getAbsolutePath());

            if (union.getLogo() != null) {
                File oldFile = new File(uploadDir + File.separator + union.getLogo());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }


            union.setLogo(fileName);
        }

        unionRepository.save(union);

        return UnionResponse.builder()
                .id(union.getId())
                .unionName(union.getUnionName())
                .shortName(union.getShortName())
                .email(union.getEmail())
                .headquarterCity(union.getHeadquarterCity())
                .websiteUrl(union.getWebsiteUrl())
                .establishDate(union.getEstablishDate())
                .logo(union.getLogo())
                .phone(mapToPhoneResponse(union.getPhone()))
                .address(mapToAddressResponse(union.getAddress()))
                .build();
    }

    public Page<UnionResponse> getAllUnions(Pageable pageable){

        Page<Union> unionPage = unionRepository.findAll(pageable);

        return unionPage.map(union -> UnionResponse.builder()
                .id(union.getId())
                .unionName(union.getUnionName())
                .shortName(union.getShortName())
                .email(union.getEmail())
                .headquarterCity(union.getHeadquarterCity())
                .websiteUrl(union.getWebsiteUrl())
                .establishDate(union.getEstablishDate())
                .logo(union.getLogo())
                .phone(mapToPhoneResponse(union.getPhone()))
                .address(mapToAddressResponse(union.getAddress()))
                .build()
        );
    }


    public UnionResponse getUnionById(String id) {

        Union union = unionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Union not found"));

        return UnionResponse.builder()
                .id(union.getId())
                .unionName(union.getUnionName())
                .shortName(union.getShortName())
                .email(union.getEmail())
                .headquarterCity(union.getHeadquarterCity())
                .websiteUrl(union.getWebsiteUrl())
                .establishDate(union.getEstablishDate())
                .logo(union.getLogo())
                .phone(mapToPhoneResponse(union.getPhone()))
                .address(mapToAddressResponse(union.getAddress()))
                .build();
    }
}
