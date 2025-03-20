import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTestDataProvider {

    @DataProvider(name = "ExcelData")
    public Object[][] readExcelData() throws IOException {
        String filePath = "C:\\Users\\IB\\Desktop\\DEV  PROJECTs\\TestNG\\src\\test\\java\\ressources/testdata.xlsx"; // Chemin vers le fichier Excel
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0); // Première feuille

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        //Itération sur les lignes et colonnes pour stocker les données dans un tableau Object[][].
        for (int i = 1; i < rowCount; i++) { // Ignorer l'en-tête
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        return data;
    }
}
