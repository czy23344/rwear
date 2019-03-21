package com.hyty.demo.demoone.service;

import com.hyty.demo.demoone.entiy.UserEntiy;
import com.hyty.demo.demoone.entiy.UserMinute;

import java.util.List;

/**
 * Created by czy on 2019/3/7.
 * 用户service
 */
public interface UserService {
    UserEntiy selectuser(String id)throws Exception;
    /**
     * 查询全部用户
     * @return list
     * @throws Exception
     */
    List<UserEntiy> selectAll()throws Exception;

    /**
     * 根据ID查询详细信息
     * @param id 表头ID
     * @return
     * @throws Exception
     */
    List<UserMinute> selectwhereId(String id)throws Exception;

    /**
     * 插入或修改用户主表数据
     * @param userEntiy 用户主表对象
     * @return userEntiy
     * @throws Exception
     */
    UserEntiy insertuser(UserEntiy userEntiy)throws Exception;

    /**
     * 插入用户表体数据
     * @param userMinute 用户表体
     * @return boolean
     * @throws Exception
     */
    boolean insertuserminute(UserMinute userMinute)throws Exception;
    /**
     * 删除用户主表
     * @param id 用户ID
     * @return boolean
     * @throws Exception
     */
    boolean deleteUserEntiy(String id)throws Exception;

    /**
     * 删除用户表体
     * @param id 表体ID
     * @return boolean
     * @throws Exception
     */
    boolean deleteUserMinute(String id)throws Exception;


}
