package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TongjiSSE on 2015/11/13.
 */
public class LtpCloudUtil {

    private static String api_key = "Q5s0X4R6YHmLvHZk8umcqysY2xek5IyxjGrrDKZW";
    private static String api_url = "http://api.ltp-cloud.com/analysis/";

    public static String reqeustLtpCloud( String text ){
        try {
            URL restServiceURL = new URL(api_url);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("api_key",api_key);
            httpConnection.setRequestProperty("text",text);
            httpConnection.setRequestProperty("pattern","all");
            httpConnection.setRequestProperty("format","json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String output;
            String result = "";
            while ((output = responseBuffer.readLine()) != null) {
                result = result + output;
            }
            httpConnection.disconnect();
            return result;
        } catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        String response = LtpCloudUtil.reqeustLtpCloud("我是中国人");
        System.out.println(response);
    }

}



