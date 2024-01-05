package com.MongMoong.MongBitProject.controller;

import com.MongMoong.MongBitProject.aspect.AdminRequired;
import com.MongMoong.MongBitProject.dto.TestCoverDTO;
import com.MongMoong.MongBitProject.dto.TestCoverResponse;
import com.MongMoong.MongBitProject.dto.TestResultFromMyPageResponse;
import com.MongMoong.MongBitProject.model.Test;
import com.MongMoong.MongBitProject.model.TestResult;
import com.MongMoong.MongBitProject.service.LikeService;
import com.MongMoong.MongBitProject.service.TestResultService;
import com.MongMoong.MongBitProject.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tests")
@Tag(name = "Test Controller", description = "심리테스트 데이터와 관련된 API를 제공하는 컨트롤러입니다.")
public class TestController {
    private final TestService testService;
    private final TestResultService testResultService;
    private final LikeService likeService;

    @AdminRequired
    @PostMapping("/test")
    @Operation(
            summary = "테스트 만들기",
            description = "필요한 데이터:" +
                    "Test의 title, content, questions(Question_id 리스트),  results(TestResult_id 리스트), imageUrl" +
                    "Question의 index(0~11), question, answerPlus, answerMinus " +
                    "TestResult의 result(MBTI 16가지), title, content, imageUrl")
    @SecurityRequirements(value = {@SecurityRequirement(name = "JWT")})
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        Test createdTest = testService.createTest(test);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @AdminRequired
    @PatchMapping("/test")
    @Operation(
            summary = "Test 정보 수정",
            description = "title, content, questions 리스트, results 리스트, imageUrl, playCount, id 순서로 전달해주세요.")
    @SecurityRequirements(value = {@SecurityRequirement(name = "JWT")})
    public ResponseEntity<Test> updateTest(@RequestBody Test test){
        Test updatedTest = testService.updateTest(test);
        return ResponseEntity.noContent().build();
    }

    @AdminRequired
    @DeleteMapping("/test/{testId}")
    @Operation(
            summary = "Test 삭제",
            description = "testId가 필요합니다. question, testResult가 같이 삭제됩니다.")
    @SecurityRequirements(value = {@SecurityRequirement(name = "JWT")})
    public ResponseEntity<Void> deleteTest(@PathVariable String testId){
        testService.deleteTest(testId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test/{testId}")
    @Operation(summary = "특정 테스트 조회", description = "테스트 하나에 대한 내용을 가져와 반환합니다.")
    public ResponseEntity<Test> getTest(@PathVariable String testId){
        Test findTest = testService.getTest(testId);
        System.out.println("testId = " + testId);
        return ResponseEntity.ok(findTest);
    }

    @GetMapping("")
    @Operation(summary = "모든 테스트 조회", description = "모든 테스트를 리스트로 가져와 반환합니다.")
    public ResponseEntity<List<TestCoverDTO>> getTestList(){
        List<TestCoverDTO> testList = testService.getTestList();
        return ResponseEntity.ok(testList);
    }

}