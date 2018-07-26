//初期解n個と生成解2m個の合計(n+2m)個の解からトーナメント方式によって解を決定する
//Ⅰ．まず(n+2m)個の解の中から、t個の解を選択する。そのt個の中から評価値の最も大きい解が生き残る。
//Ⅱ．この操作をn回行うことによって次世代の解をn個決定する。


package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class SelectSolution {
    private double Max;
    private List<List<String>> MaxObjectiveFunctionSolution = new ArrayList<>();
    private List<List<String>> meatNameLists = new ArrayList<>();
    private LinkedHashMap<List<String>, Double> tournamentMap = new LinkedHashMap<>();
    private LinkedHashMap<String, List<Integer>> nextGenerationSolution = new LinkedHashMap<>();
    private List<LinkedHashMap<String, List<Integer>>> nextGenerationSolutionList = new ArrayList<>();

    public List<LinkedHashMap<String, List<Integer>>> tournamentSelection(LinkedHashMap<List<String>, Double> meatNameAndEvaluation, LinkedHashMap<String, List<Integer>> meatMapData, LinkedHashMap<List<String>, Double> nextGenerationsSolutionAndEvaluations, int TOURNAMENT_SIZE, int NEXT_GENERATION_SOLUTION_NUMBER){
        //System.out.println("-----------------------------------------------トーナメント方式----------------------------------------------------");
        this.nextGenerationSolutionList = new ArrayList<>();
        //ココからトーナメント方式
        for(int i = 0; i < NEXT_GENERATION_SOLUTION_NUMBER; i++){
            this.meatNameLists = new ArrayList<>();
            this.tournamentMap = new LinkedHashMap<>();
            this.nextGenerationSolution = new LinkedHashMap<>();
            this.meatNameLists.addAll(meatNameAndEvaluation.keySet());
            //System.out.println("シャッフル前 : " + this.meatNameLists);
            Collections.shuffle(this.meatNameLists);
            //System.out.println("シャッフル後 : " + this.meatNameLists);
            for(int j = 0; j < TOURNAMENT_SIZE; j++){
                this.tournamentMap.put(this.meatNameLists.get(j), meatNameAndEvaluation.get(this.meatNameLists.get(j)));
            }
            //System.out.println("トーナメントメンバー : " + this.tournamentMap);
            this.Max = 0;
            for (List solutionlist : this.tournamentMap.keySet()) {
                if(meatNameAndEvaluation.get(solutionlist) > Max){
                    this.Max = meatNameAndEvaluation.get(solutionlist);
                    this.MaxObjectiveFunctionSolution.add(0, solutionlist);
                }
            }
            nextGenerationsSolutionAndEvaluations.put(this.MaxObjectiveFunctionSolution.get(0), this.Max);
            //最も適応度(目的関数値)の高い解の順番が一番前に来るようになっているので、Listの0番目を取り出せばよい。
            //System.out.println("生き残り解 : " + this.MaxObjectiveFunctionSolution.get(0));
            for (String meatname : this.MaxObjectiveFunctionSolution.get(0)) {
                this.nextGenerationSolution.put(meatname, meatMapData.get(meatname));
            }
            //System.out.println("次世代の解の一つ : " + this.nextGenerationSolution);
            this.nextGenerationSolutionList.add(this.nextGenerationSolution);
            //System.out.println("次世代の解集合 : " + this.nextGenerationSolutionList);
            //System.out.println("------------------------------------トーナメント一周終了---------------------------------------------");
        }
        return this.nextGenerationSolutionList;
    }
}
