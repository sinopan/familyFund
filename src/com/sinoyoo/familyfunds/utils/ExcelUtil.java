package com.sinoyoo.familyfunds.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelUtil {

    /**
     * ����Excel
     * @param sheetName sheet����
     * @param title ����
     * @param values ����
     * @param wb HSSFWorkbook����
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
        HSSFRow row = sheet.createRow(0);

        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
        HSSFCellStyle style = wb.createCellStyle();
        // style.setAlignment(HSSFCellStyle.); // ����һ�����и�ʽ

        //�����ж���
        HSSFCell cell = null;
        
        /*
         * ��ʽ
         */
       /* sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 3000);*/
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(6, 7000);
        
        //����Excel�еı߿�(��ͷ�ı߿�)
        //style.setFillBackgroundColor((short) 346729);
        //style.setFillForegroundColor((short) 111111);
        
        
        //��������

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 30); // ����߶�
        font.setFontName("����"); // ����
        style.setFont(font);
        
        //����Excel�еı���
        
        //��������
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            
            style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
            style.setFillBackgroundColor(IndexedColors.AQUA.index);
            cell.setCellStyle(style);
        }

        //��������
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //�����ݰ�˳�򸳸���Ӧ���ж���
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        
        return wb;
    }
}