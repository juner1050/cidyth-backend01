package com.hyzs.cidyth.common.utils.excel;

/**
 * Excel相关参数的一个实体类
 * Created by pengzk on 2017/5/12.
 */
public class ExcelBean {
    private int row = -1; //excel的行
    private int column = -1; //excel的列

    public ExcelBean(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
