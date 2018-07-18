//初期解を生成。
//Ⅰ．ランダムの乱数でソート回数を適当に決めて、その回数ソートさせてランダムに初期個体生成。(ランダムソート的な)
//Ⅱ．Ⅰの方法でn個初期個体を生成
package Main;

import java.util.*;

public class InitialSolution {
    private LinkedHashMap<String, List> meetMapDataAftersort = new LinkedHashMap<>();
    private List<String> meetNameData = new ArrayList<>();
    private List<List<LinkedHashMap<String, List>>> initialSolutionsList = new ArrayList<>();
    private List<LinkedHashMap<String, List>> meetMapDataAftersortList = new ArrayList<>();
//    public LinkedHashMap<String, List> meetMapData;
//    public LinkedHashMap<String, List> meetMapDataAftersort;
//    public List<String> meetNameData;
//    public List<LinkedHashMap> initialSolutionsList;

//    public InitialSolution(Map<String, Object> meetMapData){
//        this.meetMapData = meetMapData;
//    }

    //適当な1つの解から引数で与えるsolutionNumber個の初期解を返すメソッド
    public List<List<LinkedHashMap<String, List>>> GenerateInitialsolution(LinkedHashMap<String, List> meetMapData, int solutionNumber) {
        this.meetNameData.addAll(meetMapData.keySet());
//        for (String meetName : meetMapData.keySet()) {
//            this.meetNameData.add(meetName);
//        }
        System.out.println(this.meetNameData + "あ");
        for(int i = 0; i < solutionNumber; i++) {
//            this.initialSolutionsList = new ArrayList<>();
            Collections.shuffle(this.meetNameData);
            meetMapDataAftersortList = new ArrayList<>();
            for(int j = 0; j < meetNameData.size(); j++){
                this.meetMapDataAftersort = new LinkedHashMap<>();
                this.meetMapDataAftersort.put(this.meetNameData.get(j), meetMapData.get(this.meetNameData.get(j)));
                this.meetMapDataAftersortList.add(this.meetMapDataAftersort);
            }
            this.initialSolutionsList.add(this.meetMapDataAftersortList);
        }

        //返すリストの形List{[0] -> Map{[肉の名前], List[肉のデータ]}, [1] -> …}
        System.out.println(this.initialSolutionsList);
        System.out.println("-------------------------------------------初期解------------------------------------------");
        return this.initialSolutionsList;
    }



}
