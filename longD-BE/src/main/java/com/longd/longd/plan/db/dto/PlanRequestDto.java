package com.longd.longd.plan.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PlanRequestDto {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;
    private List<PlanInfoListDto> planInfo;
}
