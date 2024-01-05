package com.MongMoong.MongBitProject.aspect;

import com.MongMoong.MongBitProject.exception.DataMismatchException;
import com.MongMoong.MongBitProject.model.Question;
import com.MongMoong.MongBitProject.model.Test;
import com.MongMoong.MongBitProject.model.TestResult;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class TestNullAspect {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 500;

    @Before("@annotation(com.MongMoong.MongBitProject.aspect.TestNullCheck)")
    public void testNullCheck(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        dataNullCheck(args);
    }

    private void dataNullCheck(Object[] args) {
        Test test;
        for (Object arg : args) {
            if (arg instanceof Test) {
                test = (Test) arg;
                if (isTestDataIncomplete(test)) {
                    throw new DataMismatchException("모든 데이터가 채워져야 합니다.");
                }
                int testTitleLength = test.getTitle().length();
                int testContentLength = test.getContent().length();
                if(testTitleLength<MIN_LENGTH || testTitleLength > MAX_LENGTH || testContentLength <MIN_LENGTH || testContentLength > MAX_LENGTH){
                    throw new DataMismatchException("test 내용 글자제한 수 확인바랍니다.");
                }
                questionDataCheck(test);
                testResultDataCheck(test);
            }
        }


    }

    private void testResultDataCheck(Test test) {
        List<TestResult> testResultList = test.getResults();
        if(testResultList.size() != 16){
            throw new DataMismatchException("testResult는 16개를 등록해야 합니다.");
        }
        for (TestResult testResult : testResultList) {
            int resultTitleLength= testResult.getTitle().length();
            int resultContentLength = testResult.getContent().length();
            String result = testResult.getResult();
            if(resultTitleLength<MIN_LENGTH || resultTitleLength > MAX_LENGTH || resultContentLength<MIN_LENGTH || resultContentLength > MAX_LENGTH){
                throw new DataMismatchException("result 내용 글자제한 수 확인바랍니다.");
            }
            if (result.length() != 4) {
                throw new DataMismatchException("result는 4글자여야 합니다.");
            }
            if (!result.matches("[EIei][SNsn][TFtf][PJpj]")) {
                throw new DataMismatchException("result에는 MBTI 결과가 등록되어야 합니다.");
            }
        }

    }

    private void questionDataCheck(Test test) {
        List<Question> questionList = test.getQuestions();
        if(questionList.size() != 12){
            throw new DataMismatchException("question은 12개를 등록해야 합니다.");
        }
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            if (question.getIndex() != i) {
                throw new DataMismatchException("question index를 확인해주세요.");
            }
            int questionLength = question.getQuestion().length();
            int answerPlusLength = question.getAnswerPlus().length();
            int answerMinusLength = question.getAnswerMinus().length();
            if (questionLength < MIN_LENGTH || questionLength > MAX_LENGTH || answerPlusLength < MIN_LENGTH || answerPlusLength > MAX_LENGTH || answerMinusLength < MIN_LENGTH || answerMinusLength > MAX_LENGTH) {
                throw new DataMismatchException("question 내용 글자제한 수 확인바랍니다.");
            }
        }
    }
    private boolean isTestDataIncomplete(Test test) {
        return test.getTitle() == null || test.getContent() == null || test.getQuestions() == null || test.getResults() == null || test.getImageUrl() == null;
    }
}