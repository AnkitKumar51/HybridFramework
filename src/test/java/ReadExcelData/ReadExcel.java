package ReadExcelData;

import java.io.IOException;

public class ReadExcel {
    public static void main(String[] args) throws IOException {
        String excelSheetPath = System.getProperty("user.dir");
        ExcelDataConfig excelDataConfig = new ExcelDataConfig(excelSheetPath + ".//TestData//tealPDTestData.xlsx");
        System.out.println(excelDataConfig.getData(1, 0, 0));
        System.out.println(excelDataConfig.getData(1, 1, 0));
        System.out.println(excelDataConfig.getData(1, 0, 1));
        System.out.println(excelDataConfig.getData(1, 1, 1));
        System.out.println(excelDataConfig.getData(1, 0, 2));
        System.out.println(excelDataConfig.getData(1, 1, 2));

    }
}
