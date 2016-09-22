import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by My on 6.9.2016 г..
 */
public class CreateFile {

    public static void create () {
        XSSFWorkbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        XSSFSheet sheet = wb.createSheet("new sheet");
        XSSFRow row;

        FileOutputStream file ;
        try {
            file = new FileOutputStream(new File("bank_new.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        row = sheet.createRow(0);
        row.createCell(0).setCellValue(createHelper.createRichTextString("BUINBGSF"));
        row.createCell(1).setCellValue(createHelper.createRichTextString("ALLIANZ BANK BULGARIA AD"));
        row = sheet.createRow(1);
        row.createCell(0).setCellValue(createHelper.createRichTextString("BSBGBGSF"));
        row.createCell(1).setCellValue(createHelper.createRichTextString("BANKSERVICE AD"));
        row = sheet.createRow(2);
        row.createCell(0).setCellValue(createHelper.createRichTextString("BNPABGSF"));
        row.createCell(1).setCellValue(createHelper.createRichTextString("BNP PARIBAS S.A."));


        try {
            wb.write(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
