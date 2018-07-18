package Main;

import java.util.*;

public class Main {
    public static LinkedHashMap<String, List> meetMapData = new LinkedHashMap<String, List>();
    public static List<Object> meetData = new ArrayList<Object>();
    public static List<Object> meetData2 = new ArrayList<Object>();
    public static List<Object> meetData3 = new ArrayList<Object>();
    public static List<List<LinkedHashMap<String, List>>> meetMapDataList = new ArrayList<List<LinkedHashMap<String, List>>>();
    public static List<List<LinkedHashMap>> OrderCrossoverDataList = new ArrayList<List<LinkedHashMap>>();

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
        meetMapDataList = IS.GenerateInitialsolution(meetMapData, 4);
        OrderCrossover OC = new OrderCrossover();
        OC.SolutionsOrderCrossover(meetMapDataList, 2);
        //-----------------------------------------------ここからfor文(世代数＝ループ数)
        //OrderCrossOverクラスに引数で初期解のインスタンスを渡す
        //ランダムに親を2つ選択し、順序交叉によって2つの子の解を生成させる。
        //この操作をm回繰り返すことによって、2m個の解を生み出し、OrderCrossoverインスタンスでの配列の解のオブジェクトを2m個作成

        //初期解initialSolutionオブジェクト(n個)と順序交叉OrderCrossoverオブジェクトによって選択された解(2m個)を組み合わせて解の配列solutionArrayを作成し格納する。

        //解の配列であるsolutionArrayの各要素に対してmutationを確率的に行う。

        //solutionArrayの各配列に対して、2つの制約条件(値段と胃もたれ度)を満たす解の範囲を決定し、解の有効範囲の終端の要素番号を取得し、effectiveRangeという変数に格納

        //solutionArrayのeffectiveRangeまでの範囲の各要素を目的関数によって評価する。

        //ここからトーナメント選択
        //解の配列solutionArrayからt個の解取り出して、一番いいものをadaptationSolutionsという配列に格納する。

        //------------------------------------------------ここまでfor文

        //初期解のインスタンスを破棄

    }

}
