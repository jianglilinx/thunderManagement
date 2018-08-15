package org.classsix.ofms.domin;

import javax.persistence.*;

/**
 * Created by Jiang on 2018/5/28.
 * Coding
 */
@Entity
@Table(name = "WARNING_GROUP")
public class WarningGroup {
    /** 主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_WARNING_GROUP_ID")
    private Integer id;

    /** 组名 */
    @Column(name = "GROUP_NAME",unique = true)
    private String groupName;

    /** 报警组所包含管理员id，用逗号分隔 */
    @Column(name = "GROUP_Admin_Id")
    private String groupAdminId;

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

    public String getGroupAdminId() {
        return groupAdminId;
    }

    public void setGroupAdminId(String groupAdminId) {
        this.groupAdminId = groupAdminId;
    }
}
