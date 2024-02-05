package com.longd.longd.calendar.db.repository;

import com.longd.longd.calendar.db.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    public List<Calendar> findByCoupleListId(int coupleListId);

    public Optional<Calendar> findById(int id);
}
