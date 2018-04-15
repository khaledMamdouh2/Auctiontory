package com.auctiontory.model.dal;

import com.auctiontory.model.entity.BatchAuction;

public interface BatchAuctionDAO extends IDaoBase<BatchAuction> {

    boolean isActive(Integer id);

    boolean isExist(String auctionTitle);
}
