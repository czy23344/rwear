package com.hyty.demo.demoone.entiy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by czy on 2019/3/7.
 * 用户详细信息
 */
@Entity
public class UserMinute implements Serializable {
    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32, unique = true)
    private String id;
    /**
     * 工作单位
     */
    @Column(name = "ADREESS", length = 200)
    private String adreess;
    /**
     * 工作性质
     */
    @Column(name = "WORKTYPE", length = 200)
    private String worktype;
    /**
     * 工作年限
     */
    @Column(name = "WORKTIME", length = 200)
    private BigDecimal worktimee;

    /**
     * 工号
     */
    @Column(name = "IDNUMBER", length = 50)
    private String idnumber;

    /**
     * 起始时间
     */
    @Column(name = "BEGINTIME", length = 200)
    private String begin;
    /**
     * 结束时间
     */
    @Column(name = "ENDTIME", length = 200)
    private String end;
    /**
     * 与用户表关联
     */
    @JoinColumn(name = "userentiy_id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private UserEntiy userentiy;
    @Transient
    private String headId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public BigDecimal getWorktimee() {
        return worktimee;
    }

    public void setWorktimee(BigDecimal worktimee) {
        this.worktimee = worktimee;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getAdreess() {
        return adreess;
    }

    public void setAdreess(String adreess) {
        this.adreess = adreess;
    }

    public UserEntiy getUserentiy() {
        return userentiy;
    }

    public void setUserentiy(UserEntiy userentiy) {
        this.userentiy = userentiy;
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }
}
