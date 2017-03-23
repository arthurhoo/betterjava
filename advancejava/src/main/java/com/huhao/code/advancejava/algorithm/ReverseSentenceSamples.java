package com.huhao.code.advancejava.algorithm;

import java.util.Stack;

/**
 * Created by huhao on 2017/3/19.
 */
public class ReverseSentenceSamples {

    /**
     * reverse centence like
     * "You are a good boy" => "boy good a are You"
     * sentence can not start with space.
     */

    public String reverseSentence1(String str){
        if(str == null || str.isEmpty()){
            throw new IllegalArgumentException("centence should not be null.");
        }

        Stack<String> reverseStack = new Stack<String>();
        String result = null;
        int position = -1;
        int i;
        for(i = 0; i<str.length();i++){
            if(str.charAt(i) == ' '){
                if(i - position>1){
                    reverseStack.push(str.substring(position+1,i));
                    reverseStack.push(" ");
                }
                position = i;
            }
        }

        if(i-position >1){
            reverseStack.push(str.substring(position+1,i));
            reverseStack.push(" ");
        }

        StringBuffer sb = new StringBuffer();
        reverseStack.pop();
        while (!reverseStack.isEmpty()){
            sb.append(reverseStack.pop());
        }

        result = sb.toString();

        return result;
    }


}
