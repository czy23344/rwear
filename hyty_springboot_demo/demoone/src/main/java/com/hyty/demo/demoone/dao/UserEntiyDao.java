package com.hyty.demo.demoone.dao;

import com.hyty.demo.demoone.entiy.UserEntiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by czy on 2019/3/7.
 */
@Repository
public interface UserEntiyDao extends JpaRepository<UserEntiy, String>, JpaSpecificationExecutor<UserEntiy> {

}
