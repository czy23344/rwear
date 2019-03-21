package com.hyty.demo.demoone.service.impl;

import com.hyty.demo.demoone.dao.UserEntiyDao;
import com.hyty.demo.demoone.dao.UserMinuteDao;
import com.hyty.demo.demoone.entiy.UserEntiy;
import com.hyty.demo.demoone.entiy.UserMinute;
import com.hyty.demo.demoone.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by czy on 2019/3/7.
 * 用户service实现
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserEntiyDao userEntiyDao;
    @Autowired
    UserMinuteDao userMinuteDao;

    /**
     * 构建分页参数
     *
     * @param pageNumber 当前页数
     * @param pageSize   每页条数
     * @return 分页参数
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber - 1, pageSize);
    }

    @Override
    public UserEntiy selectuser(String id) throws Exception {
        UserEntiy userEntiy =userEntiyDao.findById(id).get();
        return userEntiy;
    }

    /**
     * 查询全部用户
     *
     * @return list
     * @throws Exception
     */
    @Override
    public List<UserEntiy> selectAll() throws Exception {
        Specification<UserEntiy> select = select();
        return userEntiyDao.findAll(select);
    }

    public Specification<UserEntiy> select() {
        //封装查询条件
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p = null;
            //按录用户CODE排序
            query.orderBy(cb.desc(root.get("code")));
            if (p != null) {
                list.add(p);
            }
            Predicate[] pre = new Predicate[list.size()];
            return query.where(list.toArray(pre)).getRestriction();
        };
    }

    /**
     * 根据ID查询详细信息
     *
     * @param id 表头ID
     * @return
     * @throws Exception
     */
    @Override
    public List<UserMinute> selectwhereId(String id) throws Exception {
        Specification<UserMinute> select = selectbody(id);
        return userMinuteDao.findAll(select);
    }

    public Specification<UserMinute> selectbody(String id) {
        //封装查询条件
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p = null;
            Join<UserMinute, UserEntiy> enterpriseJoin = root.join(root.getModel().getSingularAttribute
                    ("userentiy", UserEntiy.class), JoinType.INNER);
            //表头ID
            if (StringUtils.isNotBlank(id)) {
                Predicate p3 = cb.equal(enterpriseJoin.get("id").as(String.class), id);
                if (p != null) {
                    p = cb.and(p, p3);
                } else {
                    p = p3;
                }
            }
            if (p != null) {
                list.add(p);
            }
            Predicate[] pre = new Predicate[list.size()];
            return query.where(list.toArray(pre)).getRestriction();
        };
    }

    /**
     * 插入或修改用户主表数据
     *
     * @param userEntiy 用户主表对象
     * @return userEntiy
     * @throws Exception
     */
    @Override
    public UserEntiy insertuser(UserEntiy userEntiy) throws Exception {
        //保存用户
        UserEntiy userEntiy1 = userEntiyDao.save(userEntiy);
        return userEntiy1;
    }

    /**
     * 插入用户表体数据
     *
     * @param userMinute 用户表体
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean insertuserminute(UserMinute userMinute) {
        //获取到表头ID
        if (userMinute.getHeadId() != null && userMinute.getHeadId() != "") {
            //查询表头实体
            UserEntiy userEntiy = userEntiyDao.findById(userMinute.getHeadId()).get();
            //查询到表头实体不为空
            if (userEntiy != null) {
                //插入表头实体到表体 建立一对多关系
                userMinute.setUserentiy(userEntiy);
                //保存表体
                userMinuteDao.save(userMinute);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 删除用户主表
     *
     * @param id 用户ID
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean deleteUserEntiy(String id) throws Exception {
        if (id != null && id != "") {
            userEntiyDao.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * 删除用户表体
     *
     * @param id 表体ID
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean deleteUserMinute(String id) throws Exception {
        if (id != null && id != "") {
            userMinuteDao.deleteById(id);
            return true;
        }
        return false;
    }
}
