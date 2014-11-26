/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mum.processexceldata;

import com.sun.jmx.defaults.ServiceName;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author demodem
 */
public class RateService {

    StringTokenizer st;
    //Singleton==> load Country and codes to HashMap<long,Country>
    HashMap<Long, Country> countryCodeMap = new HashMap<Long, Country>();

    //rates and Date

    public void importCallRates(String path, Date effectiveDate) {
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\demodem\\Downloads\\Calling_Codes.xls"));
            System.out.println("File:" + file);
           // HSSFWorkbook book = new HSSFWorkbook(file);

            //Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            int noOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < noOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                processCallRates(sheet, effectiveDate);
            }
        } catch (Exception e) {
            System.out.println("Error in reading the file.");
        }
    }

    //Insert rates per one service and country 
    //Sheet Name should be like Spectra_USA
    public void processCallRates(HSSFSheet sheet, Date effectiveDate) {
        String serviceName = null;
        String sourceCountryName = null;
        String sheetName = sheet.getSheetName();
        Service serv = null;
        st = new StringTokenizer(sheetName, "_");
        if (st.countTokens() == 2) {
            serviceName = st.nextToken();
            sourceCountryName = st.nextToken();
        }

        if (serviceName != null && !serviceName.isEmpty()) {
            //get service from DB matching service Name and Source Country
            serv = new Service();
        }

        Iterator<Row> rowIterator = sheet.iterator();
        //Skip first row with headings
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

//insertNewRates
//Service  service
//destination  Country
//offPeakRate long
//peakRate  long        
//effectiveDate Date
        while (rowIterator.hasNext()) {
            Rate rate = new Rate();
            Row row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                    //Cell cell = cellIterator.next();                   
                //if(cell.getCellType()== Cell.CELL_TYPE_NUMERIC){
                //System.out.print(cell.getNumericCellValue() + "\t\t");
                rate.setDestination(countryCodeMap.get(cellIterator.next().getNumericCellValue()));
                rate.setPeakRate(cellIterator.next().getNumericCellValue());
                rate.setOffPeakRate(cellIterator.next().getNumericCellValue());
                   //}

            }
            rate.setService(serv);
            //insert into Rate and get Id
           // serviceIdList.add(rateId)
        }
        //update Service with RateIds 
        //serv.getRates().addAll(RateIds); saveService
    }

}
