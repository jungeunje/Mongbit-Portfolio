package com.MongMoong.MongBitProject.service;

import com.MongMoong.MongBitProject.model.Question;
import com.MongMoong.MongBitProject.model.TestResult;
import com.MongMoong.MongBitProject.repository.QuestionRepository;
import com.MongMoong.MongBitProject.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;


    public List<TestResult> createTestResultList(List<TestResult> testResultLists){
        return testResultRepository.saveAll(testResultLists);
    }

    public TestResult createTestResult(TestResult testResult){
        return testResultRepository.save(testResult);
    }
    public List<TestResult> getTestResultList(){
        return testResultRepository.findAll();
    }

    public TestResult getTestResult(String id){
        TestResult testResult = testResultRepository.findById(id).get();
        return testResult;
    }

    public TestResult updateTestResult(TestResult testResult){
        return testResultRepository.save(testResult);
    }
    public List<TestResult> updateTestResultList(List<TestResult> testResultList){
        return testResultRepository.saveAll(testResultList);
    }
    public void deleteTestResult(String id){
        testResultRepository.deleteById(id);
    }

    public TestResult getTestResultFromMyPage(String testResultId) {
        TestResult testResult = testResultRepository.findById(testResultId).get();
        return testResult;
    }

}