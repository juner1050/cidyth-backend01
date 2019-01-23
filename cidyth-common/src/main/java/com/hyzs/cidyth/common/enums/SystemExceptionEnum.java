package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统异常枚举
 * Created by 1 on 2018/9/13.
 */
public enum SystemExceptionEnum {

    SUCCESS("200","成功"),

    LOGIN_TIMEOUT("1000","登录超时"),
    LOGIN_ERROR("1001","用户名或密码错误"),
    GET_USER_FAILED("1001","获取用户失败"),
    GET_DEPTFAILED("1001","获取用户机构失败"),

    ILLEGAL_PARAMETER("2000","参数校验失败"),
    NULL_DATA("2001","数据为空"),

    REQUEST_FAILED("3000","系统请求失败")

    ;



    private final String code;
    private final String descp;

    SystemExceptionEnum(String code, String desc){
        this.code = code;
        this.descp = desc;
    }
    /**
     * 候选的名字
     * @return
     */
    public static List<String> names() {
        List<String> names = new ArrayList<String>();
        for (YesNoEnum item : YesNoEnum.values()) {
            names.add(item.name());
        }
        return names;
    }
    /**
     * 候选的代码
     * @return
     */
    public static List<String> codes() {
        List<String> values = new ArrayList<String>();
        for (YesNoEnum item : YesNoEnum.values()) {
            values.add(item.code());
        }
        return values;
    }
    /**
     * 代码
     * @return
     */
    public String code() {
        return code;
    }
    /**
     * 描述
     * @return
     */
    public String descp() {
        return descp;
    }
    /**
     * 所有的枚举
     * @return
     */
    public static List<YesNoEnum> candidates() {
        return Lists.newArrayList(YesNoEnum.values());
    }
    /**
     * 根据名字找枚举
     * @param name
     * @return
     */
    public static YesNoEnum ofName(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (YesNoEnum item : YesNoEnum.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }
    /**
     * 根据代码找枚举
     * @param value
     * @return
     */
    public static YesNoEnum ofCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (YesNoEnum item : YesNoEnum.values()) {
                if (item.code().equals(code)) {
                    return item;
                }
            }
        }
        return null;
    }

}
