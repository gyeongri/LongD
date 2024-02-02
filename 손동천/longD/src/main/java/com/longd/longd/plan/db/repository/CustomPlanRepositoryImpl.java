package com.longd.longd.plan.db.repository;

import com.longd.longd.plan.db.entity.Plan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class CustomPlanRepositoryImpl implements CustomPlanRepository{

    @Autowired
    EntityManager entityManager;
    @Override
    public int findCoupleListIdById(int id) {
        try {
            Plan plan = entityManager.createQuery("select p from Plan p where p.id = :id", Plan.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return plan.getCoupleListId();
        } catch (NoResultException e) {
            // 원하는 예외 처리를 수행하거나, 예외를 다시 던지거나, 기본값을 반환하거나 등의 로직을 추가할 수 있습니다.
            return 0; // 또는 다른 기본값을 반환하거나 원하는 로직을 수행하세요.
        }
    }
}
