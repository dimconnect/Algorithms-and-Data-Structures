package com.company;

import java.util.LinkedHashMap;


/**
 * Created by Danny on 9/6/2016.
 */
public class SwiftCodes {
    static LinkedHashMap<String, String> swiftDB = new LinkedHashMap<>();

    public SwiftCodes() {
        initialiseDB();
    }

    static void initialiseDB() {
        swiftDB.put("BUINBGSF", "ALLIANZ BANK BULGARIA AD");
        swiftDB.put("BSBGBGSF", "BANKSERVICE AD");
        swiftDB.put("BNPABGSX", "BNP PARIBAS S.A.-SOFIA BRANCH");
        swiftDB.put("NASBBGSF", "BULGARIAN DEVELOPMENT BANK");
        swiftDB.put("BNBGBGSF", "BULGARIAN NATIONAL BANK");
        swiftDB.put("BGUSBGSF", "BULGARIAN-AMERICAN CREDIT BANK");
        swiftDB.put("CECBBGSF", "CENTRAL COOPERATIVE BANK PLC");
        swiftDB.put("CEDPBGSF", "CENTRAL DEPOSITORY AD");
        swiftDB.put("BUIBBGSF", "CIBANK JSC");
        swiftDB.put("CITIBGSF", "CITIBANK EUROPE PLC, BULGARIA BRANCH");
        swiftDB.put("BINVBGSF", "COMMERCIAL BANK VICTORIA EAD");
        swiftDB.put("DEMIBGSF", "D COMMERCE BANK AD");
        swiftDB.put("STSABGSF", "DSK BANK");
        swiftDB.put("BPBIBGSF", "EUROBANK BULGARIA AD");
        swiftDB.put("EUFCBGSF", "EURO-FINANCE LTD");
        swiftDB.put("FINVBGSF", "FIRST INVESTMENT BANK AD");
        swiftDB.put("INGBBGSF", "ING BANK N.V. SOFIA BRANCH");
        swiftDB.put("INTFBGSF", "INTERCARD FINANCE AD");
        swiftDB.put("IABGBGSF", "INTERNATIONAL ASSET BANK AD");
        swiftDB.put("IORTBGSF", "INVESTBANK PLC");
        swiftDB.put("ISBKBGSF", "ISBANK AG SOFIA BRANCH");
        swiftDB.put("SOMBBGSF", "MUNICIPAL BANK PLC");
        swiftDB.put("PIRBBGSF", "PIRAEUS BANK BULGARIA AD");
        swiftDB.put("PRCBBGSF", "PROCREDIT BANK (BULGARIA) EAD");
        swiftDB.put("RZBBBGSF", "RAIFFEISENBANK (BULGARIA) EAD");
        swiftDB.put("TTBBBG22", "SOCIETE GENERALE EXPRESSBANK");
        swiftDB.put("TCZBBGSF", "T.C. ZIRAAT BANKASI - SOFIA BRANCH");
        swiftDB.put("TBIBBGSF", "TBI BANK EAD");
        swiftDB.put("TEXIBGSF", "TEXIM BANK AD");
        swiftDB.put("CREXBGSF", "TOKUDA BANK AD");
        swiftDB.put("UNCRBGSF", "UNICREDIT BULBANK AD");
        swiftDB.put("UBBSBGSF", "UNITED BULGARIAN BANK");
    }
}