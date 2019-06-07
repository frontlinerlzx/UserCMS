package com.frontlinerlzx.service;

import com.frontlinerlzx.domain.Role;
import com.frontlinerlzx.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {


    public List<UserInfo> findAll() throws Exception;

    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findById(String id) throws Exception;

    public List<Role> findOtherRoles(String userid) throws Exception;

    public  void addRoleToUser(String userId, String[] roleids) throws Exception;

    public void deleteById(String id) throws Exception;
}
