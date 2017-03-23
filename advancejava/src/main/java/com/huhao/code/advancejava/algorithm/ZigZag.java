package com.huhao.code.advancejava.algorithm;

/**
 * Created by huhao on 2017/3/23.
 */
/*n=numRows
Δ=2n-2    1                           2n-1                         4n-3
Δ=        2                     2n-2  2n                    4n-4   4n-2
Δ=        3               2n-3        2n+1              4n-5       .
Δ=        .           .               .               .            .
Δ=        .       n+2                 .           3n               .
Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
Δ=2n-2    n                           3n-2                         5n-4
*/
public class ZigZag {


    /**
     * 此方法时间复杂度O(n),但是代码并不简洁
     * @param text
     * @param nRows
     * @return
     */
    public String convert(String text,int nRows){
        if(nRows<=0 || text==null){
            throw new IllegalArgumentException("parameter should greater than 0");
        }

        if(text.isEmpty() || nRows == 1){
            return text;
        }


        StringBuffer[] stringBuffers = new StringBuffer[nRows];

        for(int i=0;i<nRows;i++){
            stringBuffers[i] = new StringBuffer();
        }

        char[] chars = text.toCharArray();
        int index=0;
        while(index<chars.length){
            for(int rowindex=0;rowindex<nRows && index<chars.length;rowindex++){
                stringBuffers[rowindex].append(chars[index]);
                index++;
            }

            for(int reverse = nRows-2;reverse>0 && index<chars.length;reverse--){
                stringBuffers[reverse].append(chars[index]);
                index++;
            }
        }

        StringBuffer result = new StringBuffer();

        for(int j = 0;j<nRows;j++){
            result.append(stringBuffers[j]);
        }

        return result.toString();
    }

    /**
     * 一种简洁的解法
     */

    public String convert2(String text,int nRows){

        if(nRows<=0 || text==null){
            throw new IllegalArgumentException("parameter should greater than 0");
        }

        if(text.isEmpty() || nRows == 1){
            return text;
        }


        StringBuffer[] stringBuffers = new StringBuffer[nRows];

        for(int i=0;i<nRows;i++){
            stringBuffers[i] = new StringBuffer();
        }

        int inc = 1;
        int index = 0;

        for(int i = 0;i<text.length();i++){
            stringBuffers[index].append(text.charAt(i));
            if(index == 0){
                inc = 1;
            }
            if(index == nRows-1){
                inc = -1;
            }

            index+=inc;
        }

        StringBuffer result = new StringBuffer();

        for(int j = 0;j<nRows;j++){
            result.append(stringBuffers[j]);
        }

        return result.toString();

    }

}
