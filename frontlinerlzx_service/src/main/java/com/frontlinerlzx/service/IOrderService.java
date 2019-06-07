package com.frontlinerlzx.service;

import com.frontlinerlzx.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll(int page,int size) throws Exception;


    Orders findById(String ordersId) throws Exception;
}
