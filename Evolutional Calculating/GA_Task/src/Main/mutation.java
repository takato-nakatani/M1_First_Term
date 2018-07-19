//突然変異のメソッド。確率的に解を変異させる。変異の方法は転座を使う予定。

package Main;

import java.util.*;

public class Mutation {

    private int cnt = 0;
    private int mutationGene1;
    private int mutationGene2;
    private Random random = new Random();
    private List<LinkedHashMap<String, List<Integer>>> SolutionDataListAfterMutation = new ArrayList<>();
    private LinkedHashMap<Integer, String> meetNameMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, String> meetNameMapCopy = new LinkedHashMap<>();
    private List<Integer> meetNameNumber = new ArrayList<>();
    private List<LinkedHashMap<String, List<Integer>>> meetDataMapList = new ArrayList<>();
    private LinkedHashMap<String, List<Integer>> afterMutation = new LinkedHashMap<>();

    public List<LinkedHashMap<String, List<Integer>>> MutationGene(List<LinkedHashMap<String, List<Integer>>> SolutionDataList, int MUTATION_PROBABILITY){
        //解のリストであるSolutionDataListの各要素に対してmutationを確率的に行う。
        this.SolutionDataListAfterMutation = new ArrayList<>();
        for(LinkedHashMap<String, List<Integer>> solution : SolutionDataList){
            if(this.random.nextInt(100) < MUTATION_PROBABILITY){
                this.meetNameMap = new LinkedHashMap<>();
                this.meetNameMapCopy = new LinkedHashMap<>();
                for(int i = 0; i < solution.size(); i++){
                    for(String keyname : solution.keySet()){
                        if(i == this.cnt){
                            this.meetNameMap.put(i, keyname);
                            this.meetNameMapCopy.put(i, keyname);
                            this.cnt = 0;
                            break;
                        }
                        this.cnt++;
                    }
                }
                System.out.println("マップ(数値⇒肉名)　：　" + this.meetNameMap);
                this.meetDataMapList = new ArrayList<>();
                this.meetDataMapList.add(solution);
                System.out.println("リスト(マップ{肉名⇒肉データ})　：　" + this.meetDataMapList);
                this.meetNameNumber = new ArrayList<>();
                for(int k = 0; k < solution.size(); k++){
                    meetNameNumber.add(k);
                }
                Collections.shuffle(this.meetNameNumber);
                this.mutationGene1 = this.meetNameNumber.get(0);
                this.mutationGene2 = this.meetNameNumber.get(1);
                this.meetNameMap.replace(this.mutationGene1, this.meetNameMapCopy.get(this.mutationGene2));
                this.meetNameMap.replace(this.mutationGene2, this.meetNameMapCopy.get(this.mutationGene1));
                System.out.println("突然変異1箇所目 : " + this.mutationGene1);
                System.out.println("突然変異2箇所目 : " + this.mutationGene2);
                System.out.println("突然変異前の解 : " + this.meetNameMapCopy);
                System.out.println("突然変異後の解 : " + this.meetNameMap);
                this.afterMutation = new LinkedHashMap<>();
                for(int n = 0; n < solution.size(); n++){
                    this.afterMutation.put(this.meetNameMap.get(n), solution.get(this.meetNameMap.get(n)));
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
