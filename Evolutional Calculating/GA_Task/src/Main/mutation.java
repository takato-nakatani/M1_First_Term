//突然変異のメソッド。確率的に解を変異させる。変異の方法は転座を使う予定。

package Main;

import java.util.*;

public class Mutation {

    private int cnt = 0;
    private int mutationGene1;
    private int mutationGene2;
    private Random random = new Random();
    private List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation = new ArrayList<>();
    private LinkedHashMap<Integer, String> meatNameMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, String> meatNameMapCopy = new LinkedHashMap<>();
    private List<Integer> meatNameNumber = new ArrayList<>();
    private List<LinkedHashMap<String, List<Integer>>> meatDataMapList = new ArrayList<>();
    private LinkedHashMap<String, List<Integer>> afterMutation = new LinkedHashMap<>();

    public List<LinkedHashMap<String, List<Integer>>> MutationGene(List<LinkedHashMap<String, List<Integer>>> SolutionDataList, int MUTATION_PROBABILITY){
        //解のリストであるSolutionDataListの各要素に対してmutationを確率的に行う。
        this.SolutionDataListAfterMutation = new ArrayList<>();
        for(LinkedHashMap<String, List<Integer>> solution : SolutionDataList){
            if(this.random.nextInt(100) < MUTATION_PROBABILITY){
                this.meatNameMap = new LinkedHashMap<>();
                this.meatNameMapCopy = new LinkedHashMap<>();
                for(int i = 0; i < solution.size(); i++){
                    for(String keyname : solution.keySet()){
                        if(i == this.cnt){
                            this.meatNameMap.put(i, keyname);
                            this.meatNameMapCopy.put(i, keyname);
                            this.cnt = 0;
                            break;
                        }
                        this.cnt++;
                    }
                }
                System.out.println("マップ(数値⇒肉名)　：　" + this.meatNameMap);
                this.meatDataMapList = new ArrayList<>();
                this.meatDataMapList.add(solution);
                System.out.println("リスト(マップ{肉名⇒肉データ})　：　" + this.meatDataMapList);
                this.meatNameNumber = new ArrayList<>();
                for(int k = 0; k < solution.size(); k++){
                    meatNameNumber.add(k);
                }
                Collections.shuffle(this.meatNameNumber);
                this.mutationGene1 = this.meatNameNumber.get(0);
                this.mutationGene2 = this.meatNameNumber.get(1);
                this.meatNameMap.replace(this.mutationGene1, this.meatNameMapCopy.get(this.mutationGene2));
                this.meatNameMap.replace(this.mutationGene2, this.meatNameMapCopy.get(this.mutationGene1));
                System.out.println("突然変異1箇所目 : " + this.mutationGene1);
                System.out.println("突然変異2箇所目 : " + this.mutationGene2);
                System.out.println("突然変異前の解 : " + this.meatNameMapCopy);
                System.out.println("突然変異後の解 : " + this.meatNameMap);
                this.afterMutation = new LinkedHashMap<>();
                for(int n = 0; n < solution.size(); n++){
                    this.afterMutation.put(this.meatNameMap.get(n), solution.get(this.meatNameMap.get(n)));
                }
                System.out.println(this.afterMutation);
                this.SolutionDataListAfterMutation.add(this.afterMutation);
                continue;
            }
            this.SolutionDataListAfterMutation.add(solution);
            System.out.println("-----------------------------------突然変異1周しました-------------------------------------");
        }
        System.out.println("突然変異前の解集合 : " + SolutionDataList);
        System.out.println("突然変異後の解集合 : " + this.SolutionDataListAfterMutation);
        return this.SolutionDataListAfterMutation;
    }
}
