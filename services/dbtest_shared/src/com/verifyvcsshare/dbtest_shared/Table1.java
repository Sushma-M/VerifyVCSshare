/*Copyright (c) 2015-2016 vcs2.com All Rights Reserved.
 This software is the confidential and proprietary information of vcs2.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcs2.com*/
package com.verifyvcsshare.dbtest_shared;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table1 generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`TABLE1`")
public class Table1 implements Serializable {

    private int id;
    private String column2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "`COLUMN2`", nullable = true, length = 255)
    public String getColumn2() {
        return this.column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table1)) return false;
        final Table1 table1 = (Table1) o;
        return Objects.equals(getId(), table1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
