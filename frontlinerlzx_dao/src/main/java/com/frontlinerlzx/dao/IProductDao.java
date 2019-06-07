package com.frontlinerlzx.dao;

import com.frontlinerlzx.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    /**
     * 根据id查询产品
     */
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;
    /**
     * 查询所有的产品信息
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * 保存产品
     */

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
}
