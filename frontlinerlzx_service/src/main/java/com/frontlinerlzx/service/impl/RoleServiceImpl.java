package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.dao.IRoleDao;
import com.frontlinerlzx.domain.Permission;
import com.frontlinerlzx.domain.Role;
import com.frontlinerlzx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {


    @Autowired
    IRoleDao roleDao;


    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    public Role findByRoleId(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId:permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }

    public void deleteById(String id) throws Exception {
        roleDao.deleteUserAndRole(id);
        roleDao.deleteById(id);

    }
}
