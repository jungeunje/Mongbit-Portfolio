package com.MongMoong.MongBitProject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Question")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String id;
    private int index; // 질문의 순서
    private String question;
    private String answerPlus;
    private String answerMinus;
}