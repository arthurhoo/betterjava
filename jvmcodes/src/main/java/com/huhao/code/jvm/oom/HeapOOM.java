package com.huhao.code.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurhoo on 6/2/16.
 */
public class HeapOOM {

    /**
     *  VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutofMemoryError
     */
    static class OOMObject{

    }

    public static void main(String args[]){

//        List<OOMObject> list = new ArrayList<OOMObject>();
//
//        while (true){
//            list.add(new OOMObject());
//        }


        String baseName = "skfjsldfjlsdjf.jsfdlksjdlf(sdfjsldkf)";
        int paramUniqueIndex = baseName.indexOf("(");
        System.out.println(paramUniqueIndex);

    }
}
