//生成した解の評価を行うクラスで計算系のメソッドを扱う


package Main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Evaluation {
    private int objectiveFunction;
    private int totalPrice;
    private int fullDegree;
    private List<String> MeetNameList = new ArrayList<>();
    private LinkedHashMap<List<String>, Integer> meetEvaluationMap = new LinkedHashMap<>();


    public LinkedHashMap<List<String>, Integer> EvaluateMeet(List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation, int PRICE_LIMIT, int FULL_DEGREE_LIMIT){
        System.out.println("--------------------------------------------------評価-------------------------------------------");
        System.out.println(SolutionDataListAfterMutation);
        this.meetEvaluationMap = new LinkedHashMap<>();
        for(LinkedHashMap<String, List<Integer>> solution : SolutionDataListAfterMutation){
            MeetNameList = new ArrayList<>();
            MeetNameList.addAll(solution.keySet());
            for (String meetname : MeetNameList) {
                this.totalPrice += solution.get(meetname).get(0);
                this.fullDegree += solution.get(meetname).get(1);

                //胃もたれ度と料金が上限を超えていなければif文の中の処理、超えていればelse文の中の処理
                if(this.totalPrice < PRICE_LIMIT && this.fullDegree < FULL_DEGREE_LIMIT){
                    this.objectiveFunction += solution.get(meetname).get(2);
                    System.out.println("食べ順 : " + solution);
                    System.out.println("目的関数値 : " + this.objectiveFunction);
                    this.meetEvaluationMap.put(MeetNameList, this.objectiveFunction);
                } else {
                    System.out.println("胃もたれ度 : " + this.fullDegree);
                    System.out.println("合計金額 : " + this.totalPrice);
                    this.totalPrice = 0;
                    this.fullDegree = 0;
                    this.objectiveFunction = 0;
                    break;
                }
            }
        }
        System.out.println(this.meetEvaluationMap);
        return this.meetEvaluationMap;
    }
}
