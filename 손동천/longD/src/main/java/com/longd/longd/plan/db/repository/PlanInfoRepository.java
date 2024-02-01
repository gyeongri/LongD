package com.longd.longd.plan.db.repository;

import com.longd.longd.plan.db.entity.PlanInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfo, Integer>, CustomPlanInfoRepository {

    List<PlanInfo> findByCoupleListId(int coupleListId);
    public Optional<PlanInfo> findById(int id);
}
