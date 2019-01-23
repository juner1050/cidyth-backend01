package com.hyzs.cidyth.common.utils.excel;

import java.util.List;
import java.util.Map;

/**
 *  pengzk 2017-5-15
 * 用于excel的读取返回；
 * code:0 返回所有读取内容； code:-1 返回所有错误行列的信息
 */
public class ExcelResult {
    private int code;
    private Map<Integer, List<String>> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<Integer, List<String>> getResult() {
        return result;
    }

    public void setResult(Map<Integer, List<String>> result) {
        this.result = result;
    }
}
