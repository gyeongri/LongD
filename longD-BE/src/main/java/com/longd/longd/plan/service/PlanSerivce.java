package com.longd.longd.plan.service;

import com.longd.longd.plan.db.entity.Plan;

import java.util.List;

public interface PlanSerivce {

    public List<Plan> getPlan(int coupleListId);

    public boolean setPlan(Plan plan);

    public boolean deletePlan(int id);
}
