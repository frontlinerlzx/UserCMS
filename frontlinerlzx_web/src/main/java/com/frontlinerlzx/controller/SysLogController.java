package com.frontlinerlzx.controller;

import com.frontlinerlzx.domain.SysLog;
import com.frontlinerlzx.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "2") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogServiceAll = sysLogService.findAll(page,size);
        //pageInfo就是分页的Bean
        PageInfo pageInfo = new PageInfo(sysLogServiceAll);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
//
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//
//        ModelAndView mv = new ModelAndView();
//
//        List<SysLog> sysLogs = sysLogService.findAll();
//
//        mv.addObject("sysLogs", sysLogs);
//        mv.setViewName("syslog-list");
//        return mv;
//    }


}
