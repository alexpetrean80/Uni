package com.example.droid.repo;

import com.example.droid.domain.Droid;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DroidRepository extends MongoRepository<Droid, String> {
}
