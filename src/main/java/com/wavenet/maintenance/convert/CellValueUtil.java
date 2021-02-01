package com.wavenet.maintenance.convert;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @ClassName CellValueUtil
 * @Description TODO
 * @Date 2020/6/23 15:19
 * @Version 1.0
 */
public class CellValueUtil {

    public static String getValue(XSSFCell xssfCell){
        if (xssfCell == null){
            return null;
        }
        if (xssfCell.getCellType()==XSSFCell.CELL_TYPE_BOOLEAN){
            try {
                return String.valueOf(xssfCell.getBooleanCellValue()).equals("")?null:String.valueOf(xssfCell.getBooleanCellValue());
            }catch (Exception e){
                return String.valueOf((int)xssfCell.getNumericCellValue()).equals("")?null:String.valueOf((int)xssfCell.getNumericCellValue());
            }
        }else if (xssfCell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
            return String.valueOf((int)xssfCell.getNumericCellValue()).equals("")?null:String.valueOf((int)xssfCell.getNumericCellValue());
        }else if (xssfCell.getCellType()==XSSFCell.CELL_TYPE_FORMULA){
            try {
                return String.valueOf((int)xssfCell.getNumericCellValue()).equals("")?null:String.valueOf((int)xssfCell.getNumericCellValue());
            }catch (Exception e){
                return String.valueOf(xssfCell.getBooleanCellValue()).equals("")?null:String.valueOf(xssfCell.getBooleanCellValue());
            }
        } else {
            return String.valueOf(xssfCell.getStringCellValue()).equals("")?null:String.valueOf(xssfCell.getStringCellValue());
        }
    }

    //获取单元格各类型值，返回字符串类型
    public static String getCellValueByCell(XSSFCell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return null;
        }
        String cellValue = "";
        int cellType=cell.getCellType();

        if(cellType==XSSFCell.CELL_TYPE_FORMULA){ //表达式类型
            try {
                return String.valueOf((int)cell.getNumericCellValue()).equals("")?null:String.valueOf((int)cell.getNumericCellValue());
            }catch (Exception e){
                return String.valueOf(cell.getBooleanCellValue()).equals("")?null:String.valueOf(cell.getBooleanCellValue());
            }
        }

        switch (cellType) {
            case XSSFCell.CELL_TYPE_STRING: //字符串类型
                cellValue= cell.getStringCellValue().trim();
                cellValue= StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                } else {  //否
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }

    public static String getValueH(HSSFCell xssfCell){
        if (xssfCell == null){
            return null;
        }
        if (xssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            return String.valueOf(xssfCell.getBooleanCellValue()).equals("")?null:String.valueOf(xssfCell.getBooleanCellValue());
        }else if (xssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            return String.valueOf((int)xssfCell.getNumericCellValue()).equals("")?null:String.valueOf((int)xssfCell.getNumericCellValue());
        }else {
            return String.valueOf(xssfCell.getStringCellValue()).equals("")?null:String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
