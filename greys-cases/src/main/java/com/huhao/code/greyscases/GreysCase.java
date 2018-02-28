package com.huhao.code.greyscases;

import java.util.Random;

/**
 * @author: Arthur Hu
 * @date: 2017/12/20 下午2:52
 * Description:
 */
public class GreysCase {

    private int count;

    public void executePlus(CalculateData data) {
        count += data.getCount();
    }

    public void executeSub(CalculateData data) {
        count -= data.getCount();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    /**
     * 计算数据携带类
     *
     */
    public static class CalculateData{
        private int count;

        private CalculateData(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    public static void main(String... args) throws InterruptedException{
        GreysCase greysCase = new GreysCase();

        Random r = new Random();
        while(true){
            int rCount = r.nextInt(10);
            //随机执行plus和sub 方法
            if(r.nextInt(2) == 0){
                greysCase.executeSub(new CalculateData(rCount));
            }else{
                greysCase.executePlus(new CalculateData(rCount));
            }
            //每次计算完后随机休息一会，喝杯茶
            Thread.sleep(r.nextInt(1000));
        }
    }
}
