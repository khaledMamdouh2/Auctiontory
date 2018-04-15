package com.auctiontory.model.dal;

import com.auctiontory.model.entity.PartsAuction;

public interface PartsAuctionDAO extends IDaoBase<PartsAuction> {

    boolean isExist(String auctionTitle);
}
