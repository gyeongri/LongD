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

//    public Integer findCoupleListIdById(int id) {
//        Integer coupleListId = entityManager.createQuery("select a.coupleListId from Plan a join PlanInfo b on a.id = b.planId where b.id = :id")
//                .setParameter("id", id).getFirstResult();
//        return coupleListId;
//    }

    //조인 하나 작성해야함

    /*
        public Optional<User> findByUserId(String userId) {
        List<User> user = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

        return user.stream().findAny();
    }
     */
}
