package Main;

import java.util.*;

public class Main {
    private static final int INITIAL_SOLUTION_NUMBER = 2;
    private static final int CROSSOVER_LOOP_NUMBER = 2;
    private static final int ONE_GENERATION_SOLUTION_NUMBER = INITIAL_SOLUTION_NUMBER + 2 * CROSSOVER_LOOP_NUMBER;
    private static final int MUTATION_PROBABILITY = 5;
    public static int cnt = 0;
    public static int valueOrder = 0;
    public static int mutationGene1;
    public static int mutationGene2;
    public static List<Integer> meetNameNumber = new ArrayList<>();
    public static LinkedHashMap<Integer, String> meetNameMap = new LinkedHashMap<>();
    public static LinkedHashMap<Integer, String> meetNameMapCopy = new LinkedHashMap<>();
    public static List<LinkedHashMap<String, List>> meetDataMapList = new ArrayList<>();
    public static LinkedHashMap<String, List> meetMapData = new LinkedHashMap<>();
    public static List<Object> meetData = new ArrayList<>();
    public static List<Object> meetData2 = new ArrayList<>();
    public static List<Object> meetData3 = new ArrayList<>();
    public static List<LinkedHashMap<String, List>> meetMapDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List>> OrderCrossoverDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List>> SolutionDataList = new ArrayList<>();
    public static List<LinkedHashMap<String, List>> SolutionDataListAfterMutation = new ArrayList<>();
    public static List<LinkedHashMap<String, List>> previousGenerationsSolution = new ArrayList<>();
    public static LinkedHashMap<String, List> afterMutation = new LinkedHashMap<>();
    public static Random random = new Random();

    public static void main(String[] args){
        //MapのString値に肉の種類の名前を、List値に肉の値段と胃もたれ度と満足度を格納

        //Mapした名前を格納した配列meetNamesArrayを作成する。

        meetData.add(980);
        meetData.add(4);
        meetData.add(5);
        meetMapData.put("カルビ", meetData);
        meetData2.add(1400);
        meetData2.add(6);
        meetData2.add(7);
        meetMapData.put("上カルビ", meetData2);
        meetData3.add(1800);
        meetData3.add(9);
        meetData3.add(9);
        meetMapData.put("ザブトン", meetData3);
        System.out.println(meetMapData);

        //初期解のインスタンスを生成。(引数にmeetNamesArrayを指定してコンストラクタで処理)
        InitialSolution IS = new InitialSolution();
        meetMapDataList = IS.GenerateInitialsolution(meetMapData, INITIAL_SOLUTION_NUMBER);
        previousGenerationsSolution = meetMapDataList;
        //-----------------------------------------------ここからfor文(世代数＝ループ数)
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
        System.out.println("突然変異前 : " + SolutionDataList);

        //解のリストであるSolutionDataListの各要素に対してmutationを確率的に行う。
        SolutionDataListAfterMutation = new ArrayList<>();
        for(LinkedHashMap<String, List> solution : SolutionDataList){

            if(random.nextInt(100) < 100){
                meetNameMap = new LinkedHashMap<>();
                meetNameMapCopy = new LinkedHashMap<>();
                for(int i = 0; i < solution.size(); i++){
                    for(String keyname : solution.keySet()){
                        if(i == cnt){
                            meetNameMap.put(i, keyname);
                            meetNameMapCopy.put(i, keyname);
                            cnt = 0;
                            break;
                        }
                        cnt++;
                    }
                }
                System.out.println("マップ　：　" + meetNameMap);
                meetDataMapList = new ArrayList<>();
                meetDataMapList.add(solution);
                System.out.println("リスト　：　" + meetDataMapList);
                meetNameNumber = new ArrayList<>();
                for(int k = 0; k < solution.size(); k++){
                    meetNameNumber.add(k);
                }
                System.out.println(meetNameNumber);
                Collections.shuffle(meetNameNumber);
                mutationGene1 = meetNameNumber.get(0);
                mutationGene2 = meetNameNumber.get(1);
                meetNameMap.replace(mutationGene1, meetNameMapCopy.get(mutationGene2));
                meetNameMap.replace(mutationGene2, meetNameMapCopy.get(mutationGene1));
                System.out.println(mutationGene1);
                System.out.println(mutationGene2);
                System.out.println(meetNameMap);
                afterMutation = new LinkedHashMap<>();
                for(int n = 0; n < solution.size(); n++){
                    afterMutation.put(meetNameMap.get(n), solution.get(meetNameMap.get(n)));
                }
                System.out.println(afterMutation);
                SolutionDataListAfterMutation.add(afterMutation);
                continue;
            }
            SolutionDataListAfterMutation.add(solution);
        }
        System.out.println("突然変異前 : " + SolutionDataList);
        System.out.println("突然変異後 : " + SolutionDataListAfterMutation);
        //solutionArrayの各配列に対して、2つの制約条件(値段と胃もたれ度)を満たす解の範囲を決定し、解の有効範囲の終端の要素番号を取得し、effectiveRangeという変数に格納
        
        //solutionArrayのeffectiveRangeまでの範囲の各要素を目的関数によって評価する。

        //ここからトーナメント選択
        //解の配列solutionArrayからt個の解取り出して、一番いいものをadaptationSolutionsという配列に格納する。

        //------------------------------------------------ここまでfor文

        //初期解のインスタンスを破棄

    }

}
