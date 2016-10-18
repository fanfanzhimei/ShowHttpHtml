package com.zhi.service;

import com.zhi.utils.ServiceTool;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/18.
 */
public class HttpService {
    private static final int SUCCESS_RESPONSE = 200;

    /*根据网页，返回htm源码*/
    public static String getHtml(String url) throws Exception {
        String data = "";
        URL httpUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        if (connection.getResponseCode() == SUCCESS_RESPONSE) {
            InputStream in = connection.getInputStream();
            byte[] bytes = ServiceTool.getBytes(in);
            data = new String(bytes);
        }
        return data;
    }
}