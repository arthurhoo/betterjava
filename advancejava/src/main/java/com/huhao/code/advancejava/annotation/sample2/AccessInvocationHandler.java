package com.huhao.code.advancejava.annotation.sample2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huhao on 16/7/27.
 */
public class AccessInvocationHandler<T> implements InvocationHandler {

     final T accessObj;

    public AccessInvocationHandler(T accessObj) {
        this.accessObj = accessObj;
    }



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RequiredRoles requiredRole = method.getAnnotation(RequiredRoles.class);
        if(requiredRole != null){
            String[] roles = requiredRole.value();
            if(roles == null){
                throw new Exception();
            }
        }
        return method.invoke(accessObj,args);
    }


}
