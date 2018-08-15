package org.classsix.ofms.domin.common;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

/**
 * 用户类
 */
@MappedSuperclass
public class BasePerson extends BaseEntity{
    private static final long serialVersionUID = 3548821545773064641L;

    /** 姓名 */
    @Column(name = "NAME", length = 50,nullable = true)
    private String realName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
