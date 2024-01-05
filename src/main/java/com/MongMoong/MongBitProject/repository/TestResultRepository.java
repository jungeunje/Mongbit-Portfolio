package com.MongMoong.MongBitProject.repository;

import com.MongMoong.MongBitProject.model.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestResultRepository extends MongoRepository<TestResult, String> {

}
