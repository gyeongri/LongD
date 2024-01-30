package com.longd.longd.calender.db.repository;

import com.longd.longd.calender.db.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long> {

    public List<Calender> findByCoupleListId(int CoupleListId);
}
