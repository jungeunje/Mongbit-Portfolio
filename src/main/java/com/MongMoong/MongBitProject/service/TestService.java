package com.MongMoong.MongBitProject.service;

import com.MongMoong.MongBitProject.aspect.TestNullCheck;
import com.MongMoong.MongBitProject.model.Question;
import com.MongMoong.MongBitProject.model.Test;
import com.MongMoong.MongBitProject.model.TestResult;
import com.MongMoong.MongBitProject.repository.TestRepository;
import com.MongMoong.MongBitProject.service.QuestionService;
import com.MongMoong.MongBitProject.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final QuestionService questionService;
    private final TestResultService testResultService;
    // 테스트 생성
    @TestNullCheck
    public Test createTest(Test test) {
        test.setCreateDate(LocalDateTime.now());
        test.setPlayCount(0);
        test.setContent(test.getContent().replaceAll("\n", "<br>"));
        List<Question> questionList = test.getQuestions();
        questionService.createQuestionList(questionList);
        List<TestResult> testResultList = test.getResults();
        for (TestResult testResult : testResultList) {
            testResult.setContent(testResult.getContent().replaceAll("\n", "<br>"));
        }
        testResultService.createTestResultList(testResultList);
        Test createdTest = testRepository.save(test);

        return createdTest;
    }

    //테스트 수정
    @TestNullCheck
    public Test updateTest(Test test) {
        test.setContent(test.getContent().replaceAll("\n", "<br>"));
        questionService.updateQuestionList(test.getQuestions());
        for (TestResult testResult : test.getResults()) {
            testResult.setContent(testResult.getContent().replaceAll("\n", "<br>"));
        }
        testResultService.updateTestResultList(test.getResults());
        return testRepository.save(test);
    }

    //테스트 삭제
    public void deleteTest(String testId){
        Test findTest = testRepository.findById(testId).get();
        List<Question> questionList = findTest.getQuestions();
        for (Question question : questionList) {
            questionService.deleteQuestion(question.getId());
        }
        List<TestResult> testResultList = findTest.getResults();
        for (TestResult testResult : testResultList) {
            testResultService.deleteTestResult(testResult.getId());
        }
        testRepository.delete(findTest);
    }

    //모든 테스트 불러오기(리스트)
    public List<Test> getTestList(){
        List<Test> testList = testRepository.findAll();
        return testList;
    }

    //특정 테스트 하나 불러오기
    public Test getTest(String testId){
        Test test = testRepository.findById(testId).get();
        return test;
    }

}
