package com.frontlinerlzx.controller;

import com.frontlinerlzx.domain.Orders;
import com.frontlinerlzx.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    IOrderService orderService;
//
//    //查询全部订单的未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = orderService.findAll();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");//对应想要访问的jsp页面
//        return mv;
//
//    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "2") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
        //pageInfo就是分页的Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");


        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true ) String ordersId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");


        return mv;

    }
}
