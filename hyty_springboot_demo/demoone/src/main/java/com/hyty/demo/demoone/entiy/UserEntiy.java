package com.hyty.demo.demoone.entiy;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by czy on 2019/3/7.
 * 用户实体
 */
@Entity
public class UserEntiy implements Serializable{
        /**
         * 主键
         */
        @Id
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        @GeneratedValue(generator = "system-uuid")
        @Column(length = 32, unique = true)
        private String id;
        /**
         * 用户编号
         */
        @NotNull(message = "用户编号编号不能为空")
        @Column(name = "CODE", length = 50)
        private String code;
        /**
         * 用户名称
         */
        @NotNull(message = "用户名称不能为空")
        @Column(name = "NAME", length = 50)
        private String name;
        /**
         * 生效时间
         */
        @Column(name = "EFFECTIVE_DATE")
        private String effectivedate;
        /**
         * 状态（0-生效 1-失效）
         */
        @Column(name = "STATE", length = 10)
        private String state;

        /**
         * 与详细信息关联
         */
        @JSONField(serialize = false)
        @OneToMany(mappedBy = "userentiy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<UserMinute> userMinutes = new HashSet<>(0);

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEffectivedate() {
                return effectivedate;
        }

        public void setEffectivedate(String effectivedate) {
                this.effectivedate = effectivedate;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public Set<UserMinute> getUserMinutes() {
                return userMinutes;
        }

        public void setUserMinutes(Set<UserMinute> userMinutes) {
                this.userMinutes = userMinutes;
        }
}
