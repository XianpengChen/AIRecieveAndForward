package com.xchen.airecieveandforward.domain.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 28609
 */
@Data
@TableName("sys_user")
public class SysUer {
    Long ID;
    String PUB_USER_ID;
    String ACCOUNT;
    String NAME;
    String PASSWORD;
    String STATUS;
    String USER_CODE;
    int GRADE;
    String GENDER;
    Date BIRTHDAY;
    String IDENTITY_NUM;
    String PHONE;
    String MOBILE;
    String EMAIL;
    String POSITION;
    String TYPE_CODE;
    String REGION_CODE;
    String REGION_NAME;
    String ORG_CODE;
    String ORG_NAME;
    String ORG_SHORT_CODE;
    int USER_TYPE;
    int SORT_ORDER;
    Long APP_ID;
    String BSP_ID;
    String CREATOR;
    Timestamp CREATE_TIME;
    String UPDATOR;
    Timestamp UPDATE_TIME;
    Long tacs_id;
    String ROLE_CODE;
}
