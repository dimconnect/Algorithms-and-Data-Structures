
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;


/**
 * Created by My on 5.9.2016 г..
 */
public class IBAN {
    public static void main(String args[]) {

        FileInputStream file;
        try {
            file = new FileInputStream(new File("bank.xlsx"));
        } catch (FileNotFoundException e) {
            CreateFile.create();
            try {
                file = new FileInputStream(new File("bank_new.xlsx"));
            } catch (FileNotFoundException p) {
                System.out.print("just forget");
                return;
            }
        }

        boolean flag = false;
        String iban = entry();
        do{
            if(!validation(iban)){
                System.out.println("Your IBAN is invalid, but don`t give up and keep trying!");
                iban = entry();
                flag = true;
            }
            if(validation(iban)) flag = false;
        }while(flag);


        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        matchingBank(wb, iban);
    }


    private static String entry(){
        Scanner scan = new Scanner(System.in);
        String iban = "";
        do {
            if (iban.length() != 0 && iban.length() != 10) System.out.println("wrong length... try again");
            iban = scan.nextLine();
        } while (iban.length() != 22);
        return iban;
    }
    private static void matchingBank(XSSFWorkbook wb, String iban) {

        XSSFSheet sheet = wb.getSheetAt(0);
        String swift = iban.substring(4, 8);//5/9
        boolean flag = false;
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (swift.equals(sheet.getRow(i).getCell(0).toString().substring(0, 4))) {
                String bank = sheet.getRow(i).getCell(1).toString();
                String swiftCode = sheet.getRow(i).getCell(0).toString();
                System.out.println(bank + '\n' + swiftCode);
                flag = true;
                break;
            }
        }
        if (!flag) System.out.print("there is no that kind of bank");
    }

    private static String convert(String string) {
        String st = string.substring(0, 1);
        Integer ch = new Integer((int) st.charAt(0) - 55);
        String stringNew = ch.toString();
        return stringNew;
    }

    private static String takeSwift(String string) {
        String swift = string.substring(0, 4);
        for (int i = 0; i < 4; i++) {
            swift = swift.substring(1) + convert(swift);
        }
        return swift;
    }
    private static Boolean validation(String string){
        for (int i = 0; i < 2; i++) {
            string = string.substring(1) + convert(string);
        }
        for (int i = 0; i < 2; i++) {
            string = string.substring(1) + string.substring(0, 1);
        }
        BigInteger valid = new BigInteger(takeSwift(string) + string.substring(4) );
        BigInteger mo = new BigInteger("97");
        if(valid.mod(mo).equals(BigInteger.ONE)) return true;
        else return false;
    }
}
