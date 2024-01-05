package com.MongMoong.MongBitProject.repository;

import com.MongMoong.MongBitProject.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}