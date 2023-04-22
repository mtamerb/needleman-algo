package com.tamerb.needleman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Main {
    public static void main(String[] args) {

        long now = System.currentTimeMillis();

        fileIO();

        System.out.println("time: " + (System.currentTimeMillis() - now) + "ms");

    }

    static int len1;
    static String sequence1;
    static int len2;
    static String sequence2;

    private static void fileIO() {
        // try-catch bloğu ile dosyaları okuma
        // ilk dosyayı okuma
        File filePath1 = new File("./resources/files/TEST1.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath1))) {

            len1 = Integer.parseInt(bufferedReader.readLine());
            sequence1 = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ikinci dosya okuma
        File filePath2 = new File("./resources/files/TEST2.txt");
        try (BufferedReader bufferedReader2 = new BufferedReader(new FileReader(filePath2))) {
            // ikinci dosyayı okuma
            len2 = Integer.parseInt(bufferedReader2.readLine());
            sequence2 = bufferedReader2.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // com.tamerb.needleman.Wunsch sınıfından nesne oluşturma
        Wunsch.doldur(sequence1, sequence2, len1, len2);
        Wunsch.yazdir();
        Wunsch.traceBackAlignment(sequence1, sequence2, len1, len2);
    }
}