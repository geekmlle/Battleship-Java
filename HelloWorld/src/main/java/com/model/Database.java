package com.model;

/**
 * Created by blondieymollo on 2/21/16.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Database {

    BufferedReader br = null;

    public String readFile (){

        StringBuilder sb = new StringBuilder("");

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/blondieymollo/Desktop/code.html"));

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return sb.toString();
    }


}
