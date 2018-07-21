//生成した解の評価を行うクラスで計算系のメソッドを扱う


package Main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Evaluation {
    private double objectiveFunction;
    private int totalPrice;
    private int fullDegree;
    private int meatGenre;
    private boolean depulicationGenreFlg;
    private List<String> meatNameList = new ArrayList<>();
    private LinkedHashMap<List<String>, Double> meatEvaluationMap = new LinkedHashMap<>();


    public LinkedHashMap<List<String>, Double> Evaluatemeat(List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation, int PRICE_LIMIT, int FULL_DEGREE_LIMIT){
        System.out.println("--------------------------------------------------評価-------------------------------------------");
        System.out.println(SolutionDataListAfterMutation);
        this.meatEvaluationMap = new LinkedHashMap<>();
        for(LinkedHashMap<String, List<Integer>> solution : SolutionDataListAfterMutation){
            meatNameList = new ArrayList<>();
            meatNameList.addAll(solution.keySet());
            this.meatGenre = 0;
            for (String meatname : meatNameList) {
                this.depulicationGenreFlg = false;
                this.totalPrice += solution.get(meatname).get(0);
                this.fullDegree += solution.get(meatname).get(1);
                System.out.println("前のジャンル : " + this.meatGenre);
                if(this.meatGenre == solution.get(meatname).get(3)){
                    this.depulicationGenreFlg = true;
                }
                this.meatGenre = solution.get(meatname).get(3);
                System.out.println("今回のジャンル : " + this.meatGenre);

                //胃もたれ度と料金が上限を超えていなければif文の中の処理、超えていればelse文の中の処理
                if(this.totalPrice < PRICE_LIMIT && this.fullDegree < FULL_DEGREE_LIMIT){
                    //ジャンルがかぶっていた時は満足度は半減する。
                    if(this.depulicationGenreFlg){
                        this.objectiveFunction += solution.get(meatname).get(2)/2;
                    }else{
                        this.objectiveFunction += solution.get(meatname).get(2);
                    }
                    System.out.println("食べ順 : " + solution);
                    System.out.println("目的関数値 : " + this.objectiveFunction);
                    this.meatEvaluationMap.put(meatNameList, this.objectiveFunction);
                } else {
                    this.totalPrice -= solution.get(meatname).get(0);
                    this.fullDegree -= solution.get(meatname).get(1);
                    System.out.println("胃もたれ度 : " + this.fullDegree);
                    System.out.println("合計金額 : " + this.totalPrice);
                    this.totalPrice = 0;
                    this.fullDegree = 0;
                    this.objectiveFunction = 0;
                    break;
                }
                System.out.println("------------------------------内ループ終了--------------------------------------------");
            }
            System.out.println("-----------------------------------------外ループ終了------------------------------------------------------");
        }
        System.out.println(this.meatEvaluationMap);
        return this.meatEvaluationMap;
    }
}
