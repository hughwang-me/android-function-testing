package com.uwjx.function.util;
import android.content.Context;
import android.widget.Toast;

import com.uwjx.function.domain.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtils {
    private static WritableFont arial14font = null;

    private static WritableCellFormat arial14format = null;
    private static WritableFont arial10font = null;
    private static WritableCellFormat arial10format = null;
    private static WritableFont arial12font = null;
    private static WritableCellFormat arial12format = null;
    private final static String UTF8_ENCODING = "UTF-8";

    /**
     * 单元格的格式设置 字体大小 颜色 对齐方式、背景颜色等...
     */
    private static void format() {
        try {
            arial14font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(jxl.format.Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);

            arial10font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial10format.setBackground(Colour.GRAY_25);

            arial12font = new WritableFont(WritableFont.ARIAL, 10);
            arial12format = new WritableCellFormat(arial12font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial12format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public static void generateExcel(File file , String sheetName , List<String> headers , List<Person> data){
        format();
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(file);
            //设置表格的名字
            WritableSheet sheet = workbook.createSheet(sheetName, 0);
            for (int i = 0; i < headers.size(); i++) {
                sheet.addCell(new Label(i, 0, headers.get(i), arial10format));
            }
            for (int i = 0; i < data.size(); i++) {
                Person projectBean =  data.get(i);
                List<String> list = new ArrayList<>();
                list.add(projectBean.getName()+"-" + i);
                list.add(projectBean.getAge()+"-" + i);
                list.add(projectBean.isBody()+"-" + i);

                for (int j = 0; j < list.size(); j++) {
                    sheet.addCell(new Label(j, i+1, list.get(j), arial12format));
                }
            }
            sheet.setRowView(0, 340);
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initExcel(File file, String[] colName , String sheetName) {
        format();
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(file);
            //设置表格的名字
            WritableSheet sheet = workbook.createSheet(sheetName, 0);
            //创建标题栏
//            sheet.addCell((WritableCell) new Label(0, 0, file.getName(), arial14format));
            for (int col = 0; col < colName.length; col++) {
                sheet.addCell(new Label(col, 0, colName[col], arial10format));
            }
            //设置行高
            sheet.setRowView(0, 340);
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeObjListToExcel(List<Person> objList, File file, Context c) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(file);
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(file, workbook);
                WritableSheet sheet = writebook.getSheet(0);

                for (int j = 0; j < objList.size(); j++) {
                    Person projectBean =  objList.get(j);
                    List<String> list = new ArrayList<>();
                    list.add(projectBean.getName());
                    list.add(projectBean.getAge()+"");
                    list.add(projectBean.isBody()+"");

                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, j+ 1, list.get(i), arial12format));
                        if (list.get(i).length() <= 4) {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length()+ 8);
                        } else {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length()+5);
                        }
                    }
                    //设置行高
                    sheet.setRowView(j+ 1, 350);
                }

                writebook.write();
                Toast.makeText(c, "导出Excel成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
