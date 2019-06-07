package com.frontlinerlzx.service;

import com.frontlinerlzx.domain.Permission;
import com.frontlinerlzx.domain.Role;

import java.util.List;

public interface IRoleService {


    public List<Role> findAll() throws Exception;

    public void save(Role role) throws Exception;

    public Role findByRoleId(String roleId)throws Exception;

    public List<Permission> findOtherPermissions(String roleId)throws Exception;

    public void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
