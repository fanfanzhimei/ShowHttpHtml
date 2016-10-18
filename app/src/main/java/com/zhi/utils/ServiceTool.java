package com.zhi.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/10/18.
 */
public class ServiceTool {

    /*从输入流中读取数据，并用byte[]  返回*/
    public static byte[] getBytes(InputStream in) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = in.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}