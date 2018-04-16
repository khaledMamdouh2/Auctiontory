package com.auctiontory.controller;

import com.auctiontory.model.entity.PartsAuction;

public interface PartsAuctionController extends IControllerBase<PartsAuction> {
    boolean isExist(String auctionTitle);
    boolean isActive(Integer id);
}
