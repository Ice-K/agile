package com.ice.agile.anagile.common.base;



import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Cteated by wangpeng
 * 2018/2/26 16:23
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2203613343377335393L;

    private Timestamp createTime; //创建时间
    private String createUser;    //创建人
    private Timestamp updateTime; //修改时间
    private String updateUser;    //修改人
    private Integer isDel;    //是否删除  0未删除  1已经删除


    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
