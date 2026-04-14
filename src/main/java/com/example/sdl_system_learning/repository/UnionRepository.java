package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.Union;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnionRepository extends MongoRepository<Union, String>{

    boolean existsByEmail(String email);

    @NonNull
    Page<Union> findAll(@NonNull Pageable pageable);
}