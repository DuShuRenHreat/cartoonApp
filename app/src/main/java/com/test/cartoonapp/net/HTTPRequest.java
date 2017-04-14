package com.test.cartoonapp.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PC-2 on 2017/4/11.
 */

public class HTTPRequest {
    public static String  request(String strUrl){
        StringBuffer sb = new StringBuffer();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(strUrl).openConnection();
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            //conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36"); man
            //conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8"); 网页
            conn.setDoInput(true);
            conn.setDoInput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
