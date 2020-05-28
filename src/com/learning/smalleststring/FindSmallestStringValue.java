package com.learning.smalleststring;

import java.util.HashMap;
import java.util.Map;

public class FindSmallestStringValue {

    public static String getSmallestString(int weight){


    Map map = new HashMap<Integer,String>();
    int prevWeight = 1;
    int asciiVal=65;
      map.put(prevWeight, "A");

      for(int i=2;i<=26;i++){
        String letter =  Character.toString((char)(asciiVal+i-1));
        int letterVal = i*prevWeight+prevWeight;
        map.put(letterVal, letter);
    }

      if(map.containsKey(weight)){
        return (String)map.get(weight);
    }

    StringBuilder sb = new StringBuilder();
    long j = weight-1;
    long target=weight;
      while(j>0){
        if(map.containsKey(j)){
            int count =1;
            while(j*count<=target){
                sb.append(map.get(j));
                count ++;
            }
            target -= j*(count-1);
        }
        j--;
    }
      return sb.toString();
}

    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(FindSmallestStringValue.getSmallestString(6));
    }
}
