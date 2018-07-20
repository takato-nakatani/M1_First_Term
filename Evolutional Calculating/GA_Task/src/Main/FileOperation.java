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
    private List<List<String>> meetCsvDataListString = new ArrayList<>();
    private List<List<Integer>> meetCsvDataListInteger = new ArrayList<>();

    public List<List<String>> OpenCsvToMeetDataMapString(String filepath){
        try{
            File file = new File(filepath);
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            this.meetCsvDataListString = new ArrayList<>();
            csvStringList = new ArrayList<>();

            while ((this.stringNumberData = bufferedreader.readLine()) != null){
                this.csvStringList = ParseLineString(this.stringNumberData);
                System.out.println(stringNumberData);
                this.meetCsvDataListString.add(this.csvStringList);
            }

            bufferedreader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return this.meetCsvDataListString;
    }

    public List<List<Integer>> OpenCsvToMeetDataMapInteger(String filepath){
        try{
            File file = new File(filepath);
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            this.meetCsvDataListInteger = new ArrayList<>();
            csvIntegerList = new ArrayList<>();

            while ((this.stringNumberData = bufferedreader.readLine()) != null){
                this.csvIntegerList = ParseLineInteger(this.stringNumberData);
                System.out.println(stringNumberData);
                this.meetCsvDataListInteger.add(this.csvIntegerList);
            }

            bufferedreader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return this.meetCsvDataListInteger;
    }

    private static List<Integer> ParseLineInteger(String line){
        List<Integer> meetIntDataList = new ArrayList<Integer>();
        StringTokenizer token = new StringTokenizer(line, ",");
        while (token.hasMoreElements()) {
            String s = token.nextToken();
            try {
                meetIntDataList.add(Integer.parseInt(s));
            } catch (Exception ex) {
                new Exception("Bad Integer in " + "[" + line + "]. " + ex.getMessage());
            }
        }
        return meetIntDataList;
    }

    private static List<String> ParseLineString(String line){
        List<String> meetStrDataList = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(line, ",");
        while (token.hasMoreElements()) {
            String s = token.nextToken();
            try {
                meetStrDataList.add(s);
            } catch (Exception ex) {
                new Exception("Bad Integer in " + "[" + line + "]. " + ex.getMessage());
            }
        }
        return meetStrDataList;
    }

//                this.token = new StringTokenizer(stringNumberData, ",");
//                this.meetCsvData = new ArrayList<>();
//
//                while (this.token.hasMoreTokens()){
//                    this.meetCsvData.add(this.token.nextToken());
//                }
//
//                this.meetCsvDataList.add(this.meetCsvData);
}
