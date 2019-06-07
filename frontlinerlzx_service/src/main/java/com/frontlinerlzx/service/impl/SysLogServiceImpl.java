package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.dao.ISysLogDao;
import com.frontlinerlzx.domain.SysLog;
import com.frontlinerlzx.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {


    @Autowired
    ISysLogDao sysLogDao;
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);

    }

    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        //参数pageNum页码值，参数pageSize代表是每页显示的条数
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
