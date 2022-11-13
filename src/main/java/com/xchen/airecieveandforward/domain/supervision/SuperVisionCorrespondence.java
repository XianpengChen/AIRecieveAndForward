package com.xchen.airecieveandforward.domain.supervision;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 28609
 */
@TableName("supervision_correspondence")
@Data
public class SuperVisionCorrespondence {
    @TableField("organID")
    private Long organId;
    @TableField("organName")
    private String organName;
    @TableField("userID")
    private int userId;
    @TableField("userName")
    private String userName;
    @TableField("userPhone")
    private String userPhone;

    public SuperVisionCorrespondence(Long organId, String organName, int userId, String userName, String userPhone) {
        this.organId = organId;
        this.organName = organName;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
    }
    public SuperVisionCorrespondence() {

    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "SuperVisionCorrespondence{" +
                "organId=" + organId +
                ", organName='" + organName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }

    public static List<String> getColumnName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("organID");
        list.add("organName");
        list.add("userID");
        list.add("userName");
        list.add("userPhone");
        return list;
    }
}
