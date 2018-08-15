package org.classsix.ofms.domin.vo;

import org.classsix.ofms.domin.PowerPlant;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
public class UserVO {
    private Integer id;

    /** 账号 */
    private String userName;

    /** 密码 */
    private String password;

    /** 所属电厂 */
    private PowerPlant powerPlant;

    /** 角色划分 0为管理员、1为发布人员*/
    private Integer role;

    /** 真实姓名 **/
    private String realName;

    private int isDelete = 0;

    public UserVO() {
    }

    public UserVO(Integer id, String userName, String password, PowerPlant powerPlant, Integer role, String realName, int isDelete) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.powerPlant = powerPlant;
        this.role = role;
        this.realName = realName;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PowerPlant getPowerPlant() {
        return powerPlant;
    }

    public void setPowerPlant(PowerPlant powerPlant) {
        this.powerPlant = powerPlant;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
