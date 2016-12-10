package com.huhao.code.advancejava.annotation;

import java.lang.reflect.Field;

/**
 * Created by huhao on 16/7/27.
 */
public class FruiteInfoUtil {

    public static void getFruiteInfo(Class<?> clazz) {
        String strFruitName = " 水果名称：";
        String strFruitColor = " 水果颜色：";
        String strFruitProvicer = "供应商信息：";

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println(strFruitName);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor += fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);

            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvicer = strFruitProvicer + " 供应商编号:" + fruitProvider.id() + " 供应商名称:" + fruitProvider.name()
                                   + " 供应商地址:" + fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}
