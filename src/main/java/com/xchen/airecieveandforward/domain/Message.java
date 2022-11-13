package com.xchen.airecieveandforward.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    private String Id;
    private String ChnId;
    private String ChnName;
    private String RuleId;
    private String RuleName;
    private String Time;
    private String RuleType;
    private String EventType;
    private String Text;
    private String Pic1_base64;
    private String Pic2_base64;
    private String Pic3_base64;
    private String ObjType;
    private String ObjScore;
    private String ObjX;
    private String ObjY;
    private String ObjW;
    private String ObjH;
    private String AreaNo;
    private String ObjTypeName;
    private String HostCode;
    public static List<String> getColumnName() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("ChnId");
        columnNames.add("ChnName");
        columnNames.add("RuleId");
        columnNames.add("RuleName");
        columnNames.add("Time");
        columnNames.add("RuleType");
        columnNames.add("EventType");
        columnNames.add("Text");
        columnNames.add("Pic1_base64");
        columnNames.add("Pic2_base64");
        columnNames.add("Pic3_base64");
        columnNames.add("ObjType");
        columnNames.add("ObjScore");
        columnNames.add("ObjX");
        columnNames.add("ObjY");
        columnNames.add("ObjW");
        columnNames.add("ObjH");
        columnNames.add("AreaNo");
        columnNames.add("ObjTypeName");
        columnNames.add("HostCode");
        return columnNames;
    }
}
