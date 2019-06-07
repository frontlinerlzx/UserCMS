package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.service.IProductService;
import com.frontlinerlzx.dao.IProductDao;
import com.frontlinerlzx.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {


    @Autowired
    private IProductDao productDao;

    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }


    public void save(Product product) {
        productDao.save(product);

    }
}
