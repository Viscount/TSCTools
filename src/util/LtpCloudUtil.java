package util;

import entity.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by TongjiSSE on 2015/11/13.
 */
public class LtpCloudUtil {

    private static String api_key = "Q5s0X4R6YHmLvHZk8umcqysY2xek5IyxjGrrDKZW";
    private static String api_url = "http://api.ltp-cloud.com/analysis/";

    public static String reqeustLtpCloud( String text ){
        try {
            RequestParam requestParam = new RequestParam(api_key,text,"all","json");
            URL restServiceURL = new URL(buildURL(requestParam));
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Charset", "UTF-8");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            InputStreamReader inputReader = new InputStreamReader(httpConnection.getInputStream(),"UTF-8");
            BufferedReader responseBuffer = new BufferedReader(inputReader);

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

    public static String buildURL(RequestParam requestParam){
        try{
            String resultURL = api_url;
            resultURL += "?";
            resultURL += "api_key=" + requestParam.getApi_key() + "&";
            resultURL += "text=" + URLEncoder.encode(requestParam.getText(), "UTF-8") + "&";
            resultURL += "pattern=" + requestParam.getPattern() + "&";
            resultURL += "format=" + requestParam.getFormat();
            return resultURL;
        } catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args){
        String response = LtpCloudUtil.reqeustLtpCloud("我是中国人");
        System.out.println(response);
    }

}



