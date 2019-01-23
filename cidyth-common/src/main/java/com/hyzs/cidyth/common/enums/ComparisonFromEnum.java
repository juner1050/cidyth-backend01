package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/10/24.
 */
public enum ComparisonFromEnum {
    DNA("0", "DNA比中"), FINGER("1", "指纹比中");
    private final String code;
    private final String descp;

    ComparisonFromEnum(String code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    /**
     * 候选的名字
     * @return
     */
    public static List<String> names() {
        List<String> names = new ArrayList<String>();
        for (ComparisonFromEnum item : ComparisonFromEnum.values()) {
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
        for (ComparisonFromEnum item : ComparisonFromEnum.values()) {
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
    public static List<ComparisonFromEnum> candidates() {
        return Lists.newArrayList(ComparisonFromEnum.values());
    }
    /**
     * 根据名字找枚举
     * @param name
     * @return
     */
    public static ComparisonFromEnum ofName(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (ComparisonFromEnum item : ComparisonFromEnum.values()) {
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
    public static ComparisonFromEnum ofCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (ComparisonFromEnum item : ComparisonFromEnum.values()) {
                if (item.code().equals(code)) {
                    return item;
                }
            }
        }
        return null;
    }

}
