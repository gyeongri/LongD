package com.longd.longd.plan.db.entity;

import com.longd.longd.coupleList.db.entity.CoupleList;
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
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPLE_LIST_ID")
    private CoupleList coupleList;
//    private Integer coupleListId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;

}
