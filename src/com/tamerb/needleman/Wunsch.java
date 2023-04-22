package com.tamerb.needleman;

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


        // iç matris değerlerimizi algortimazmıdaki 3 farklı fonksiyona göre max değerleriyle doldurduk
        for (int i = 1; i < rowLen + 1; i++) {
            for (int j = 1; j < colLen + 1; j++) {
                matrix[0][0] = 0;
                matrix[i][0] = matrix[i - 1][0] + gap;
                matrix[0][j] = matrix[0][j - 1] + gap;
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
        StringBuilder hiza1Builder = new StringBuilder();
        StringBuilder hiza2Builder = new StringBuilder();

        int i = len1;
        int j = len2;

        while (i > 0 && j > 0) {
            int score = matrix[i][j];
            int scoreDiag = matrix[i - 1][j - 1];
            int scoreUp = matrix[i - 1][j];

            if (score == scoreDiag + (rowSequence.charAt(i - 1) == columnSequence.charAt(j - 1) ? match : mismatch)) {
                hiza1Builder.append(rowSequence.charAt(i - 1));
                hiza2Builder.append(columnSequence.charAt(j - 1));
                i--;
                j--;
            } else if (score == scoreUp + gap) {
                hiza1Builder.append(rowSequence.charAt(i - 1));
                hiza2Builder.append("-");
                i--;
            } else {
                hiza1Builder.append("-");
                hiza2Builder.append(columnSequence.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            hiza1Builder.append(rowSequence.charAt(i - 1));
            hiza2Builder.append("-");
            i--;
        }

        while (j > 0) {
            hiza1Builder.append("-");
            hiza2Builder.append(columnSequence.charAt(j - 1));
            j--;
        }

        String hiza1 = hiza1Builder.reverse().toString();
        String hiza2 = hiza2Builder.reverse().toString();

        System.out.println("hiza1: " + hiza1 + "\nhiza2: " + hiza2);

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


    static void yazdir() {
        System.out.println("Matris :");
        System.out.println(Arrays.deepToString(matrix)
                .replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}







