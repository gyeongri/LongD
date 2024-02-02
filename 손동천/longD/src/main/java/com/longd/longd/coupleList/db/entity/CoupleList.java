package com.longd.longd.coupleList.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class CoupleList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;
    private Integer userIdA;
    private Integer userIdB;
    private LocalDate startDay;
    private Integer oneQA_index;
    private String coupleImgUrl;


}
