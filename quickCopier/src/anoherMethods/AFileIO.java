package anoherMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AFileIO {
    public static String stringNumSelectline(String fileName, int sns) throws IOException {
        String word="";
        int i=0;
        Scanner sc = new Scanner(new File(fileName));
        while (i!=sns) {
            i++;
            word = sc.nextLine();
        }
        sc.close();
        return word;
    }
    public static void infReader(String fileName) throws IOException {
        String word="";
        Scanner sc = new Scanner(new File(fileName));
        while (word!=null){
            word=sc.nextLine();
        }
    }
    public static short countStrings(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        Scanner reader = new Scanner(file, "UTF-8");
        int out = 0;
        String x;
        while (reader.hasNextLine()) {
           x =  reader.nextLine();
           ++out;
        }
        return (short) out;
    }
    public static String stringNumSelectline(File file, int sns) throws IOException {
        String word="";
        int i=0;
        Scanner sc = new Scanner(file, "UTF-8");
        try {
            while (i!=sns) {
                i++;
                word = sc.nextLine();
            }
        } catch (Exception e) {
            return "noString";
        }

        sc.close();
        return word;
    }
}
