package com.longd.longd.plan.db.repository;

import com.longd.longd.coupleList.db.entity.CoupleList;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
@Transactional
public class CustomPlanInfoRepositoryImpl implements CustomPlanInfoRepository {

    @Autowired
    EntityManager entityManager;


}
