package com.MongMoong.MongBitProject.service;

import com.MongMoong.MongBitProject.model.Question;
import com.MongMoong.MongBitProject.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    // 질문 리스트 생성하기
    public List<Question> createQuestionList(List<Question> questionLists){
        List<Question> questionList = questionRepository.saveAll(questionLists);
        return questionList;
    }

    // 질문 생성하기
    public Question createQuestion(Question question){
        Question createdquestion = questionRepository.save(question);
        return createdquestion;
    }

    public List<Question> getQuestionList(){
        return questionRepository.findAll();
    }

    public Question getQuestion(String id){
        return questionRepository.findById(id).get();
    }

    public List<Question> updateQuestionList(List<Question> questionList){
        return questionRepository.saveAll(questionList);
    }

    public void deleteQuestion(String id){
        questionRepository.deleteById(id);
    }
}