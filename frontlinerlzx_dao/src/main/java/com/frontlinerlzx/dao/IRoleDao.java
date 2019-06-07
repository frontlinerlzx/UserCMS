package com.frontlinerlzx.dao;

import com.frontlinerlzx.domain.Permission;
import com.frontlinerlzx.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    /**
     * 根据用户的id查询出所有对应的角色
     * @param UserId
     * @return
     */
    @Select("select * from role where id in (select roleid from users_role where userid = #{userid}) ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.frontlinerlzx.dao.IPermissionsDao.findPermissionByRoleId")),

    })
    public List<Role> findRoleByUserId(String UserId);


    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select  * from role where id = #{roleId}")
    public Role findById(String roleId);


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    public  void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);


    @Delete("delete from users_role where roleId = #{id}")
    public void deleteUserAndRole(String id) throws Exception;

    @Delete("delete from role where id = #{id}")
    void deleteById(String id) throws Exception;
}
