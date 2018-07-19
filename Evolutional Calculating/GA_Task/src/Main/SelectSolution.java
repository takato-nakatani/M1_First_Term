//初期解n個と生成解2m個の合計(n+2m)個の解からトーナメント方式によって解を決定する
//Ⅰ．まず(n+2m)個の解の中から、t個の解を選択する。そのt個の中から評価値の最も大きい解が生き残る。
//Ⅱ．この操作をn回行うことによって次世代の解をn個決定する。


package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class SelectSolution {
    private int Max;
    private List<List<String>> MaxObjectiveFunctionSolution = new ArrayList<>();
    private List<List<String>> meetNameLists = new ArrayList<>();
    private LinkedHashMap<List<String>, Integer> tournamentMap = new LinkedHashMap<>();
    private LinkedHashMap<String, List<Integer>> nextGenerationSolution = new LinkedHashMap<>();
    private List<LinkedHashMap<String, List<Integer>>> nextGenerationSolutionList = new ArrayList<>();

    public List<LinkedHashMap<String, List<Integer>>> tournamentSelection(LinkedHashMap<List<String>, Integer> meetNameAndEvaluation, LinkedHashMap<String, List<Integer>> meetMapData, int TOURNAMENT_SIZE, int NEXT_GENERATION_SOLUTION_NUMBER){
        System.out.println("-----------------------------------------------トーナメント方式----------------------------------------------------");
        this.nextGenerationSolutionList = new ArrayList<>();
        //ココからトーナメント方式
        for(int i = 0; i < NEXT_GENERATION_SOLUTION_NUMBER; i++){
            this.meetNameLists = new ArrayList<>();
            this.tournamentMap = new LinkedHashMap<>();
            this.nextGenerationSolution = new LinkedHashMap<>();
            this.meetNameLists.addAll(meetNameAndEvaluation.keySet());
            System.out.println("シャッフル前 : " + this.meetNameLists);
            Collections.shuffle(this.meetNameLists);
            System.out.println("シャッフル後 : " + this.meetNameLists);
            for(int j = 0; j < TOURNAMENT_SIZE; j++){
                this.tournamentMap.put(this.meetNameLists.get(j), meetNameAndEvaluation.get(this.meetNameLists.get(j)));
            }
            System.out.println("トーナメントメンバー : " + this.tournamentMap);
            this.Max = 0;
            for (List solutionlist : this.tournamentMap.keySet()) {
                if(meetNameAndEvaluation.get(solutionlist) > Max){
                    this.Max = meetNameAndEvaluation.get(solutionlist);
                    this.MaxObjectiveFunctionSolution.add(0, solutionlist);
                }
            }
            //最も適応度(目的関数値)の高い解の順番が一番前に来るようになっているので、Listの0番目を取り出せばよい。
            System.out.println("生き残り解 : " + this.MaxObjectiveFunctionSolution.get(0));
            for (String meetname : this.MaxObjectiveFunctionSolution.get(0)) {
                this.nextGenerationSolution.put(meetname, meetMapData.get(meetname));
            }
            System.out.println("次世代の解の一つ : " + this.nextGenerationSolution);
            this.nextGenerationSolutionList.add(this.nextGenerationSolution);
            System.out.println("次世代の解集合 : " + this.nextGenerationSolution);
            System.out.println("------------------------------------トーナメント一周終了---------------------------------------------");
        }

        return this.nextGenerationSolutionList;
    }
}
