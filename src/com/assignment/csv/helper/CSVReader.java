package com.assignment.csv.helper;

import com.assignment.csv.model.Transaction;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReader {
    private static final String COMMA = ",";

    public List<Transaction> processInputFile(String inputFilePath) {

        List<Transaction> inputList = new ArrayList<Transaction>();

        try{

            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
                  e.printStackTrace();
        }

        return inputList ;
    }

    private Function<String, Transaction> mapToItem = (line) -> {

        String[] p = line.split(COMMA);// a CSV has comma separated lines
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);


        Transaction item = new Transaction();

        item.setId(p[0]);//<-- this is the first column in the csv file
        if (p[1] != null && p[1].trim().length() > 0) {

            try {
                item.setDate(formatter.parse(p[1].trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (p[2] != null && p[2].trim().length() > 0) {
            item.setAmount(Double.parseDouble(p[2]));
        }
        if (p[3] != null && p[3].trim().length() > 0) {
            item.setMerchant(p[3]);
        }
        if (p[4] != null && p[4].trim().length() > 0) {
            item.setType(p[4]);
        }
        if(p.length >5){

            if (p[5] != null && p[5].trim().length() > 0) {
                item.setRelatedTransaction(p[5]);
            }
        }
        return item;
    };

}
