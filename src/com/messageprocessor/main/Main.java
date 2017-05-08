package com.messageprocessor.main;

import com.messageprocessor.model.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Sale sale = new Sale();
        String inputDataOption = null;
        String line = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            System.out.println("Please chose option:");
            System.out.println("Read from console: 1");
            System.out.println("Read from sample file: 2");
            inputDataOption = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputDataOption.compareToIgnoreCase("1") == 0) {
            System.out.println("You chose to send message from the console. The application will read message until \"exit\" occured");
            String strLine = "";
            try {
                isr = new InputStreamReader(System.in);
                br = new BufferedReader(isr);
                while ((line = br.readLine()) != null && !line.equals("exit")) {
                    sale.processNotification(line);
                    sale.log.report();
                    System.out.println("Message processed!");
                }
                isr.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("You chose to send message from the sample file.");
            try {
                isr.close();
                BufferedReader inputFile = new BufferedReader(new FileReader("messages.txt"));
                while ((line = inputFile.readLine()) != null) {
                    sale.processNotification(line);
                    sale.log.report();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}