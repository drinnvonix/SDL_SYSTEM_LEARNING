package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.Union;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnionRepository extends MongoRepository<Union, String> {

    boolean existsByEmail(String email);
}