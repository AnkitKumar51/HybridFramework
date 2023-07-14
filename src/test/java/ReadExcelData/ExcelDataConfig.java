package ReadExcelData;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataConfig {

    XSSFWorkbook wb;
    XSSFSheet sheet;
    XSSFRow rows;
    XSSFCell cell;

    public ExcelDataConfig(String excelPath) throws IOException {
        try {
            File src = new File(excelPath);
            FileInputStream file = new FileInputStream(src);
            wb = new XSSFWorkbook(file);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row,int column){
        sheet = wb.getSheetAt(sheetNumber);
        rows = sheet.getRow(row);
        cell = rows.getCell(column);
        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try{
            data = dataFormatter.formatCellValue(cell);
        }
        catch (Exception e) {
            data="";
        }
        return data;
    }

    public int getRowCount(int sheetIndex){
        sheet = wb.getSheetAt(sheetIndex);
        return sheet.getLastRowNum();
    }

    public int getCellCount(int sheetIndex, int rowNum){
        rows = wb.getSheetAt(sheetIndex).getRow(rowNum);
        return rows.getLastCellNum();
    }
}