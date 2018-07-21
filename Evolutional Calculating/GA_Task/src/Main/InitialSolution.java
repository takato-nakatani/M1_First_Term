//初期解を生成。
//Ⅰ．ランダムの乱数でソート回数を適当に決めて、その回数ソートさせてランダムに初期個体生成。(ランダムソート的な)
//Ⅱ．Ⅰの方法でn個初期個体を生成
package Main;

import java.util.*;

public class InitialSolution {
    private LinkedHashMap<String, List<Integer>> meatMapDataAftersort = new LinkedHashMap<>();
    private List<String> meatNameData = new ArrayList<>();
    private List<LinkedHashMap<String, List<Integer>>> initialSolutionsList = new ArrayList<>();

    //適当な1つの解から引数で与えるsolutionNumber個の初期解を返すメソッド
    public List<LinkedHashMap<String, List<Integer>>> GenerateInitialsolution(LinkedHashMap<String, List<Integer>> meatMapData, int initialsolutionNumber) {
        this.meatNameData.addAll(meatMapData.keySet());
        System.out.println(this.meatNameData + "あ");
        for(int i = 0; i < initialsolutionNumber; i++) {
            Collections.shuffle(this.meatNameData);
            this.meatMapDataAftersort = new LinkedHashMap<>();
            for(int j = 0; j < meatNameData.size(); j++){
                this.meatMapDataAftersort.put(this.meatNameData.get(j), meatMapData.get(this.meatNameData.get(j)));
            }
            this.initialSolutionsList.add(this.meatMapDataAftersort);
        }
        //返すリストの形List{[0] -> Map{[肉の名前], List[肉のデータ]}, [1] -> …}
        System.out.println(this.initialSolutionsList);
        System.out.println("-------------------------------------------初期解------------------------------------------");
        return this.initialSolutionsList;
    }
}
