package org.classsix.ofms.domin;

import javax.persistence.*;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
@Entity
public class Indicators {
    /** 用户主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_INDICATORS_ID")
    private Integer id;

    /** 指标名称*/
    @Column(name = "INDICATOR_NAME", length = 50, nullable = false)
    private String indicatorsName;

    /** 指标数值*/
    @Column(name = "INDICATOR_NUM", length = 11, nullable = false)
    private Integer indicatorsNum;

    /** 指标单位，0为立方米/秒(主蒸汽流量)，1为摄氏度(主汽温度)，2为千焦耳(总燃料量)*/
    @Column(name = "INDICATOR_TYPE", length = 11, nullable = false)
    private Integer indicatorsType;

    /** 指标所属电厂*/
    @Column(name = "FK_POWER_PLANT", length = 11, nullable = false)
    private Integer powerPlantId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndicatorsName() {
        return indicatorsName;
    }

    public void setIndicatorsName(String indicatorsName) {
        this.indicatorsName = indicatorsName;
    }

    public Integer getIndicatorsNum() {
        return indicatorsNum;
    }

    public void setIndicatorsNum(Integer indicatorsNum) {
        this.indicatorsNum = indicatorsNum;
    }

    public Integer getIndicatorsType() {
        return indicatorsType;
    }

    public void setIndicatorsType(Integer indicatorsType) {
        this.indicatorsType = indicatorsType;
    }

    public Integer getPowerPlantId() {
        return powerPlantId;
    }

    public void setPowerPlantId(Integer powerPlantId) {
        this.powerPlantId = powerPlantId;
    }
}
