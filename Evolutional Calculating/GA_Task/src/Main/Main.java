package Main;

import java.util.*;

public class Main {
    //初期解(親となる遺伝子)の個数
    private static final int INITIAL_SOLUTION_NUMBER = 10;
    //    private static final int INITIAL_SOLUTION_NUMBER = 10;
    //交叉回数
//    private static final int CROSSOVER_LOOP_NUMBER = 20;
    private static final int CROSSOVER_LOOP_NUMBER = 20;
    //突然変異の確率
//    private static final int MUTATION_PROBABILITY = 10;
    private static final int MUTATION_PROBABILITY = 20;
    //合計金額の上限
//    private static final int PRICE_LIMIT = 9000;
    private static final int PRICE_LIMIT = 9000;
    //胃もたれ度の上限
//    private static final int STOMACH_HEAVY_DEGREE_LIMIT = 42;
    private static final int STOMACH_HEAVY_DEGREE_LIMIT = 42;
    //トーナメントサイズ
//    private static final int TOURNAMENT_SIZE = 3;
    private static final int TOURNAMENT_SIZE = 3;
    //世代数
    private static final int GENERATIONS_NUMBER = 1000;
//    //初期解(親となる遺伝子)の個数
//    private static final int INITIAL_SOLUTION_NUMBER = 2;
//    //交叉回数
//    private static final int CROSSOVER_LOOP_NUMBER = 3;
//    //突然変異の確率
//    private static final int MUTATION_PROBABILITY = 10;
//    //合計金額の上限
//    private static final int PRICE_LIMIT = 7000;
//    //胃もたれ度の上限
//    private static final int STOMACH_HEAVY_DEGREE_LIMIT = 35;
//    //トーナメントサイズ
//    private static final int TOURNAMENT_SIZE = 2;
//    //世代数
//    private static final int GENERATIONS_NUMBER = 2;

    public static int cntNumberData = 0;
    public static double maxEvaluationFinalSolution = 0;
    public static double maxEvaluationInitiationSolution;
    public static List<String> maxEvaluationSolutionList = new ArrayList<>();
    public static List<Integer> csvDataInteger = new ArrayList<>();
    public static List<List<Integer>> meatDataCsv = new ArrayList<>();
    public static List<List<String>> meatNameCsv = new ArrayList<>();
    public static List<String> InitialOptimalSolution = new ArrayList<>();
    public static LinkedHashMap<String, List<Integer>> meatMapData = new LinkedHashMap<>();
    public static List<LinkedHashMap<String, List<Integer>>> meatMapDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> OrderCrossoverDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> previousGenerationsSolution = new ArrayList<>();
    public static List<LinkedHashMap<String, List<Integer>>> nextGenerationsSolution = new ArrayList<>();
    public static LinkedHashMap<List<String>, Double> nextGenerationsSolutionAndEvaluations = new LinkedHashMap<>();
    public static LinkedHashMap<List<String>, Double> meatNameAndEvaluation = new LinkedHashMap<>();
    public static LinkedHashMap<List<String>, Double> meatNameAndEvaluationInitial = new LinkedHashMap<>();

    public static void main(String[] args){
        //MapのString値に肉の種類の名前を、Listに肉の値段と胃もたれ度と満足度を格納

        //Mapした名前を格納したListにmeatNamesArrayを作成する。
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

        //System.out.println("meatdatacsv : " + meatDataCsv);
        //System.out.println("meatnamecsv : " + meatNameCsv);
        //System.out.println("meatMapData : " + meatMapData);

        //初期解のインスタンスを生成。(引数にmeatNamesArrayを指定してコンストラクタで処理)
        InitialSolution IS = new InitialSolution();
        meatMapDataList = IS.GenerateInitialsolution(meatMapData, INITIAL_SOLUTION_NUMBER);
//        //System.out.println("初期解 : " + meatMapDataList);
        //-----------------------------------------------ここからfor文(世代数＝ループ数)
        for (int i = 0; i < GENERATIONS_NUMBER; i++) {
            if(i == 25){
                for (List<String> finalGenerationsmeatOrder : nextGenerationsSolutionAndEvaluations.keySet()) {
                    if (nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder) > maxEvaluationFinalSolution){
                        maxEvaluationFinalSolution = nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder);
                        maxEvaluationSolutionList = finalGenerationsmeatOrder;
                    }
                }
                System.out.println(i + "世代目の解 : " + maxEvaluationSolutionList);
                System.out.println(i + "世代目の解の目的関数値" + maxEvaluationFinalSolution);

            }
            System.out.println("-----------------------------------------" + (i+1) + "世代目開始---------------------------------------------------------");
//            //System.out.println("前世代からの引継ぎ : " + previousGenerationsSolution);
            //OrderCrossOverクラスに引数で初期解のインスタンスを渡す
            //ランダムに親を2つ選択し、順序交叉によって2つの子の解を生成させる。
            //この操作をm回繰り返すことによって、2m個の解を生み出し、OrderCrossoverインスタンスでの配列の解のオブジェクトを2m個作成
            OrderCrossoverDataList = new ArrayList<>();
            OrderCrossover OC = new OrderCrossover();
            OrderCrossoverDataList = OC.SolutionsOrderCrossover(meatMapDataList, CROSSOVER_LOOP_NUMBER, INITIAL_SOLUTION_NUMBER);
            //初期解initialSolutionオブジェクト(n個)と順序交叉OrderCrossoverオブジェクトによって生成された解(2m個)を組み合わせて解のリストSolutionDataListを作成し格納する。
            SolutionDataList = new ArrayList<>();
            SolutionDataList.addAll(previousGenerationsSolution);
            previousGenerationsSolution = new ArrayList<>();
            SolutionDataList.addAll(OrderCrossoverDataList);
            //解のリストであるSolutionDataListの各要素に対してmutationを確率的に行う。
            Mutation Mutation = new Mutation();
            SolutionDataListAfterMutation = Mutation.MutationGene(SolutionDataList, MUTATION_PROBABILITY);
            //solutionArrayの各配列に対して、2つの制約条件(値段と胃もたれ度)を満たす解の範囲を決定し、解の有効範囲の終端の要素番号を取得し、effectiveRangeという変数に格納
            //solutionArrayのeffectiveRangeまでの範囲の各要素を目的関数によって評価する。
            Evaluation evaluation = new Evaluation();
            meatNameAndEvaluation = evaluation.Evaluatemeat(SolutionDataListAfterMutation, PRICE_LIMIT, STOMACH_HEAVY_DEGREE_LIMIT);
            System.out.println(meatNameAndEvaluation);
            //ここからトーナメント選択
            //解の配列solutionArrayからt個の解取り出して、一番いいものをadaptationSolutionsという配列に格納する。
            SelectSolution SS = new SelectSolution();
            nextGenerationsSolutionAndEvaluations = new LinkedHashMap<>();
            nextGenerationsSolution = SS.tournamentSelection(meatNameAndEvaluation, meatMapData, nextGenerationsSolutionAndEvaluations, TOURNAMENT_SIZE, INITIAL_SOLUTION_NUMBER);
            System.out.println("次世代の解と評価値 : " + nextGenerationsSolutionAndEvaluations);
            //次の世代に解を引き継ぐ
            previousGenerationsSolution = nextGenerationsSolution;
            //System.out.println("解 : " + nextGenerationsSolution);
            //System.out.println("解の個数 : " + nextGenerationsSolution.size());
            System.out.println("-----------------------------------------" + (i+1) + "世代目終了---------------------------------------------------------");
        }
        //------------------------------------------------ここまでfor文

        //最終的に一番いい解とそのデータを格納
        for (List<String> finalGenerationsmeatOrder : nextGenerationsSolutionAndEvaluations.keySet()) {
            if (nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder) > maxEvaluationFinalSolution){
                maxEvaluationFinalSolution = nextGenerationsSolutionAndEvaluations.get(finalGenerationsmeatOrder);
                maxEvaluationSolutionList = finalGenerationsmeatOrder;
            }
        }

        Evaluation evaluationinitial= new Evaluation();
        meatNameAndEvaluationInitial = evaluationinitial.Evaluatemeat(meatMapDataList, PRICE_LIMIT, STOMACH_HEAVY_DEGREE_LIMIT);
        double objective_function;
            for (List<String> initialsolutionmeatorder : meatNameAndEvaluationInitial.keySet()){
                objective_function = meatNameAndEvaluationInitial.get(initialsolutionmeatorder);
                if (maxEvaluationInitiationSolution < objective_function){
                    maxEvaluationInitiationSolution = objective_function;
                    InitialOptimalSolution = initialsolutionmeatorder;
                }
            }
        System.out.println("初期解 : " + InitialOptimalSolution);
        System.out.println("初期解の目的関数値 : " + maxEvaluationInitiationSolution);
        System.out.println("最終解 : " + maxEvaluationSolutionList);
        System.out.println("最終解の目的関数値 : " + maxEvaluationFinalSolution);
    }

}