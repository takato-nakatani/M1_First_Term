//初期解を生成。
//Ⅰ．ランダムの乱数でソート回数を適当に決めて、その回数ソートさせてランダムに初期個体生成。(ランダムソート的な)
//Ⅱ．Ⅰの方法でn個初期個体を生成
package Main;

import java.util.*;

public class InitialSolution {
    private LinkedHashMap<String, List<Integer>> meetMapDataAftersort = new LinkedHashMap<>();
    private List<String> meetNameData = new ArrayList<>();
    private List<LinkedHashMap<String, List<Integer>>> initialSolutionsList = new ArrayList<>();

    //適当な1つの解から引数で与えるsolutionNumber個の初期解を返すメソッド
    public List<LinkedHashMap<String, List<Integer>>> GenerateInitialsolution(LinkedHashMap<String, List<Integer>> meetMapData, int initialsolutionNumber) {
        this.meetNameData.addAll(meetMapData.keySet());
        System.out.println(this.meetNameData + "あ");
        for(int i = 0; i < initialsolutionNumber; i++) {
            Collections.shuffle(this.meetNameData);
            this.meetMapDataAftersort = new LinkedHashMap<>();
            for(int j = 0; j < meetNameData.size(); j++){
                this.meetMapDataAftersort.put(this.meetNameData.get(j), meetMapData.get(this.meetNameData.get(j)));
            }
            this.initialSolutionsList.add(this.meetMapDataAftersort);
        }
        //返すリストの形List{[0] -> Map{[肉の名前], List[肉のデータ]}, [1] -> …}
        System.out.println(this.initialSolutionsList);
        System.out.println("-------------------------------------------初期解------------------------------------------");
        return this.initialSolutionsList;
    }
}
