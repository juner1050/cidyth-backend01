package com.hyzs.cidyth.common.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;

/**
 * Author pengzk
 * Created Time : 2017/5/10
 * Description 读取excel工具类
 */
public class ExcelHelper {
	private static ExcelHelper singleton=null;
    private ExcelHelper() {

    }
    public static ExcelHelper getInstance(){
    	synchronized(ExcelHelper.class){
    		if(singleton==null){
    			singleton = new ExcelHelper();
    		}
    		return singleton;
    	}
    }
    /**
     * 判断日期格式和范围
     */
    String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

    private Pattern pattern = Pattern.compile(rexp);


    /**
     * 读取excel表头
     * @param file 文件
     * @return 标题列表
     * @throws Exception 异常
     */
    public String[] readExcelTitle(File file, int sheetIndex, int titleIndex) throws Exception {

        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = sheet.getRow(titleIndex);
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param file 文件
     * @param sheetIndex sheet
     * @param contentStartRowIndex 内容的起始行
     * @return Map
     * @throws Exception 异常
     */
    public Map<Integer, List<String>> readExcelContent(File file, int sheetIndex, int contentStartRowIndex) throws Exception{
        Workbook wb = WorkbookFactory.create(file);
        Map<Integer, List<String>> contentMap = new HashMap<>();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        //得到内容的总列数
        //Row row = sheet.getRow(rowIndex-1);
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        List<String> mRowStrList;
        //Excel读取，实际是从0开始，因此需要将指定的行-1
        contentStartRowIndex = contentStartRowIndex-1;
        for (int i = contentStartRowIndex; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            mRowStrList = new ArrayList<>();
            while (j < colNum) {
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                if(null != row) {
                    if(row.getCell(j)!=null){
                        try{
                            mRowStrList.add(getCellFormatValue(row.getCell(j)).trim());
                        }catch (Exception e){
                            throw new RuntimeException("第"+(i+1)+"第" + (j+1) +"列单元格输入异常："+e.getMessage());
                        }
                    }else{
                        mRowStrList.add("");
                    }
                }
                j++;
            }
            contentMap.put(i, mRowStrList);
        }
        return contentMap;
    }


    /**
     * 读取Excel数据内容
     * @param file 文件
     * @param sheetIndex sheet
     * @param contentStartRowIndex 内容的起始行
     * @param colNum 当前excel的Row，即有多少列
     * @return Map
     * @throws Exception 异常
     */
    public Map<Integer, List<String>> readExcelContent2(File file, int sheetIndex, int contentStartRowIndex,int rowNum, int colNum) throws Exception{
        Workbook wb = WorkbookFactory.create(file);
        Map<Integer, List<String>> contentMap = new HashMap<>();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row;
        List<String> mRowStrList;
        for (int i = contentStartRowIndex; i < contentStartRowIndex + rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            mRowStrList = new ArrayList<>();
            while (j < colNum) {
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                if(null != row&& null != row.getCell(j)) {
                    mRowStrList.add(getCellFormatValue(row.getCell(j)).trim());
                }
                j++;
            }
            contentMap.put(i, mRowStrList);
        }
        return contentMap;
    }




    /**
     * 读取指定的行、列的数据
     * note:如果要读的数据是在合并行或者列，则rowNum、columnNum均为合并行列的开始数；
     * @param file 文件名
     * @param sheetIndex sheet
     * @param list 指定行和列的一个列表数据
     * @return 读出来的内容
     * @throws Exception 异常
     */
    public List<String> readAppointListExcelContent(File file, int sheetIndex, List<ExcelBean> list)throws Exception{

        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(sheetIndex);
        List<String> stringList = new ArrayList<>();
        if(null != list && list.size()>0){
            for(ExcelBean bean : list){
                if(bean.getRow()>0 && bean.getColumn()>0){
                    Row row = sheet.getRow(bean.getRow());
                    stringList.add(getCellFormatValue(row.getCell(bean.getColumn())));
                }
            }

        }
        return stringList;
    }

    /**
     * 读取指定的行、列的数据
     * @param file 文件名
     * @param sheetIndex sheet
     * @param rowNum 哪一列
     * @param columnNum 哪一行
     * @return 读出来的内容
     * @throws Exception 异常
     */
    public String readAppointExcelContent(File file, int sheetIndex, int rowNum, int columnNum)throws Exception{

        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowNum);
        return getCellFormatValue(row.getCell(columnNum));

    }

    private String getCellFormatValue(Cell cell) throws Exception{
        String cellValue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {
                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formatData = sdf.format(date);
                        Date mDate = sdf.parse(formatData);
                        cellValue = String.valueOf(mDate.getTime());

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        if(cellValue.endsWith(".0") && !cellValue.contains("E")){
                            cellValue =  cellValue.replace(".0", "");
                        }else if(cellValue.contains("E")){
                            cellValue =  cellValue.split("E")[0].replace(".", "");
                        }
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case STRING:
                    // 取得当前的Cell字符串
                    cellValue = cell.getRichStringCellValue().getString().trim();
                    break;
                // 默认的Cell值
                default:
                    cellValue = " ";
            }
        } else {
            cellValue = "";
        }
        return cellValue;

    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(Cell cell) {
        String strCell;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                strCell = cell.getStringCellValue();
                break;
            case NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("")) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(Cell cell) {
        String result = "";
        try {
            CellType cellType = cell.getCellTypeEnum();
            if (cellType == CellType.NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == CellType.STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == CellType.BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * pengzk 2017-5-15
     * 读取Excel数据内容，并指定列规则
     * @param file 文件
     * @param sheetIndex sheet
     * @param contentStartRowIndex 内容的起始行
     * @param rowIndex 哪一行能确定当前excel的Row，即有多少列
     * @param ruleMap excel列数据规则
     * @return ExcelResult 读取结果
     * @throws Exception 异常
     */
    public ExcelResult readExcelContent(File file, int sheetIndex, int contentStartRowIndex, int rowIndex, Map<Integer, ExcelCellType> ruleMap) throws Exception{
        if(null == ruleMap){
            throw new NullPointerException();
        }

        ExcelResult result = new ExcelResult();
        Workbook wb = WorkbookFactory.create(file);
        Map<Integer, List<String>> contentMap = new HashMap<>();
        Map<Integer, List<String>> errorContentMap = new HashMap<>();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        //得到内容的总列数
        Row row = sheet.getRow(rowIndex);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        List<String> mRowStrList;
        List<String> mErrorRowStrList;
        for (int i = contentStartRowIndex; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            mRowStrList = new ArrayList<>();
            mErrorRowStrList = new ArrayList<>();
            while (j < colNum) {
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean

                String cellValue = getCellFormatValue(row.getCell(j)).trim();
                mRowStrList.add(cellValue);

                if(ruleMap.containsKey(j)){
                    ExcelCellType type = ruleMap.get(j);
                    switch (type){
                        case NUMBER:{
                            if(!org.codehaus.plexus.util.StringUtils.isEmpty(cellValue) && !org.codehaus.plexus.util.StringUtils.isNumeric(cellValue)){
                                mErrorRowStrList.add("第"+(i+1)+"行,"+"第"+ (j+1) +"列，应该为数字，请核对填写标准");
                            }
                        }
                        break;
                        case STRING:{

                        }
                        break;
                        case DATE:{
                            String newCellValue = cellValue.replace("//", "-");
                            if(!pattern.matcher(newCellValue).matches()){
                                mErrorRowStrList.add("第"+(i+1)+"行，"+"第"+ (j+1) +"列，应该为日期,输入的日期格式不对，请核对填写标准");
                            }
                        }
                        break;

                        case NOT_EMPTY:{
                            if(org.codehaus.plexus.util.StringUtils.isEmpty(cellValue)){
                                mErrorRowStrList.add("第"+(i+1)+"行，"+"第"+ (j+1) +"列，内容不能为空，请核对填写标准");
                            }
                        }
                        break;
                    }
                }
                if(mErrorRowStrList.size()>0) {
                    errorContentMap.put(i, mErrorRowStrList);
                }
                j++;
            }
            contentMap.put(i, mRowStrList);
        }

        if(errorContentMap.size()>0){ //excel中有错误信息
            result.setCode(-1);
            result.setResult(errorContentMap);
        }else{
            result.setCode(0);
            result.setResult(contentMap);
        }
        return result;
    }


    /**
     * 导出Excel（Excel不存在，且需要往Excel中写入标题和内容）
     * @param filePath 导出完整路径的文件名 例如 "D:/workbook.xls"
     * @param titleIndex 标题行
     * @param titleList 标题内容
     * @param contentList 内容
     */
    public void writeExcel(String filePath, int titleIndex, List<String> titleList, List<List<Object>> contentList){

        if(StringUtils.isEmpty(filePath)){
            throw new UnsupportedOperationException();
        }

        if(titleIndex<0){
            throw new UnsupportedOperationException();
        }

        if(null == titleList &&  null ==contentList){
            throw new UnsupportedOperationException();
        }

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet1");
        //创建Excel title
        Row row = sheet.createRow(titleIndex);
        if(null != titleList && titleList.size()>0){
            int size = titleList.size();
            for(int i=0; i<size; i++){
                row.createCell(i).setCellValue(titleList.get(i));
            }
        }

        writeContent(filePath, wb, sheet, titleIndex, contentList);

    }

    /**
     * 导出Excel(主要针对那种Excel已经存在，并且标题已经存在（或不需要标题），只是往excel中填充内容)
     * @param filePath 导出完整路径的文件名 例如 "D:/workbook.xls"
     * @param index 哪一行开始写入内容
     * @param contentList 内容
     */
    public void writeExcel(String filePath, int index, List<List<Object>> contentList) throws Exception{

        if(StringUtils.isEmpty(filePath)){
            throw new UnsupportedOperationException();
        }

        if(index<0){
            throw new UnsupportedOperationException();
        }

        FileInputStream fs = new FileInputStream(filePath);
        POIFSFileSystem ps = new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        Workbook wb = new HSSFWorkbook(ps);
        Sheet sheet = wb.getSheetAt(0);
        writeContent(filePath, wb, sheet, index, contentList);

    }

    /**
     * Excel中写入内容
     * @param filePath 文件路径
     * @param wb Workbook
     * @param sheet Sheet
     * @param index 写入内容的起始行
     * @param list 内容list
     */
    private void writeContent(String filePath, Workbook wb, Sheet sheet, int index, List<List<Object>> list){


//        CellStyle style = wb.createCellStyle();
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderRight(BorderStyle.THIN);
//        style.setRightBorderColor(IndexedColors.BLACK.getIndex());


        //创建Excel内容
        if(null != list && list.size()>0){
            int size = list.size();
            for(int i= 0; i< size; i++) {
                Row contentRow = sheet.createRow(i + index);
                List<Object> curLineList = list.get(i);
                if(null != curLineList && curLineList.size()>0){
                    int lineSize = curLineList.size();
                    for(int j=0; j<lineSize; j++){
                        if(null != curLineList.get(j)) {
                            contentRow.createCell(j).setCellValue(curLineList.get(j).toString());
                        }else{
                            contentRow.createCell(j).setCellValue("");
                        }
                    }
                }
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filePath);
            fileOut.flush();
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != fileOut) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
