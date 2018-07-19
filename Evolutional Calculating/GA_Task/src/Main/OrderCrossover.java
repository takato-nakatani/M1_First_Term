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
    private List<String> offspring1MeetName = new ArrayList<>();
    private List<String> offspring2MeetName = new ArrayList<>();
    private LinkedHashMap<String, List> offspring1SolutionMap = new LinkedHashMap<>();
    private LinkedHashMap<String, List> offspring2SolutionMap = new LinkedHashMap<>();
    private List<LinkedHashMap<String, List>> OrderCrossoverSolutionList = new ArrayList<>();
    private Random random = new Random();
    private int separatePoint;

//    @Override
//    public boolean equals(Object o){
//        if(o == this) return true;
//        if(o == null) return false;
//        if(!(o instanceof String))
//    }

//    public List<List<Map>> SolutionsOrderCrossover(List<List<Map>> SolutionPopulationList, int CrossoverNumber){
    public List<LinkedHashMap<String, List>> SolutionsOrderCrossover(List<LinkedHashMap<String, List>> SolutionPopulationList, int CrossoverloopNumber, int initialSolutionNumber) {
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


            this.separatePoint = this.random.nextInt(this.parentSolution1.size());

            this.offspring1MeetName = this.GenerateOffspring(this.separatePoint, this.parentSolution1, this.parentSolution2);
            this.offspring2MeetName = this.GenerateOffspring(this.separatePoint, this.parentSolution2, this.parentSolution1);
            System.out.println("分割点" + this.separatePoint);
            System.out.println("子孫1：" + this.offspring1MeetName);
            System.out.println("子孫2：" + this.offspring2MeetName);
//            for(int i = 0; i < this.separatePoint + 1; i++){
            //子孫1の肉名とデータをMap型に格納


            for(int i = 0; i < this.offspring1MeetName.size(); i++){
                out1 :
                for (LinkedHashMap<String, List> meetMap : SolutionPopulationList) {
                    for (String meetName : meetMap.keySet()) {
                        if (meetName.equals(this.offspring1MeetName.get(i))) {
                            System.out.println("操作前 : " + this.offspring1MeetName.get(i) + "のデータ：" + meetMap.get(this.offspring1MeetName.get(i)));
                            this.offspring1SolutionMap.put(this.offspring1MeetName.get(i), meetMap.get(this.offspring1MeetName.get(i)));
                            break out1;
                        }
                    }
                }
//                this.offspring1SolutionMap.put(this.offspring1MeetName.get(i), )
            }

            for(int i = 0; i < this.offspring2MeetName.size(); i++){
                out2 :
                for (LinkedHashMap<String, List> meetMap : SolutionPopulationList) {
                    for (String meetName : meetMap.keySet()) {
                        if (meetName.equals(this.offspring2MeetName.get(i))) {
                            System.out.println("操作前 : " + this.offspring2MeetName.get(i) + "のデータ：" + meetMap.get(this.offspring2MeetName.get(i)));
                            this.offspring2SolutionMap.put(this.offspring2MeetName.get(i), meetMap.get(this.offspring2MeetName.get(i)));
                            break out2;
                        }
                    }
                }
//                this.offspring1SolutionMap.put(this.offspring1MeetName.get(i), )
            }
            //子孫1と子孫2のMapデータをリストに挿入


//            //子1の生成。親1から部分的に引き継ぎ、親1の残りの部分は親2の出てくる順番に格納する。
//            //親１から一部を引き継ぐ
//            for (int i = 0; i< this.separatePoint; i++) {
//                this.offspring1MeetName.add(this.parentSolution1.get(i));
//            }
//            //親１の残りの要素が親２で現れている順番と肉の種類をcountPlaceに格納
//            for(int j = this.separatePoint; j < this.parentSolution1.size(); j++){
//                for(int k = 0; k < this.parentSolution1.size(); k++){
//                    if(this.parentSolution1.get(j).equals(this.parentSolution2.get(k))){
//                        this.countPlaceandName.put(k, this.parentSolution2.get(k));
//                        break;
//                    }
//                }
//            }
//            //親2の順番と同じように親1の残りの要素を入れる。
//            for(int i = 0; i < this.parentSolution1.size() - this.separatePoint; i++){
//                this.offspring1MeetName.add(this.countPlaceandName.get(i));
//            }


//            for (String meetName1 : this.offspring1MeetName) {
//
//                System.out.println("meetName1 : " + meetName1);
//                System.out.println("SolutionPopulationList.get(0).get(0).get(meetName1).get(this.separatePoint) : " + SolutionPopulationList.get(0).get(0).keySet().iterator().next());
////                if(meetName1.equals(SolutionPopulationList.get(0).get(0).get(meetName1).get(this.separatePoint))){
//                    System.out.println("操作後 : " + meetName1 + "のデータ：" + SolutionPopulationList.get(0).get(0).get(meetName1));
//                    this.offspring1SolutionMap.put(meetName1, SolutionPopulationList.get(0).get(0).get(meetName1));
////                }
//            }
//            for (String meetName1 : this.offspring1MeetName) {
//                for(LinkedHashMap<String, List> meetMap : SolutionPopulationList.get(0)){
////                    System.out.println("キーの値1：" + meetMap.keySet().iterator().next());
////                    System.out.println("キーの値2：" + meetName1);
//                    if(meetName1.equals(meetMap.keySet().iterator().next())){
//                        System.out.println(meetName1 + "のデータ：" + SolutionPopulationList.get(0).get(0).get(meetName1));
//                        this.offspring2SolutionMap.put(meetName1, meetMap.get(meetName1));
//                    }
//                }
//            }
            System.out.println("子孫1の解データ：" + offspring1SolutionMap);
            this.OrderCrossoverSolutionList.add(this.offspring1SolutionMap);
//            System.out.println("引数の解：" + SolutionPopulationList.get(0));
//            for (String meetName2 : this.offspring2MeetName) {
//                for(LinkedHashMap<String, List> meetMap : SolutionPopulationList.get(0)){
////                    System.out.println("キーの値1：" + meetMap.keySet().iterator().next());
////                    System.out.println("キーの値2：" + meetName2);
//                    if(meetName2.equals(meetMap.keySet().iterator().next())){
//                        System.out.println(meetName2 + "のデータ：" + SolutionPopulationList.get(0).get(0).get(meetName2));
//                        this.offspring2SolutionMap.put(meetName2, meetMap.get(meetName2));
//                    }
//                }
//            }
            System.out.println("子孫2の解データ：" + offspring2SolutionMap);
            this.OrderCrossoverSolutionList.add(this.offspring2SolutionMap);

        }
        System.out.println(this.OrderCrossoverSolutionList);
        System.out.println("-------------------------------------------交叉終了------------------------------------------");
        return this.OrderCrossoverSolutionList;
    }





    private List<String> GenerateOffspring(int separatePoint, List<String> parentSolution1, List<String> parentSolution2){
        List<String> offspringMeetName = new ArrayList<>();
        Map<Integer, String> countPlaceandName = new TreeMap<>();

        //子1の生成。親1から部分的に引き継ぎ、親1の残りの部分は親2の出てくる順番に格納する。
        //親１から一部を引き継ぐ
        for (int i = 0; i< separatePoint; i++) {
            offspringMeetName.add(parentSolution1.get(i));
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
        //親2の順番と同じように親1の残りの要素を入れる。
//        for(int i = 0; i < parentSolution1.size() - separatePoint; i++){
//            Iterator<Map> iterator = new Iterator<Map>();
//            System.out.println(countPlaceandName.get(i) + "が子孫に選択された");
//            offspringMeetName.add(countPlaceandName.get(i));
//        }
        Iterator<Integer> mapIterator= countPlaceandName.keySet().iterator();
        while(mapIterator.hasNext()) {
            // nextを使用して値を取得する
            Integer key = (Integer)mapIterator.next();
            offspringMeetName.add(countPlaceandName.get(key));
        }

        return offspringMeetName;
    }
}
