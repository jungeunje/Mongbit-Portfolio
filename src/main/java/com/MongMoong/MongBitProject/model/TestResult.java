package com.MongMoong.MongBitProject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TestResult")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
    @Id
    private String id;
    private String result;
    private String title;
    private String content;
    private String imageUrl;
}
