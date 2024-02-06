package com.longd.longd.bucketList.db.repository;

import com.longd.longd.bucketList.db.entity.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketListRepository extends JpaRepository<BucketList, Integer> {

    public List<BucketList> findByCoupleList_Id(int id);

}
