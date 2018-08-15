package org.classsix.ofms.domin;

import org.classsix.ofms.domin.validgroup.UserGroup;

import javax.persistence.*;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
@Entity
@Table(name = "POWER_PLANT")
public class PowerPlant {
    /** 主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_PLANT_ID")
    private Integer id;

    /** 发电厂名称 */
    @Column(name = "PLANT_NAME", length = 50, nullable = false,unique = true)
    private String plantName;

    /** 发电厂类型,0为火力，1为水力，2为核能，3为风力*/
    @Column(name = "PLANT_TYPE", length = 2, nullable = false)
    private Integer plantType;

    /** 发电厂编号,编号为6位数，100000+id*/
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Integer getPlantType() {
        return plantType;
    }

    public void setPlantType(Integer plantType) {
        this.plantType = plantType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
