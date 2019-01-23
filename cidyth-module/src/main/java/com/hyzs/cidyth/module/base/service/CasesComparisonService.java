package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;

/**
 * Created by 1 on 2018/10/12.
 */
public interface CasesComparisonService {

    /**
     * 批量插入数据
     */
    void insert(String ajbh);
    /**
     * 批量插入数据
     */
    void insert(Integer ajid, String ajbh, Dept sendDept, Dept receiveDept, User receiveUser);

}
