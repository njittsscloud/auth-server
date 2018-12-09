package com.tss.authserver.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author MQG
 * @date 2018/1209
 * */
public enum RoleTypeEnum {

    ADMIN(1, "管理员"),
    SYY(2, "实验员"),
    TEACHER(3, "教师"),
    STUDENT(4, "学生");

    private int id;
    private String desc;

    private static Map<Integer, RoleTypeEnum> typeMap =
            Arrays.stream(RoleTypeEnum.values()).collect(Collectors.toMap(RoleTypeEnum::getId, e -> e));

    RoleTypeEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescById(int id) {
        RoleTypeEnum roleTypeEnum = typeMap.get(id);
        if (roleTypeEnum != null) {
            return roleTypeEnum.getDesc();
        }
        return "";
    }

    public static RoleTypeEnum getById(int id) {
        return typeMap.get(id);
    }

}
