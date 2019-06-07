package com.frontlinerlzx.service;

import com.frontlinerlzx.domain.Permission;

import java.util.List;

public interface IPermissionService {


    public List<Permission> findAll() throws Exception;


    public void save(Permission permission) throws  Exception;

    public void deleteById(String id)throws Exception;
}
