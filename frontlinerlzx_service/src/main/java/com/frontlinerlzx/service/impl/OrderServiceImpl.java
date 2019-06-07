package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.dao.IOrdersDao;
import com.frontlinerlzx.domain.Orders;
import com.frontlinerlzx.service.IOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrdersDao ordersDao;
    public List<Orders> findAll(int page,int size) throws Exception {

        //参数pageNum页码值，参数pageSize代表是每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

    public void deleteById(String id) {

    }
}
