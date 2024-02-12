package com.longd.longd.plan.service;



import com.longd.longd.plan.db.dto.PlanInfoListDto;
import com.longd.longd.plan.db.entity.PlanInfo;

import java.util.List;

public interface PlanInfoService {

    public List<PlanInfoListDto> getPlanInfoList(int planId);
    public PlanInfo getPlanInfo(int id);

    public boolean setPlanInfo(PlanInfo planInfo);

    public boolean deletePlanInfo(int id);

}
