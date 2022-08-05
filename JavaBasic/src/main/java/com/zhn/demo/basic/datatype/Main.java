package com.zhn.demo.basic.datatype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println(1 / 0.1);  // 10.0
        System.out.println(100000000 / (100000000 / 0.1)); // 0.1

        String url = "https://ipv4.icanhazip.com/";
        BufferedReader reader = null;
        HttpURLConnection connection;
        StringBuilder builder = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String read = "";
            while ((read = reader.readLine()) != null) {
                builder.append(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(builder.toString());

        StringBuilder builder2 = new StringBuilder();
        String[] arr = builder2.toString().split("\\.");

        System.out.println(Arrays.toString(arr));

        System.out.println("=================");

        String ip = "119.123.58.134";
        String[] split = ip.split("\\.");
        System.out.println(Integer.valueOf(split[0]));
        System.out.println(Integer.valueOf(split[1]));
        System.out.println(Integer.valueOf(split[2]));
        System.out.println(Integer.valueOf(split[3]));

        System.out.println(Integer.parseInt(split[0]));
        System.out.println(Integer.parseInt(split[1]));
        System.out.println(Integer.parseInt(split[2]));
        System.out.println(Integer.parseInt(split[3]));

        System.out.println(Byte.valueOf(split[0]));
        System.out.println(Byte.valueOf(split[1]));
        System.out.println(Byte.valueOf(split[2]));
//        System.out.println(Byte.valueOf(split[3]));     //  报错  Value out of range. Value:"134" Radix:10
//        System.out.println(Byte.parseByte(split[3]));     //  报错  Value out of range. Value:"134" Radix:10


    }

}
