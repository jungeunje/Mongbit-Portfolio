package com.MongMoong.MongBitProject.aspect;

import com.MongMoong.MongBitProject.exception.DataMismatchException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TestScoreAspect {

    @Before("@annotation(com.MongMoong.MongBitProject.aspect.TestScoreCheck)")
    public void checkScoreValidate(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        int[] score = scoreIntArrAndLengthCheck(args);
        if(score.length != 4){
            throw new DataMismatchException("score의 길이가 부적합합니다.");
        }
        checkCollectNumber(score);
    }

    private static void checkCollectNumber(int[] score) {
        for (int param : score) {
            if (param != -3 && param != -1 && param != 1 && param != 3) {
                throw new DataMismatchException("유효하지 않은 값이 있습니다: " + param);
            }
        }
    }

    private int[] scoreIntArrAndLengthCheck(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof int[]) {
                return (int[]) arg;
            }
        }
        throw new DataMismatchException("score의 데이터 타입이 잘못되었습니다.");
    }
}