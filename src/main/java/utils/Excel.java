package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Excel {
    public static void convertExcelToCSV(InputStream excelInputStream, File csvOutputFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(excelInputStream);
        DataFormatter dataFormatter = new DataFormatter();

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvOutputFile))) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    StringBuilder rowString = new StringBuilder();
                    for (Cell cell : row) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        rowString.append(cellValue).append(",");
                    }
                    // Remove the last comma and add a new line
                    if (rowString.length() > 0) {
                        rowString.setLength(rowString.length() - 1);
                    }
                    writer.println(rowString.toString());
                }
            }
        }
    }
}
