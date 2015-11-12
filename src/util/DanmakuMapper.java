package util;

import entity.Danmaku;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class DanmakuMapper {

    // 通过XML中提取出的parameter字符串和content字符串，映射到弹幕类
    public static Danmaku convert(String parameter, String content){
        Danmaku danmaku = new Danmaku();
        danmaku.setContent(content);
        String[] parameters = parameter.split(",");
        danmaku.setVideoSecond(Double.parseDouble(parameters[0]));
        danmaku.setMode(Integer.parseInt(parameters[1]));
        danmaku.setFontSize(Integer.parseInt(parameters[2]));
        danmaku.setColor(Long.parseLong(parameters[3]));
        danmaku.setTimestamp(Long.parseLong(parameters[4]));
        danmaku.setPoolType(Integer.parseInt(parameters[5]));
        danmaku.setSenderId(parameters[6]);
        danmaku.setId(Long.parseLong(parameters[7]));
        return danmaku;
    }
}
