package com.longd.longd.plan.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlanListDto {

    private Integer id;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;
}
