package com.frontlinerlzx.service;

import com.frontlinerlzx.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws Exception;

    public void save(Product product);

}
