package com.huhao.code.advancejava.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huhao on 2017/3/3.
 */
public class Solution {

    private HashMap<String,ArrayList<String>> tempWords = new HashMap<String, ArrayList<String>>();


    boolean isWork(String workds, HashMap<String, Boolean> dic){
        if(workds == null || workds.length()==0){
            throw new IllegalArgumentException("参数不能为空");
        }

        if(dic.containsKey(workds)){
            return true;
        }

        getAllWords(workds,dic);

        for(String key:tempWords.keySet()){
            ArrayList<String> wordList = tempWords.get(key);
            if(workds.endsWith(wordList.get(wordList.size()-1))){
                return true;
            }
        }


        return false;
    }

    private void getAllWords(String subString, HashMap<String,Boolean> dic){
        for (int i = 0;i < subString.length();i++){
            int p = i;
            for(int j = i;j<subString.length();j++){
                String tryWord = subString.substring(p,j);
                
                if(dic.containsKey(tryWord)){
                    ArrayList<String> firstWord = tempWords.get(tryWord);
                    if(firstWord == null){
                        firstWord = new ArrayList<String>();
                    }
                    firstWord.add(tryWord);
                    tempWords.put(tryWord,firstWord);
                    p = j+1;
                }
            }

        }

    }

}
