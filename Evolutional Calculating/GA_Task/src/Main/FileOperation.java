//csvファイルを読み込むための関数

package Main;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class FileOperation {
    private List<String> csvStringList = new ArrayList<>();
    private List<Integer> csvIntegerList = new ArrayList<>();
    private String stringNumberData;
    private List<List<String>> meatCsvDataListString = new ArrayList<>();
    private List<List<Integer>> meatCsvDataListInteger = new ArrayList<>();

    public List<List<String>> OpenCsvTomeatDataMapString(String filepath){
        try{
            File file = new File(filepath);
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            this.meatCsvDataListString = new ArrayList<>();
            csvStringList = new ArrayList<>();

            while ((this.stringNumberData = bufferedreader.readLine()) != null){
                this.csvStringList = ParseLineString(this.stringNumberData);
                System.out.println(stringNumberData);
                this.meatCsvDataListString.add(this.csvStringList);
            }

            bufferedreader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return this.meatCsvDataListString;
    }

    public List<List<Integer>> OpenCsvTomeatDataMapInteger(String filepath){
        try{
            File file = new File(filepath);
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            this.meatCsvDataListInteger = new ArrayList<>();
            csvIntegerList = new ArrayList<>();

            while ((this.stringNumberData = bufferedreader.readLine()) != null){
                this.csvIntegerList = ParseLineInteger(this.stringNumberData);
                System.out.println(stringNumberData);
                this.meatCsvDataListInteger.add(this.csvIntegerList);
            }

            bufferedreader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return this.meatCsvDataListInteger;
    }

    private static List<Integer> ParseLineInteger(String line){
        List<Integer> meatIntDataList = new ArrayList<Integer>();
        StringTokenizer token = new StringTokenizer(line, ",");
        while (token.hasMoreElements()) {
            String s = token.nextToken();
            try {
                meatIntDataList.add(Integer.parseInt(s));
            } catch (Exception ex) {
                new Exception("Bad Integer in " + "[" + line + "]. " + ex.getMessage());
            }
        }
        return meatIntDataList;
    }

    private static List<String> ParseLineString(String line){
        List<String> meatStrDataList = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(line, ",");
        while (token.hasMoreElements()) {
            String s = token.nextToken();
            try {
                meatStrDataList.add(s);
            } catch (Exception ex) {
                new Exception("Bad Integer in " + "[" + line + "]. " + ex.getMessage());
            }
        }
        return meatStrDataList;
    }
}
