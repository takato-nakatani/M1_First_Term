package Main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.*;


public class Main {
    private static final int INITIAL_SOLUTION_NUMBER = 2;
    private static final int CROSSOVER_LOOP_NUMBER = 2;
    private static final int MUTATION_PROBABILITY = 100;
    private static final int PRICE_LIMIT = 2000;
    private static final int FULL_DEGREE_LIMIT = 11;
    private static final int TOURNAMENT_SIZE = 2;
    private static final int GENERATIONS_NUMBER = 2;
    public static int cntNumberData = 0;
    public static List<Integer> csvDataInteger = new ArrayList<>();
    public static List<List<Integer>> meetDataCsv = new ArrayList<>();
    public static List<List<String>> meetNameCsv = new ArrayList<>();
    public static LinkedHashMap<String, List<Integer>> meetMapData = new LinkedHashMap<>();
    public static List<LinkedHashMap<String, List<Integer>>> meetMapDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> OrderCrossoverDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> previousGenerationsSolution = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> nextGenerationsSolution = new ArrayList<>();
    public static LinkedHashMap<List<String>, Integer> meetNameAndEvaluation = new LinkedHashMap<>();

    public static void main(String[] args){
        //MapのString値に肉の種類の名前を、List値に肉の値段と胃もたれ度と満足度を格納

        //Mapした名前を格納した配列meetNamesArrayを作成する。
        FileOperation fo = new FileOperation();
        meetNameCsv = fo.OpenCsvToMeetDataMapString("src\\Main\\meet_name.csv");
        meetDataCsv = fo.OpenCsvToMeetDataMapInteger("src\\Main\\meet_data.csv");
        meetMapData = new LinkedHashMap<>();

        for (List<Integer> meetdatalist : meetDataCsv) {
            csvDataInteger = new ArrayList<>();
            for (Integer meetdata : meetdatalist) {
                csvDataInteger.add(meetdata);
            }
            meetMapData.put(meetNameCsv.get(cntNumberData).get(0), csvDataInteger);
            cntNumberData++;
        }

        System.out.println("meetdatacsv : " + meetDataCsv);
        System.out.println("meetnamecsv : " + meetNameCsv);
        System.out.println("meetMapData : " + meetMapData);

        //初期解のインスタンスを生成。(引数にmeetNamesArrayを指定してコンストラクタで処理)
        InitialSolution IS = new InitialSolution();
        meetMapDataList = IS.GenerateInitialsolution(meetMapData, INITIAL_SOLUTION_NUMBER);
        previousGenerationsSolution = meetMapDataList;
        //-----------------------------------------------ここからfor文(世代数＝ループ数)
        for (int i = 0; i < GENERATIONS_NUMBER; i++) {
            //OrderCrossOverクラスに引数で初期解のインスタンスを渡す
            //ランダムに親を2つ選択し、順序交叉によって2つの子の解を生成させる。
            //この操作をm回繰り返すことによって、2m個の解を生み出し、OrderCrossoverインスタンスでの配列の解のオブジェクトを2m個作成
            OrderCrossoverDataList = new ArrayList<>();
            OrderCrossover OC = new OrderCrossover();
            OrderCrossoverDataList = OC.SolutionsOrderCrossover(meetMapDataList, CROSSOVER_LOOP_NUMBER, INITIAL_SOLUTION_NUMBER);
            //初期解initialSolutionオブジェクト(n個)と順序交叉OrderCrossoverオブジェクトによって選択された解(2m個)を組み合わせて解のリストSolutionDataListを作成し格納する。
            SolutionDataList.addAll(previousGenerationsSolution);
            previousGenerationsSolution = new ArrayList<>();
            SolutionDataList.addAll(OrderCrossoverDataList);
            //解のリストであるSolutionDataListの各要素に対してmutationを確率的に行う。
            Mutation Mutation = new Mutation();
            SolutionDataListAfterMutation = Mutation.MutationGene(SolutionDataList, MUTATION_PROBABILITY);
            //solutionArrayの各配列に対して、2つの制約条件(値段と胃もたれ度)を満たす解の範囲を決定し、解の有効範囲の終端の要素番号を取得し、effectiveRangeという変数に格納
            //solutionArrayのeffectiveRangeまでの範囲の各要素を目的関数によって評価する。
            Evaluation evaluation = new Evaluation();
            meetNameAndEvaluation = evaluation.EvaluateMeet(SolutionDataListAfterMutation, PRICE_LIMIT, FULL_DEGREE_LIMIT);

            //ここからトーナメント選択
            //解の配列solutionArrayからt個の解取り出して、一番いいものをadaptationSolutionsという配列に格納する。
            SelectSolution SS = new SelectSolution();
            nextGenerationsSolution = SS.tournamentSelection(meetNameAndEvaluation, meetMapData, TOURNAMENT_SIZE, INITIAL_SOLUTION_NUMBER);
            System.out.println("-----------------------------------------" + (i+1) + "世代目終了---------------------------------------------------------");
        }
        //------------------------------------------------ここまでfor文

        //初期解のインスタンスを破棄
    }

}