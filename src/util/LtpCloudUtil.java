package util;

import entity.RequestParam;
import entity.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/13.
 */
public class LtpCloudUtil {

    private static String api_key = "Q5s0X4R6YHmLvHZk8umcqysY2xek5IyxjGrrDKZW";
    private static String api_url = "http://api.ltp-cloud.com/analysis/";

    public static List<Word> parseText( String text ){
        String rawJson = requestLtpCloud(text);
        String modifiedJson = convertToWordListJson(rawJson);
        List<Word> result = JsonUtil.toObjectList(modifiedJson, Word.class);
        return result;
    }

    public static String requestLtpCloud( String text ){
        try {
            Thread.sleep(100);
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

    private static String buildURL(RequestParam requestParam){
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

    private static String convertToWordListJson(String rawJson){
        int beginIndex = 0;
        int endIndex = rawJson.length();
        int countLeft = 0;
        while ( countLeft < 2 ){
            if ( rawJson.charAt(beginIndex) == '[' ) countLeft++;
            beginIndex++;
        }
        int countRight = 0;
        while ( countRight < 2 ){
            endIndex--;
            if ( rawJson.charAt(endIndex) == ']' ) countRight++;
        }
        String modifiedJson = rawJson.substring(beginIndex,endIndex);
        return modifiedJson.trim();
    }

    public static void main(String[] args){
        List<Word> response = LtpCloudUtil.parseText("工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作");
        System.out.println(JsonUtil.toJson(response));
    }

}



