package Main;

import java.util.*;

public class Main {
    private static final int INITIAL_SOLUTION_NUMBER = 2;
    private static final int CROSSOVER_LOOP_NUMBER = 2;
    private static final int MUTATION_PROBABILITY = 10;
    private static final int PRICE_LIMIT = 8000;
    private static final int FULL_DEGREE_LIMIT = 35;
    private static final int TOURNAMENT_SIZE = 2;
    private static final int GENERATIONS_NUMBER = 50;
    public static int cntNumberData = 0;
    public static double maxEvaluation = 0;
    public static List<String> maxEvaluationSolution = new ArrayList<>();
    public static List<Integer> csvDataInteger = new ArrayList<>();
    public static List<List<Integer>> meatDataCsv = new ArrayList<>();
    public static List<List<String>> meatNameCsv = new ArrayList<>();
    public static LinkedHashMap<String, List<Integer>> meatMapData = new LinkedHashMap<>();
    public static List<LinkedHashMap<String, List<Integer>>> meatMapDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> OrderCrossoverDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> previousGenerationsSolution = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> nextGenerationsSolution = new ArrayList<>();
    public static LinkedHashMap<List<String>, Double> nextGenerationsSolutionAndEvaluations = new LinkedHashMap<>();
    public static LinkedHashMap<List<String>, Double> meatNameAndEvaluation = new LinkedHashMap<>();

    public static void main(String[] args){
        //MapのString値に肉の種類の名前を、List値に肉の値段と胃もたれ度と満足度を格納

        //Mapした名前を格納した配列meatNamesArrayを作成する。
        FileOperation fo = new FileOperation();
        meatNameCsv = fo.OpenCsvTomeatDataMapString("src\\Main\\meat_name.csv");
        meatDataCsv = fo.OpenCsvTomeatDataMapInteger("src\\Main\\meat_data.csv");
        meatMapData = new LinkedHashMap<>();

        for (List<Integer> meatdatalist : meatDataCsv) {
            csvDataInteger = new ArrayList<>();
            for (Integer meatdata : meatdatalist) {
                csvDataInteger.add(meatdata);
            }
            meatMapData.put(meatNameCsv.get(cntNumberData).get(0), csvDataInteger);
            cntNumberData++;
        }

        System.out.println("meatdatacsv : " + meatDataCsv);
        System.out.println("meatnamecsv : " + meatNameCsv);
        System.out.println("meatMapData : " + meatMapData);

        //初期解のインスタンスを生成。(引数にmeatNamesArrayを指定してコンストラクタで処理)
        InitialSolution IS = new InitialSolution();
        meatMapDataList = IS.GenerateInitialsolution(meatMapData, INITIAL_SOLUTION_NUMBER);
        previousGenerationsSolution = meatMapDataList;
        //-----------------------------------------------ここからfor文(世代数＝ループ数)
        for (int i = 0; i < GENERATIONS_NUMBER; i++) {
            //OrderCrossOverクラスに引数で初期解のインスタンスを渡す
            //ランダムに親を2つ選択し、順序交叉によって2つの子の解を生成させる。
            //この操作をm回繰り返すことによって、2m個の解を生み出し、OrderCrossoverインスタンスでの配列の解のオブジェクトを2m個作成
            OrderCrossoverDataList = new ArrayList<>();
            OrderCrossover OC = new OrderCrossover();
            OrderCrossoverDataList = OC.SolutionsOrderCrossover(meatMapDataList, CROSSOVER_LOOP_NUMBER, INITIAL_SOLUTION_NUMBER);
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
            meatNameAndEvaluation = evaluation.Evaluatemeat(SolutionDataListAfterMutation, PRICE_LIMIT, FULL_DEGREE_LIMIT);

            //ここからトーナメント選択
            //解の配列solutionArrayからt個の解取り出して、一番いいものをadaptationSolutionsという配列に格納する。
            SelectSolution SS = new SelectSolution();
            nextGenerationsSolutionAndEvaluations = new LinkedHashMap<>();
            nextGenerationsSolution = SS.tournamentSelection(meatNameAndEvaluation, meatMapData, nextGenerationsSolutionAndEvaluations, TOURNAMENT_SIZE, INITIAL_SOLUTION_NUMBER);
            System.out.println("次世代の解と評価値 : " + nextGenerationsSolutionAndEvaluations);
            System.out.println("-----------------------------------------" + (i+1) + "世代目終了---------------------------------------------------------");
        }
        //------------------------------------------------ここまでfor文

        //最終的に一番いい解とそのデータを格納
        for (List<String> finalGenerationsmeatOrder : nextGenerationsSolutionAndEvaluations.keySet()) {
            if (nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder) > maxEvaluation){
                maxEvaluation = nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder);
                maxEvaluationSolution = finalGenerationsmeatOrder;
            }
        }
        System.out.println("最終解 : " + maxEvaluationSolution);
        System.out.println("目的関数値 : " + maxEvaluation);
        //初期解のインスタンスを破棄
    }

}