package com.longd.longd.calendar.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;
    private Integer coupleListId;
    private LocalDate start; // 지정 날짜
    private LocalDate end; // 하루 초과일 경우 사용
    private Integer type; // 기념일 속성, (1은 생일, 2은 병원예약 처럼)
    private String title; // 제목
    private String content; // 내용


}
