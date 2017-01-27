/**
 * created since 2009-7-16
 */
package com.huhao.code.advancejava.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author duwei
 * @version $Id: StringUtil.java,v 0.1 2009-7-16 下午08:58:23 duwei Exp $
 */
public class StringUtil {
    public static final String CHARSET_NAME_UTF8 = "UTF-8";

    /**
     * 将字节数组按base64转换为字符串
     * @param bytes
     * @return
     */
    public static String encodeBase64Str(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return toString(Base64.encodeBase64(bytes));
    }

    /**
     * 将字符串按照Base64转换为字节数组
     * @param str
     * @return
     */
    public static byte[] decodeBase64Str(final String str) {
        if (str == null) {
            return null;
        }
        try {
            return Base64.decodeBase64(toBytes(str));
        } catch (final RuntimeException e) {
            throw e;
        }
    }

    /**
     * 编码转换
     * @param bytes
     * @return
     */
    public static String toString(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, CHARSET_NAME_UTF8);
        } catch (final UnsupportedEncodingException e) {

        }
        return null;
    }

    /**
     * 编码转换
     * @param bytes
     * @return
     */
    public static byte[] toBytes(final String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(CHARSET_NAME_UTF8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Union multiple character arrays.
     * 
     * @param list the char[]s to union
     * @return the union of the char[]s
     */
    public static char[] union(char[]... list) {
        StringBuilder sb = new StringBuilder();

        for (char[] characters : list) {
            for (int i = 0; i < list.length; i++) {
                if (!contains(sb, characters[i]))
                    sb.append(list[i]);
            }
        }

        char[] toReturn = new char[sb.length()];
        sb.getChars(0, sb.length(), toReturn, 0);
        Arrays.sort(toReturn);
        return toReturn;
    }

    /**
     * Returns true if the character is contained in the provided StringBuilder.
     * @param input 	The input
     * @param c 		The character to check for to see if {@code input} contains.
     * @return			True if the specified character is contained; false otherwise.
     */
    public static boolean contains(StringBuilder input, char c) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c)
                return true;
        }
        return false;
    }

    private StringUtil() {
    }
}
