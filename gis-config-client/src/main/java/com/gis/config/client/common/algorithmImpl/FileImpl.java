package com.gis.config.client.common.algorithmImpl;

import java.io.*;

/**
 * 文件相关的算法实现
 */
public class FileImpl {

    /**
     * **************************************************
     * 以下方式利用mozilla的jchardet作为探测工具
     */

    private static boolean found    = false;
    /**
     * 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null, 这时应当查询属性
     */
    private static String  encoding = null;

    /**
     * 利用文件头特征判断文件的编码方式
     *
     * @param fileName 需要处理的文件
     * @return 返回文件编码
     */
    public static String simpleEncoding(String fileName) {
        int p = 0;
        try (
                BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        ) {
            p = (bin.read() << 8) + bin.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }



}
