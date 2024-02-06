package com.longd.longd.bucketList.service;

import com.longd.longd.bucketList.db.entity.BucketList;

import java.util.List;

public interface BucketListService {

    public List<BucketList> getBucketList(int coupleListId);
    public BucketList getBucket(int id);
    public boolean setBucket(BucketList bucketList);
    public boolean deleteBucket(int id);

}
