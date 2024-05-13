import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;

public class ReadCsvTest {
    public static void main(String[] args) throws IOException {
        String csvFilePath = "C:/Users/Nam/Desktop/products.csv";

        try (Reader reader = new InputStreamReader(new FileInputStream(csvFilePath));
             CSVReader csvReader = new CSVReader(reader)) {
            csvReader.skip(1);
            String[] nextRecord;
            String products = "";
            String colors = "";
            String sizes = "";
            String imgs = "";
            while ((nextRecord = csvReader.readNext()) != null) {
                String size = nextRecord[5];
                String color = nextRecord[6];
                String img = nextRecord[7];
                colors += color + ", ";
                sizes += size +", ";
                imgs += img + ", ";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
