package com.MongMoong.MongBitProject.service;

import com.MongMoong.MongBitProject.model.Question;
import com.MongMoong.MongBitProject.model.Test;
import com.MongMoong.MongBitProject.model.TestResult;
import com.MongMoong.MongBitProject.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    /*
    PageRequest는 Pageable 인터페이스를 구현하는 클래스이므로 Pageable 타입을 요구하는 메소드에 PageRequest 인스턴스를 전달할 수 있다.
    getRecentTests())에서 PageRequest 인스턴스를 생성하고 findByOrderByCreateDateDesc())에 전달하면 타입 오류가 발생하지 않는다.
     */

    // 새로운 테스트 생성
    public Test createTest(Test test) {
        Test createdTest = testRepository.save(test);
        return createdTest;
    }
    public List<Test> getRecentTests(int size) {
        Page<Test> page = testRepository.findByOrderByCreateDateDesc(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createDate")));
        return page.getContent();
    }

    public List<Test> getTestList(){
        List<Test> testList = testRepository.findAll();
        return testList;
    }
    public Optional<Test> getTest(String id){
        //test내용을 출력하는 화면에 question, testResult 는 필요 x
        // ㄴ모든 정보를 한번에 가져와서 넘길거면 이 부분 수정
        Optional<Test> test = testRepository.findById(id);
        return test;
    }

    public Test updateTest(String id, Test updatedTest) {
        Optional<Test> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            test.setTitle(updatedTest.getTitle());
            test.setContent(updatedTest.getContent());
            test.setImageUrl(updatedTest.getImageUrl());
            test.setPlayCount(updatedTest.getPlayCount());
            //list 형태로 수정
            test.setQuestions(updatedTest.getQuestions());
            test.setResults(updatedTest.getResults());

            return testRepository.save(test);
        } else {
            throw new IllegalArgumentException("Test not found with ID: " + id);
        }
    }

    public void deleteTest(String id){
        testRepository.deleteById(id);
    }

//    public Optional<Test> getTest(String id){
//        Optional<Test> test = testRepository.findById(id);
//        test.ifPresent(t -> {
//            List<Question> questions = testRepository.findQuestionById(id);
//            List<TestResult> testResults = testRepository.findTestResultById(id);
//            t.setQuestions(questions);
//            t.setResults(testResults);
//        });
//        return test;
//    }
//    public List<Question> getQuestions(String id){
//        return testRepository.findQuestionsById(id);
//    }
//    public List<TestResult> getTestResult(String id){
//        return testRepository.findTestResultsById(id);
//    }


}
