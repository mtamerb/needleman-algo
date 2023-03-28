import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



public class Main {
    public static void main(String[] args) {
        //çalışma süresi için startTime tuttuk
        long startTime = System.nanoTime();

        // try-catch bloğu ile dosyaları okuma
        try {
            // home klasoru
            File homedir = new File(System.getProperty("user.home"));

            // ilk dosyayı okuma
            File filePath1 = new File(homedir, "Documents/TEST");
            FileReader fileReader = new FileReader(filePath1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int len1 = Integer.parseInt(bufferedReader.readLine());
            String sequence1 = bufferedReader.readLine();

            // ikinci dosyayı okuma
            File filePath2 = new File(homedir, "Documents/TEST2");
            FileReader fileReader2 = new FileReader(filePath2);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

            int len2 = Integer.parseInt(bufferedReader2.readLine());
            String sequence2 = bufferedReader2.readLine();

            System.out.println("optimal hizalama öncesi --> " + sequence1 + " && " +sequence2);
            // Wunsch sınıfından nesne oluşturma
            Wunsch.doldur(sequence1, sequence2, len1, len2);
            Wunsch.yazdir();
            Wunsch.traceBackAlignment(sequence1, sequence2, len1, len2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // çalışma süresi için endTime tuttuk ve yazdırdık
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("çalışma süresi: " + duration + " nanoseconds");

    }
}