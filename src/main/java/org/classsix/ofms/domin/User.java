package org.classsix.ofms.domin;

import org.classsix.ofms.domin.common.BasePerson;
import org.classsix.ofms.domin.validgroup.UserGroup;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */
@Entity
@Table(name = "ADMIN")
public class User extends BasePerson{
    private static final long serialVersionUID = 5783613723738241740L;
    /** 用户主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_USER_ID")
    private Integer id;

    /** 账号 */
    @Column(name = "USER_NAME", length = 50, nullable = false,unique = true)
    @NotNull(groups = {UserGroup.login.class})
    private String userName;

    /** 密码 */
    @Column(name = "PASSWORD", length = 20, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private String password;

    /** 所属电厂 */
    @Column(name = "FK_POWER_PLANT", length = 11, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private Integer powerPlantId;

    /** 角色划分 0为管理员、1为发布人员*/
    @Column(name = "ROLE", length = 1, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private Integer role;

    /** 用户所在报警组，用逗号分隔 */
    @Column(name = "GROUP_Admin_Id")
    private String groupIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getPowerPlantId() {
        return powerPlantId;
    }

    public void setPowerPlantId(Integer powerPlantId) {
        this.powerPlantId = powerPlantId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }
}
