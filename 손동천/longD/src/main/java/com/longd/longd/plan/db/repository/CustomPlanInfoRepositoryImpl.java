package com.longd.longd.plan.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@RequiredArgsConstructor
@Repository
@Transactional
public class CustomPlanInfoRepositoryImpl implements CustomPlanInfoRepository {

    @Autowired
    EntityManager entityManager;

    //조인 하나 작성해야함

}
