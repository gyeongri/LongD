package com.longd.longd.user.db.repository;

import com.longd.longd.user.db.entity.NationList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationListRepository extends JpaRepository<NationList, Integer> {

}
