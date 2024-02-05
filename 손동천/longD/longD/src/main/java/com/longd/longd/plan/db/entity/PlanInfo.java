package com.longd.longd.plan.db.entity;

import jakarta.persistence.*;
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
@Entity
@NoArgsConstructor
public class PlanInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    private Plan plan;
    //private Integer planId;
    private String title;
    private String infoType;
    private String MyOrder;
    private String titleUrl;
    private LocalDate date;
    private Long latitude;
    private Long longitude;
    private String memo;


}
