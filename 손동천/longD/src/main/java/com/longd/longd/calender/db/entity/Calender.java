package com.longd.longd.calender.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Calender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private Integer coupleListId;
    private LocalDateTime dateStart; // 지정 날짜
    private LocalDateTime dateEnd; // 하루 초과일 경우 사용
    private Integer type; // 기념일 속성, (1은 생일, 2은 병원예약 처럼)
    private String content; // 내용


}
