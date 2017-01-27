package com.huhao.code.advancejava.security;

import com.huhao.code.advancejava.utils.StringUtil;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by huhao on 2016/12/13.
 */
public class AESUtil {

    /**
     * AES 简易加密工具
     * @param content
     * @param password
     * @return
     */
    public static byte[] aesEncrypt(String content, String password) {
        if (content == null || password == null || content.isEmpty() || password.isEmpty()) {
            return null;
        }

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());

            kgen.init(128, random);// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return result;

        } catch (NoSuchPaddingException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return null;
    }

    /**
     * AES 简易解密工具
     * @param content
     * @param password
     * @return
     */
    public static byte[] aesDecrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            return result; // 明文

        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return null;
    }

    /**
     * 二进制转换为十六进制工具
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * AES 简易加密工具，使用Base64编码返回字符串
     * @param content
     * @param password
     * @return
     */
    public static final String aesEncrytToString(String content, String password) {
        if (content == null || password == null || content.isEmpty() || password.isEmpty()) {
            return null;
        }

        byte[] encrytResult = aesEncrypt(content, password);
        if (encrytResult == null || encrytResult.length < 1) {
            return null;
        }
        return StringUtil.encodeBase64Str(encrytResult);
    }

    /**
     * AES 简易解密工具，返回字符串，对Base64解码
     * @param content
     * @param password
     * @return
     */
    public static final String aesDecrytToString(String content, String password) {
        if (content == null || password == null || content.isEmpty() || password.isEmpty()) {
            return null;
        }

        byte[] decodeResult = StringUtil.decodeBase64Str(content); // Base64解码
        if (decodeResult == null || decodeResult.length < 1) {
            return null;
        }

        byte[] decryptResult = aesDecrypt(decodeResult, password);
        if (decryptResult == null) {
            return null;
        }

        return new String(decryptResult);
    }



}
