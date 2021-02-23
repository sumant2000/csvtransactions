package com.assignment.csv;

import com.assignment.csv.helper.CSVReader;
import com.assignment.csv.model.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args)  {
        CSVReader csvReader=new CSVReader();
        List<Transaction> listOfTransactions = csvReader.processInputFile(args[0]);

        Map<Boolean,List<Transaction>> mapList = listOfTransactions.stream().collect(Collectors.partitioningBy(item -> "REVERSAL".equalsIgnoreCase(item.getType().trim())));
        List<Transaction> reversalTxs = mapList.get(true);
        listOfTransactions =mapList.get(false);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);


        Date fromDate= null;
        Date toDate=null;
        try {
            fromDate = formatter.parse(args[1].replaceAll("fromDate:","").trim());
            toDate=formatter.parse(args[2].replaceAll("toDate:","").trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String merchantName=args[3].replaceAll("merchant:","").trim();
        Date finalFromDate = fromDate;
        Date finalToDate = toDate;

        List<Transaction> finalList = listOfTransactions.stream().filter(
                m -> m.getMerchant().trim().equals(merchantName)).
                filter(e -> e.getDate().after(finalFromDate)).
                filter(e -> e.getDate().before(finalToDate)).
        filter(r -> !checkReversal(reversalTxs, r)).collect(Collectors.toList());

        // calculating average
        double amountAverage = finalList.stream().mapToDouble(Transaction::getAmount)
                .average()
                .getAsDouble();

        System.out.println("Number of transactions = "+finalList.size());
        System.out.println("Average Transaction Value = "+amountAverage);


    }

    /**
     *
     * @param reversalTxs as List of transactions to iterate
     * @param tx as Transaction
     * @return true if found transaction with reversal
     */
    private static boolean checkReversal(List<Transaction> reversalTxs, Transaction tx){
        Optional<Transaction> txFound = reversalTxs.stream().filter(item -> item.getRelatedTransaction().trim().equalsIgnoreCase(tx.getId())).findAny();
        return txFound.isPresent();
    }

}
