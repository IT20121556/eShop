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

import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.dto.productDTO;

public class CustomerExcelExporter {
	 private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<customerDTO> listUsers;
	     
	    public CustomerExcelExporter(List<customerDTO> listUsers) {
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
	        createCell(row, 0, "Customer Id", style);      
	        createCell(row, 1, "First Name", style);       
	        createCell(row, 2, "Last Name", style);    
	        createCell(row, 3, "Email", style);
	        createCell(row, 4, "Phone", style);
	        createCell(row, 5, "Address", style);
	        createCell(row, 6, "City", style);
	        createCell(row, 7, "Country", style);
	        createCell(row, 8, "Province", style);
	        createCell(row, 9, "PostalCode", style);
	        
	         
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
	                 
	        for (customerDTO user : listUsers) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	        	
	            createCell(row, columnCount++, user.getCustomerId(), style);
	            createCell(row, columnCount++, user.getfName(), style);
	            createCell(row, columnCount++, user.getlName(), style);
	            createCell(row, columnCount++, user.getEmail(), style);
	            createCell(row, columnCount++, user.getPhone(), style);
	            createCell(row, columnCount++, user.getAddress(), style);
	            createCell(row, columnCount++, user.getCity(), style);
	            createCell(row, columnCount++, user.getCountry(), style);
	            createCell(row, columnCount++, user.getProvince(), style);
	            createCell(row, columnCount++, user.getPostalCode(), style);
	            
	             
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
