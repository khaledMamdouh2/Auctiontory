package com.auctiontory.controller;

import com.auctiontory.model.entity.BatchAuction;

public interface BatchAuctionController extends IControllerBase<BatchAuction>{

    boolean isActive(Integer id);

}
