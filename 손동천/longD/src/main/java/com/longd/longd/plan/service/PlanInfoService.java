package com.longd.longd.plan.service;



import com.longd.longd.plan.db.entity.PlanInfo;

import java.util.List;

public interface PlanInfoService {

    public List<PlanInfo> getPlanInfo(int planId);

    public boolean setPlanInfo(PlanInfo planInfo);

    public boolean deletePlanInfo(int id);

}
