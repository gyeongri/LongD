package com.longd.longd.plan.db.repository;


import com.longd.longd.plan.db.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>, CustomPlanRepository {

    List<Plan> findByCoupleListId(int coupleListId);
    public Optional<Plan> findById(int id);
    List<Plan> findByDateStartLessThanEqualAndDateEndGreaterThanEqualAndCoupleList_IdEquals(LocalDate date1, LocalDate date2, int coupleListId);
}
