package com.longd.longd.plan.service;

import com.longd.longd.plan.db.entity.Plan;
import com.longd.longd.plan.db.dto.PlanListDto;

import java.util.List;

public interface PlanSerivce {

    public List<PlanListDto> getPlan(int coupleListId);

    public boolean setPlan(Plan plan);

    public boolean deletePlan(int id);

    public Plan getDetailPlan(int planId);
}
