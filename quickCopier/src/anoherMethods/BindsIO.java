package anoherMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BindsIO {

    public static String[][] toArray(String fileName, int lines) throws FileNotFoundException {
        int q = 0;
        String[][] Binds = new String[lines][2];
        Scanner sc = new Scanner(new File(fileName));
        Scanner buffer = new Scanner(new File(fileName));
        String line;
        for (int i = 0; i < lines; i++) {
            line = sc.nextLine();
            int indexM = line.indexOf("=");
            String out1 = line.substring(0, indexM);
            String out2 = line.substring(indexM+1);
            Binds[i][0]=out1;
            Binds[i][1]=out2;
        }
        sc.close();
        return Binds;
    }

}
