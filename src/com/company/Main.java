package com.company;


import java.math.BigInteger;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String iban = "BG50STSA93000020499849";
        //String iban = "GB82WEST12345698765432";
        if(validate(iban))
        {
            System.out.println(checkSwift(iban));
        }
        else
        {
            System.out.println("The IBAN you've entered is not valid!");
        }



    }

    private static String checkSwift(String iban) {
        SwiftCodes sw = new SwiftCodes();
        sw.initialiseDB();
        String result="There is no matching SWIFT code in the database";
        for (Map.Entry<String, String> entry : sw.swiftDB.entrySet()) {
            String shortSwift=entry.getKey().substring(0,4);
            String mySwift=iban.substring(4,8);
            if(shortSwift.equals(mySwift))
            {
                result=entry.getValue()+"\n"+entry.getKey();
                break;
            }
        }
        return result;
    }

    private static boolean validate(String iban) {
        boolean verifyLen = false;
        boolean verifyMod = false;
        if (iban.length() == 22) {
            verifyLen = true;
        }
        if (modCheck(iban)) {
            verifyMod = true;
        }
        if (verifyLen & verifyMod) {
            return true;
        }
        else
        {
            return false;
        }
    }

    //check the result of mod by 97
    private static boolean modCheck(String iban) {
        String newIban;
        try
        {
            newIban=iban.substring(4, 22) + iban.substring(0, 4);
        }catch(IndexOutOfBoundsException e)
        {
            return false;
        }
        int count = 10;
        for (char c = 'A'; c <= 'Z'; c++) {
            if (newIban.indexOf(c) >= 0) {
                newIban = newIban.replaceAll(String.valueOf(c), String.valueOf(Integer.toString(count)));

            }
            count++;
        }
        BigInteger num = new BigInteger(newIban);
        BigInteger modBy = new BigInteger("97");
        if (num.mod(modBy).equals(BigInteger.ONE)) {
            return true;
        } else {
            return false;
        }
    }
}
