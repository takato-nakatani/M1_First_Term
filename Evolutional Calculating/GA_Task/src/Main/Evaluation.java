//生成した解の評価を行うクラスで計算系のメソッドを扱う


package Main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Evaluation {
    private double objectiveFunction;
    private int cnt;
    private int totalPrice;
    private int fullDegree;
    private int meatGenre;
    private String previousmeatname;
    private boolean depulicationGenreFlg;
    private boolean goodcombinationFlg;
    private boolean goodMatchingBeforeRiceFlg;
    private boolean badMatchingBeforeRiceFlg;
    private boolean goodMatchingAfterRiceFlg;
    private boolean badMatchingAfterRiceFlg;
    private List<String> meatNameList = new ArrayList<>();
    private LinkedHashMap<List<String>, Double> meatEvaluationMap = new LinkedHashMap<>();


    public LinkedHashMap<List<String>, Double> Evaluatemeat(List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation, int PRICE_LIMIT, int STOMACH_HEAVY_DEGREE_LIMIT){
        //System.out.println("--------------------------------------------------評価-------------------------------------------");
        //System.out.println(SolutionDataListAfterMutation);
        this.meatEvaluationMap = new LinkedHashMap<>();
        for(LinkedHashMap<String, List<Integer>> solution : SolutionDataListAfterMutation){
            meatNameList = new ArrayList<>();
            meatNameList.addAll(solution.keySet());
            this.meatGenre = 0;
            this.cnt = 1;
            for (String meatname : meatNameList) {
                this.depulicationGenreFlg = false;
                this.goodcombinationFlg = false;
                this.goodMatchingBeforeRiceFlg = false;
                this.badMatchingBeforeRiceFlg = false;
                this.goodMatchingAfterRiceFlg = false;
                this.badMatchingAfterRiceFlg = false;
                this.totalPrice += solution.get(meatname).get(0);
                this.fullDegree += solution.get(meatname).get(1);
                //System.out.println("前のジャンル : " + this.meatGenre);
                if(this.meatGenre == solution.get(meatname).get(3)){
                    this.depulicationGenreFlg = true;
                }else if(this.meatGenre == 7 && solution.get(meatname).get(3) == 12){
                    this.goodcombinationFlg = true;
                }else if((this.meatGenre == 1 || this.meatGenre == 3) && solution.get(meatname).get(3) == 13){
                    this.goodMatchingBeforeRiceFlg = true;
                }else if(this.meatGenre == 13 && (solution.get(meatname).get(3) == 1 || solution.get(meatname).get(3) == 3)){
                    this.goodMatchingAfterRiceFlg = true;
                }else if ((this.meatGenre ==13) && (solution.get(meatname).get(3) == 1 || solution.get(meatname).get(3) == 3)){
                    this.goodMatchingAfterRiceFlg = true;
                }else if(this.meatGenre == 12 && solution.get(meatname).get(3) == 13){
                    this.badMatchingBeforeRiceFlg = true;
                }else if(this.meatGenre == 13 && solution.get(meatname).get(3) == 12){
                    this.badMatchingAfterRiceFlg = true;
                }
                this.meatGenre = solution.get(meatname).get(3);
                //System.out.println("今回のジャンル : " + this.meatGenre);

                //胃もたれ度と料金が上限を超えていなければif文の中の処理、超えていればelse文の中の処理
                if(this.totalPrice < PRICE_LIMIT && this.fullDegree < STOMACH_HEAVY_DEGREE_LIMIT){
                    this.cnt++;
                    //ジャンルがかぶっていた時は満足度は半減する。
                    if(this.depulicationGenreFlg){
                        this.objectiveFunction += solution.get(meatname).get(2)/2;
                    }else if(this.goodcombinationFlg){
                        this.objectiveFunction += solution.get(meatname).get(2) + solution.get(this.previousmeatname).get(2);
                    }else if(this.goodMatchingBeforeRiceFlg){
                        this.objectiveFunction += solution.get(meatname).get(2) + solution.get(this.previousmeatname).get(2);
                    }else if(this.goodMatchingAfterRiceFlg){
                        this.objectiveFunction += solution.get(meatname).get(2) * 2;
                    }else if(this.badMatchingBeforeRiceFlg || this.badMatchingAfterRiceFlg){
                        this.objectiveFunction += solution.get(meatname).get(2) - 20;
                    }else{
                        this.objectiveFunction += solution.get(meatname).get(2);
                    }
                    //System.out.println("食べ順 : " + solution);
                    //System.out.println("目的関数値 : " + this.objectiveFunction);
                    this.meatEvaluationMap.put(meatNameList, this.objectiveFunction);
                    this.previousmeatname = meatname;
                } else {
                    this.totalPrice -= solution.get(meatname).get(0);
                    this.fullDegree -= solution.get(meatname).get(1);
                    //System.out.println("胃もたれ度 : " + this.fullDegree);
                    //System.out.println("合計金額 : " + this.totalPrice);
                    this.totalPrice = 0;
                    this.fullDegree = 0;
                    this.objectiveFunction = 0;
                    break;
                }
                //System.out.println("------------------------------内ループ終了--------------------------------------------");
            }
            //System.out.println("-----------------------------------------外ループ終了------------------------------------------------------");
        }
        //System.out.println(this.meatEvaluationMap);
        return this.meatEvaluationMap;
    }
}
