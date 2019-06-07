package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.dao.IPermissionsDao;
import com.frontlinerlzx.domain.Permission;
import com.frontlinerlzx.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionsDao permissionsDao;
    public List<Permission> findAll() throws Exception {

        return permissionsDao.findAll();
    }

    public void save(Permission permission) throws Exception {
        permissionsDao.save(permission);
    }

    public void deleteById(String id) throws Exception {
        permissionsDao.deleteRoleAndPermission(id);
        permissionsDao.deleteById(id);
    }
}
