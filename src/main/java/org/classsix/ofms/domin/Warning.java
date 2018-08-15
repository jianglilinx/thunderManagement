package org.classsix.ofms.domin;

import javax.persistence.*;

/**
 * Created by Jiang on 2018/6/9.
 * Coding
 */
@Entity
@Table(name = "WARNING")
public class Warning {
    /** 主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_WARNING_ID")
    private Integer id;

    /** 报警名 */
    @Column(name = "WARNING_NAME",length = 50, nullable = false)
    private String warningName;

    /** 最大值 */
    @Column(name = "WARNING_MAX",length = 50, nullable = false)
    private Integer maxValue;

    /** 最新值 */
    @Column(name = "WARNING_MIN",length = 50, nullable = false)
    private Integer minValue;

    /** 指标单位，数值表示暂定*/
    @Column(name = "WARNING_TYPE", length = 11, nullable = false)
    private Integer warningType;

    /** 报警组外键*/
    @Column(name = "FK_GROUP_ID", length = 11)
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarningName() {
        return warningName;
    }

    public void setWarningName(String warningName) {
        this.warningName = warningName;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getWarningType() {
        return warningType;
    }

    public void setWarningType(Integer warningType) {
        this.warningType = warningType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
