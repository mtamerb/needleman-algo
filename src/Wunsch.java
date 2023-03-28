import java.io.*;
import java.util.Arrays;

public class Wunsch {

    static int rowLen;
    static int colLen;

    static int[][] matrix;

    static final int match = 1;
    static final int mismatch = -1;
    static final int gap = -2;
    static int matchValue = 0;


    //matrisimizi doldurma methodu
    static void doldur(String rowSequence, String columnSequence, int rowLength, int colLength) {

        rowLen = rowLength;
        colLen = colLength;

        matrix = new int[rowLen + 1][colLen + 1];


        // 0 0 sıfır matrisini 0 olarak belirledik
        matrix[0][0] = 0;


        // satır değerlerini gap ifadesi kadar artanlı şekilde doldurduk
        for (int i = 1; i < rowLen + 1; i++) {
            matrix[i][0] = matrix[i - 1][0] + gap;
        }
        //  sütun değerlerini gap ifadesi kadar artanlı doldurduk
        for (int i = 1; i < colLen + 1; i++) {
            matrix[0][i] = matrix[0][i - 1] + gap;
        }

        // iç matris değerlerimizi algortimazmıdaki 3 farklı fonksiyona göre max değerleriyle doldurduk
        for (int i = 1; i < rowLen + 1; i++) {
            for (int j = 1; j < colLen + 1; j++) {
                if (rowSequence.charAt(i - 1) == columnSequence.charAt(j - 1)) {
                    matchValue = match;
                } else {
                    matchValue = mismatch;
                }
                matrix[i][j] = Math.max(Math.max(matrix[i][j - 1] + gap, matrix[i - 1][j - 1] + matchValue), matrix[i - 1][j] + gap);
            }
        }
    }

    // traceback fonkisyonumuz --> en iyi eşleşmeyi bulmak için
    static void traceBackAlignment(String rowSequence, String columnSequence, int len1, int len2) {
        String hiza1 = "";
        String hiza2 = "";
        int rowLength = len1;
        int columnLength = len2;
        int i = rowLength;
        int j = columnLength;

        while (i > 0 && j > 0) {
            int score = matrix[i][j];
            int scoreDiag = matrix[i - 1][j - 1];
            int scoreUp = matrix[i - 1][j];

            if (score == scoreDiag + (rowSequence.charAt(i - 1) == columnSequence.charAt(j - 1) ? match : mismatch)) {
                hiza1 += (rowSequence.charAt(i - 1));
                hiza2 += (columnSequence.charAt(j - 1));
                i--;
                j--;
            } else if (score == scoreUp + gap) {
                hiza1 += (rowSequence.charAt(i - 1));
                hiza2 += ("-");
                i--;
            } else {
                hiza1 += ("-");
                hiza2 += (columnSequence.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            hiza1 += (rowSequence.charAt(i - 1));
            hiza2 += ("-");
            i--;
        }

        while (j > 0) {
            hiza1 += ("-");
            hiza2 += (columnSequence.charAt(j - 1));
            j--;
        }

        hiza1 = reverseAlignment(hiza1);
        hiza2 = reverseAlignment(hiza2);


        System.out.println("\nOptimal Hizalama : ");

        System.out.println("optimal hizalama sonrası --> " + hiza1 + " && " +hiza2);



        System.out.println("score: " + calculateScore(hiza1, hiza2));
    }


    static int calculateScore(String hiza1, String hiza2) {
        int score = 0;
        for (int i = 0; i < hiza1.length(); i++) {
            if (hiza1.charAt(i) == hiza2.charAt(i)) {
                score += match;
            } else if (hiza1.charAt(i) == '-' || hiza2.charAt(i) == '-') {
                score += gap;
            } else {
                score += mismatch;
            }
        }
        return score;
    }

    static String reverseAlignment(String str) {
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
    }


    static void yazdir() {
        System.out.println("Matris :");
        System.out.println(Arrays.deepToString(matrix)
                .replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}







