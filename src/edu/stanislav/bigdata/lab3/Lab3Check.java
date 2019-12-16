package edu.stanislav.bigdata.lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lab3Check {

    public static void main(String[] args) {

        String csvFile = "C:\\Space\\Learning\\KPI\\9 term\\BigData\\big_data_labs\\EURUSD_GBP_CHF_short.csv";
        List<String[]> lines = new ArrayList<>();
        String lineRead = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((lineRead = br.readLine()) != null) {
                lines.add(lineRead.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] line : lines) {
            System.out.println(Arrays.toString(line));
        }

        // count avg of volumes for all 3 currencies (from example)
        Double AVG_VOL_USD = 0.0, AVG_VOL_GBP = 0.0, AVG_VOL_CHF = 0.0;
        for (String[] line : lines) {
            AVG_VOL_USD += Double.parseDouble(line[6]);
            AVG_VOL_GBP += Double.parseDouble(line[13]);
            AVG_VOL_CHF += Double.parseDouble(line[20]);
        }

        AVG_VOL_USD /= lines.size();
        AVG_VOL_GBP /= lines.size();
        AVG_VOL_CHF /= lines.size();

        System.out.println(AVG_VOL_USD);
        System.out.println(AVG_VOL_GBP);
        System.out.println(AVG_VOL_CHF);

        // count avg of maximums for all 3 currencies for certain year
        Map<String, SumCount> avgMaximums = new TreeMap<>();
        for (String[] line: lines) {
            String year = line[0].split("\\.")[0];
            if (!avgMaximums.containsKey("USD_" + year)) {
                avgMaximums.put("USD_" + year, new SumCount());
                avgMaximums.put("GBP_" + year, new SumCount());
                avgMaximums.put("CHF_" + year, new SumCount());
            }
            SumCount usd_sum_count = avgMaximums.get("USD_" + year);
            usd_sum_count.addToSum(Double.parseDouble(line[3]));
            usd_sum_count.addCount();
            SumCount gbp_sum_count = avgMaximums.get("GBP_" + year);
            gbp_sum_count.addToSum(Double.parseDouble(line[10]));
            gbp_sum_count.addCount();
            SumCount chf_sum_count = avgMaximums.get("CHF_" + year);
            chf_sum_count.addToSum(Double.parseDouble(line[17]));
            chf_sum_count.addCount();

        }

        // print average maximum for each currency for each year
        for (String key: avgMaximums.keySet()) {
            System.out.print(key + "=");
            SumCount sumCount = avgMaximums.get(key);
            System.out.print(sumCount.getSum() / sumCount.getCount());
            System.out.println();
        }
    }

}
