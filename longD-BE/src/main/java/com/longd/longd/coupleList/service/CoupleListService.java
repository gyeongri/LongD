package com.longd.longd.coupleList.service;

import com.longd.longd.coupleList.db.dto.CheckRegistDto;
import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.user.db.entity.User;

import java.util.Optional;

public interface CoupleListService {

    public String setCoupleList(CheckRegistDto checkRegistDto);
    public boolean modifyCoupleList(CoupleList coupleList);
    public Optional<CoupleList> getCoupleListInfo();
    public User getPartnerInfo();
}
