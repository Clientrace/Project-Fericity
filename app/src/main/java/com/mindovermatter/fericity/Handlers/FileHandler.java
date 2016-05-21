package com.mindovermatter.fericity.Handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Sherlock Holmes on 5/21/2016.
 */
public class FileHandler {

    private static Random rand = new Random();
    private static Scanner x;
    private static File file;
    private static FileWriter fw;
    private static BufferedWriter bw;

    public static void save(){
        try {
            file = new File("user_data.inf");
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
        }catch(Exception e){}
    }

    public static String encrypt(String toEncrypt, String code){
        String retval = "";
        retval += rand.nextInt(10);
        for(int a=0;a<toEncrypt.length();a++){
            retval += encryptChar(toEncrypt.charAt(a), code);
            retval += rand.nextInt(10);
        }
        return retval;
    }
    public static String decrypt(String toEncrypt, String code){
        String retval = "";
        String temp = "";
        for(int a=0;a<toEncrypt.length();a++){
            temp += decryptChar(toEncrypt.charAt(a), code);
        }
        for(int i=1;i<temp.length();i+=2){
            retval = retval.concat(""+temp.charAt(i));
        }
        return retval;
    }
    public static char decryptChar(char toDecrypt, String code){
        code = new StringBuffer(code).reverse().toString();
        for(int a=0;a<code.length();a++){
            toDecrypt = (char) (toDecrypt^code.charAt(a));
        }
        return toDecrypt;
    }
    public static char encryptChar(char toEncrypt, String code){
        for(int a=0;a<code.length();a++){
            toEncrypt = (char) (toEncrypt^code.charAt(a));
        }
        return toEncrypt;
    }

}
