package org.classsix.ofms.domin.vo;

import org.classsix.ofms.domin.User;

import java.util.List;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
public class WarningGroupVO {

    private Integer id;

    /** 组名 */
    private String groupName;

    /** 报警组所包含管理员id，用逗号分隔 */
    private List<User> groupAdminList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getGroupAdminList() {
        return groupAdminList;
    }

    public void setGroupAdminList(List<User> groupAdminList) {
        this.groupAdminList = groupAdminList;
    }
}
