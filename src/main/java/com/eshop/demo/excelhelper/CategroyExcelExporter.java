package com.eshop.demo.excelhelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eshop.demo.dto.categoryDTO;
import com.eshop.demo.dto.customerDTO;

public class CategroyExcelExporter {
	private XSSFWorkbook workbook;
 
    private XSSFSheet sheet;
    private List<categoryDTO> listUsers;
     
    public CategroyExcelExporter(List<categoryDTO> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
        			
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Category ID", style);      
        createCell(row, 1, "Category Name", style);       
        createCell(row, 2, "Category Alias", style);    
        createCell(row, 3, "Enabled", style);
       
        
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (categoryDTO user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            String statis;
        	if(user.getEnabled()==1)
        	{
        		statis="Enabled";
        	}
        	else
        	{
        		statis="Disabled";
        	}
            createCell(row, columnCount++, user.getCategory_id(), style);
            createCell(row, columnCount++, user.getCategory_name(), style);
            createCell(row, columnCount++, user.getAlias(), style);
            createCell(row, columnCount++, statis, style);
           
            
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
