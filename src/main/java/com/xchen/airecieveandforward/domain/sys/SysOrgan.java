package com.xchen.airecieveandforward.domain.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 28609
 */
@Data
@TableName("sys_organ")
public class SysOrgan {
    Long ID;
    String ORGAN_ID;
    String CODE;
    String NAME;
    String PINYIN;
    String ICON;
    String ORGAN_TYPE;
    String SHORT_NAME;
    String REGION_CODE;
    String REGION_NAME;
    int SORT_ORDER;
    String REMARK;
    Long APP_ID;
    int CHILDS;
    String IS_BUSINESS;
    String IS_HALL;
    String ORGAN_LEVEL;
    String TYPE;
    String SHORT_CODE;
    String PARENT_CODE;
    String ADDRESS;
    String PHONE;
    String ZIP_CODE;
    String EMAIL;
    String LEADER;
    String STATUS;
    String BSP_ID;
    String TYSHXYMD;
    String CREATOR;
    Timestamp CREATE_TIME;
    String UPDATOR;
    Timestamp UPDATE_TIME;
}
