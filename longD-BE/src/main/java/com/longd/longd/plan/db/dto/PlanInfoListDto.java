package com.longd.longd.plan.db.dto;

import com.longd.longd.plan.db.entity.Plan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class PlanInfoListDto {

    private Integer id;
    private String title;
    private String infoType;
    private String MyOrder;
    private String titleUrl;
    private LocalDate date;
    private Long latitude;
    private Long longitude;
    private String memo;
}
