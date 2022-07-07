package com.ulimit.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParseFiles{

    int lineCount=0;
    List<MyOrders> rows = new ArrayList<>();

    /* Method Name :   parseCsvFiles
       Arguments   :   Type String used for CSV file name as input
       Description :   This method will parse the input csv file and create a POJO.
   */
    public String parseCsvFile(String fileName) {

        Pattern pattern = Pattern.compile(",");

        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {

            file.lines().skip(1).forEach(line -> {

                String[]col = pattern.split(line);
                lineCount++;
                rows.add( new MyOrders( Integer.parseInt(col[0]), Double.parseDouble(col[1]), col[2], col[3], fileName, lineCount ));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        lineCount =0 ;
        return "OK";
    }


    /*  Method Name :   parseJsonFiles
        Arguments   :   Type String used for Json file name as input
        Description :   This method will parse the input json file and create a POJO.
    */
    public String parseJsonFile(String fileName) {


        JSONParser jsonParser = new JSONParser();

        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {

            file.lines().forEach(line -> {

                Object obj = null;
                try {
                    obj = jsonParser.parse(line);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject row = (JSONObject) obj;
                rows.add(new MyOrders((long) row.get("orderId"), (Double) row.get("amount"), (String) row.get("currency"), (String) row.get("comment"), fileName, lineCount));
            });

        lineCount =0 ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "OK";
    }

}