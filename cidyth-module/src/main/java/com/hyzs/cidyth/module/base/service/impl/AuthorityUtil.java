package com.hyzs.cidyth.module.base.service.impl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 1 on 2018/11/6.
 */
public class AuthorityUtil {

    private static List<String> lsTempCityAuthroty = Lists.newArrayList("057664");

    public static boolean check(String account){
        return lsTempCityAuthroty.contains(account);
    }
}
