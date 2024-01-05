package com.MongMoong.MongBitProject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Document("Test")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    private String id;
    private String title;
    private String content;
    @DBRef
    private List<Question> questions;
    @DBRef
    private List<TestResult> results;
    private LocalDateTime createDate;
    private String imageUrl;
    private int playCount;
}