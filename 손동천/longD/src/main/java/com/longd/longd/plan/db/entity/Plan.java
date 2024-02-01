package com.longd.longd.plan.db.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Setter
@Slf4j
@ToString
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    private Integer coupleListId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;

}
