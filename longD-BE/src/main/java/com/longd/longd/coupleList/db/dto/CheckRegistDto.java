package com.longd.longd.coupleList.db.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckRegistDto {

    private String name;
    private LocalDate birth;
    private String email;
    private int code;
    private LocalDate date;


}
