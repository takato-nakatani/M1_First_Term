//Ⅰ．二つの親から2つの子を生成する。
//Ⅱ．交叉は順序交叉を行う。
//Ⅲ．この操作もm回繰り返し、新しい解を2m個生成
//解の集合を

package Main;

import com.sun.javafx.collections.MappingChange;

import java.util.*;

public class OrderCrossover {

    private List<Integer> randomCrossoverPlaceList = new ArrayList<>();
    private List<String> parentSolution1 = new ArrayList<>();
    private List<String> parentSolution2 = new ArrayList<>();
    private List<String> offspring1meatName = new ArrayList<>();
    private List<String> offspring2meatName = new ArrayList<>();
    private LinkedHashMap<String, List<Integer>> offspring1SolutionMap = new LinkedHashMap<>();
    private LinkedHashMap<String, List<Integer>> offspring2SolutionMap = new LinkedHashMap<>();
    private List<LinkedHashMap<String, List<Integer>>> OrderCrossoverSolutionList = new ArrayList<>();
    private Random random = new Random();
    private int separatePoint;

    public List<LinkedHashMap<String, List<Integer>>> SolutionsOrderCrossover(List<LinkedHashMap<String, List<Integer>>> SolutionPopulationList, int CrossoverloopNumber, int initialSolutionNumber) {
        //親の個体を重複無しでランダムに得られるように乱数のリストを生成
        for (int k = 0; k < initialSolutionNumber; k++) {
            this.randomCrossoverPlaceList.add(k);
            System.out.println("個数　：　" + this.randomCrossoverPlaceList + initialSolutionNumber);
        }

        ///交叉開始
        for (int m = 0; m < CrossoverloopNumber; m++) {
            this.parentSolution1 = new ArrayList<>();
            this.parentSolution2 = new ArrayList<>();
            this.offspring1SolutionMap = new LinkedHashMap<>();
            this.offspring2SolutionMap = new LinkedHashMap<>();
            Collections.shuffle(this.randomCrossoverPlaceList);
            System.out.println("-------------------------------------------交叉------------------------------------------");
            System.out.println(this.randomCrossoverPlaceList);
            System.out.println(SolutionPopulationList);

            //親の個体を重複無しでランダムに2つ取り出して2つの変数を格納
            this.parentSolution1.addAll(SolutionPopulationList.get(this.randomCrossoverPlaceList.get(0)).keySet());
            System.out.println("親1：" + parentSolution1);
            this.parentSolution2.addAll(SolutionPopulationList.get(this.randomCrossoverPlaceList.get(1)).keySet());
            System.out.println("親2：" + parentSolution2);
            //順序交叉の分岐点をランダムに決定
            this.separatePoint = this.random.nextInt(this.parentSolution1.size());
            //GenerateOffspringメソッドにより子孫1と子孫2を生成
            this.offspring1meatName = this.GenerateOffspring(this.separatePoint, this.parentSolution1, this.parentSolution2);
            this.offspring2meatName = this.GenerateOffspring(this.separatePoint, this.parentSolution2, this.parentSolution1);
            System.out.println("分割点" + this.separatePoint);
            System.out.println("子孫1：" + this.offspring1meatName);
            System.out.println("子孫2：" + this.offspring2meatName);
            //以下、子孫1にデータ埋め込み。
            for(int i = 0; i < this.offspring1meatName.size(); i++){
                out1 :
                for (LinkedHashMap<String, List<Integer>> meatMap : SolutionPopulationList) {
                    for (String meatName : meatMap.keySet()) {
                        if (meatName.equals(this.offspring1meatName.get(i))) {
                            System.out.println("操作前 : " + this.offspring1meatName.get(i) + "のデータ：" + meatMap.get(this.offspring1meatName.get(i)));
                            this.offspring1SolutionMap.put(this.offspring1meatName.get(i), meatMap.get(this.offspring1meatName.get(i)));
                            break out1;
                        }
                    }
                }
            }
            //以下、子孫2にデータ埋め込み。
            for(int i = 0; i < this.offspring2meatName.size(); i++){
                out2 :
                for (LinkedHashMap<String, List<Integer>> meatMap : SolutionPopulationList) {
                    for (String meatName : meatMap.keySet()) {
                        if (meatName.equals(this.offspring2meatName.get(i))) {
                            System.out.println("操作前 : " + this.offspring2meatName.get(i) + "のデータ：" + meatMap.get(this.offspring2meatName.get(i)));
                            this.offspring2SolutionMap.put(this.offspring2meatName.get(i), meatMap.get(this.offspring2meatName.get(i)));
                            break out2;
                        }
                    }
                }
            }
            System.out.println("子孫1の解データ：" + offspring1SolutionMap);
            this.OrderCrossoverSolutionList.add(this.offspring1SolutionMap);
            System.out.println("子孫2の解データ：" + offspring2SolutionMap);
            this.OrderCrossoverSolutionList.add(this.offspring2SolutionMap);
        }
        System.out.println(this.OrderCrossoverSolutionList);
        System.out.println("-------------------------------------------交叉終了------------------------------------------");
        return this.OrderCrossoverSolutionList;
    }

    //親を引数で受け取り、順序交叉を行うメソッド。
    private List<String> GenerateOffspring(int separatePoint, List<String> parentSolution1, List<String> parentSolution2){
        List<String> offspringmeatName = new ArrayList<>();
        Map<Integer, String> countPlaceandName = new TreeMap<>();

        //子1の生成。親1から部分的に引き継ぎ、親1の残りの部分は親2の出てくる順番に格納する。
        //親１から一部を引き継ぐ
        for (int i = 0; i< separatePoint; i++) {
            offspringmeatName.add(parentSolution1.get(i));
        }
        //親１の残りの要素が親２で現れている順番と肉の種類をcountPlaceに格納
        for(int j = separatePoint; j < parentSolution1.size(); j++){
            for(int k = 0; k < parentSolution2.size(); k++){
                if(parentSolution1.get(j).equals(parentSolution2.get(k))){
                    countPlaceandName.put(k, parentSolution2.get(k));
                    break;
                }
            }
        }
        Iterator<Integer> mapIterator= countPlaceandName.keySet().iterator();
        while(mapIterator.hasNext()) {
            // nextを使用して値を取得する
            Integer key = (Integer)mapIterator.next();
            offspringmeatName.add(countPlaceandName.get(key));
        }
        return offspringmeatName;
    }
}
