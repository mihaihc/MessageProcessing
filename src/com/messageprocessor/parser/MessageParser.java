package com.messageprocessor.parser;

public class MessageParser {

    private String productType = null;

    private double productPrice = 0;

    private int productQuantity = 0;

    private String operatorType = null;

    public MessageParser(String message) {
        parseMessage(message);
    }

    private void parseMessage(String message) {

        if (null == message || message.isEmpty()) {
            System.out.println("Message is empty!");
        }
        String[] messageArray = message.trim().split("\\s+");
        String firstWord = messageArray[0];
        if (firstWord.matches("Add|Subtract|Multiply")) {
            parseMessageType3(messageArray);
        } else if (firstWord.matches("^\\d+")) {
            parseMessageType2(messageArray);
        } else if (messageArray.length == 3 && messageArray[1].contains("at")) {
            parseMessageType1(messageArray);
        } else {
            System.out.println("Wrong sales notice");
        }
    }

    private void parseMessageType1(String[] messageArray) {
        if(messageArray.length > 3 || messageArray.length < 3) {
            System.out.println("Incorect length for  message type 1.Can't parse the message!");
        } else {
            productType = parseType(messageArray[0]);
            productPrice = parsePrice(messageArray[2]);
            productQuantity = 1;
        }
    }

    private void parseMessageType2(String[] messageArray) {
        if(messageArray.length > 7 || messageArray.length < 7) {
            System.out.println("Incorect length for  message type 2.Can't parse the message!");
        } else{
            productType = parseType(messageArray[3]);
            productPrice = parsePrice(messageArray[5]);
            productQuantity = Integer.parseInt(messageArray[0]);
        }
    }

    private void parseMessageType3(String[] messageArray) {
        if(messageArray.length > 3 || messageArray.length < 3) {
            System.out.println("Incorect length for  message type 3.Can't parse the message!");
        } else {
            operatorType = messageArray[0];
            productType = parseType(messageArray[2]);
            productQuantity = 0;
            productPrice = parsePrice(messageArray[1]);
        }
    }

    // Just to handle the plural cases of the fruit products
    // Made under the assumption that no other sale items will be given. e.g Tumbler, Plates. etc.
    // @return[String] parsed string e.g 'mango' will become 'mangoes'
    public String parseType(String rawType) {
        String parsedType = "";
        String typeWithoutLastChar = rawType.substring(0, rawType.length() - 1);
        //enum DEPREC
        if (rawType.endsWith("o")) {
            parsedType = String.format("%soes", typeWithoutLastChar);
        } else if (rawType.endsWith("y")) {
            parsedType = String.format("%sies", typeWithoutLastChar);
        } else if (rawType.endsWith("h")) {
            parsedType = String.format("%shes", typeWithoutLastChar);
        } else if (!rawType.endsWith("s")) {
            parsedType = String.format("%ss", rawType);
        } else {
            parsedType = String.format("%s", rawType);
        }
        return parsedType.toLowerCase();
    }

    public double parsePrice(String rawPrice) {
        double price = Double.parseDouble(rawPrice.replaceAll("£|p", ""));
        if (!rawPrice.contains(".")) {
            price = Double.valueOf(Double.valueOf(price) / Double.valueOf("100"));
        }
        return price;
    }

    public String getProductType() {
        return productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }


}