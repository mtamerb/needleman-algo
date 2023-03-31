import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Main {
    public static void main(String[] args) {

        long now = System.currentTimeMillis();

        fileIO();

        System.out.println("time: " + (System.currentTimeMillis() - now) + "ms");

    }

    private static void fileIO() {
        // try-catch bloğu ile dosyaları okuma
        try {
            // home klasoru



            // ilk dosyayı okuma
            File filePath1 = new File("./resources/files/TEST1.txt");
            FileReader fileReader = new FileReader(filePath1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int len1 = Integer.parseInt(bufferedReader.readLine());
            String sequence1 = bufferedReader.readLine();

            // ikinci dosyayı okuma
            File filePath2 = new File("./resources/files/TEST2.txt");
            FileReader fileReader2 = new FileReader(filePath2);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

            int len2 = Integer.parseInt(bufferedReader2.readLine());
            String sequence2 = bufferedReader2.readLine();

            // Wunsch sınıfından nesne oluşturma
            Wunsch.doldur(sequence1, sequence2, len1, len2);
            Wunsch.yazdir();
            Wunsch.traceBackAlignment(sequence1, sequence2, len1, len2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}