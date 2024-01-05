package com.MongMoong.MongBitProject.service;

import com.MongMoong.MongBitProject.aspect.TestNullCheck;
import com.MongMoong.MongBitProject.aspect.TestScoreCheck;
import com.MongMoong.MongBitProject.model.Test;
import com.MongMoong.MongBitProject.model.TestResult;
import com.MongMoong.MongBitProject.repository.TestRepository;
import com.MongMoong.MongBitProject.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTestResultService {

    /*
score[0] > 0 == "E" else "I"
score[1] > 0 == "N" else "S"
score[2] > 0 == "F" else "T"
score[3] > 0 == "J" else "P"
 */
    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final TestService testService;

    @TestNullCheck
    @TestScoreCheck
    public String getTestResultI(String testId, int[] score) {
        String result = setResult(score);
        Test findTest = testService.getTest(testId);
        List<TestResult> testResultList = findTest.getResults();
        for(TestResult testResult : testResultList) {
            if (testResult.getResult().toUpperCase().equals(result)) {
                return testResult.getId();
            }
        }
        return "";
    }

    // 테스트 결과 점수를 MBTI로 변환하기
    private static String setResult(int[] score) {
        String result = "";
        if (score[0] > 0) {
            result += "E";
        } else {
            result += "I";
        }
        if (score[1] > 0) {
            result += "N";
        } else {
            result += "S";
        }
        if (score[2] > 0) {
            result += "F";
        } else {
            result += "T";
        }
        if (score[3] > 0) {
            result += "J";
        } else {
            result += "P";
        }
        return result;
    }


}