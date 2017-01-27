package com.huhao.code.advancejava.security;

import com.huhao.code.advancejava.utils.StringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by huhao on 2016/12/13.
 */
public class AesEnCriptionAndDecriptionTest {

    @Test
    public void test_encritpString(){
        String content = "RZ05UB7lzfhdhTy4XsplCN3wOxZLapauthRZ13GZ00RZ00";

        String pass = "MVCSECURITY_SESSIONID";

        byte[] encodeResult = AESUtil.aesEncrypt(content,pass);

        Assert.assertNotNull(encodeResult);
        System.out.println(StringUtil.encodeBase64Str(encodeResult));

        String original = new String(AESUtil.aesDecrypt(encodeResult,pass));
        Assert.assertNotNull(original);
        System.out.println(original);
    }

    @Test
    public void test_decryptionSessionId(){
        String content = "DH5RtCl8HbdaTGiBH/Mq8wbUSKt31HIDLqEDuWTW6ux5o3QBZ5a35s/0yriANDIgpqgoXDO3BCqZOpRRGt8L6A==";
        String result = AESUtil.aesDecrytToString(content,"MVCSECURITY_SESSIONID");
        System.out.println(result);

        Assert.assertEquals("RZ00D84896201E18416AB9211483A1532FAApetstoreRZ00",result);


    }


}
