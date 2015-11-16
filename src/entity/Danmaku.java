package entity;

/**
 * 弹幕类实体
 * Created by TongjiSSE on 2015/11/12.
 */
public class Danmaku implements Comparable<Danmaku>{
    // 视频中出现的秒数
    private double videoSecond;
    // 弹幕的模式 1..3 滚动弹幕 4底端弹幕 5顶端弹幕 6.逆向弹幕 7精准定位 8高级弹幕
    private int mode;
    // 字号,12非常小,16特小,18小,25中,36大,45很大,64特别大
    private int fontSize;
    // 字体的颜色,HTML颜色的十进制
    private long color;
    // Unix格式的时间戳
    private long timestamp;
    // 弹幕池 0普通池 1字幕池 2特殊池
    private int poolType;
    // 发送者ID
    private String senderId;
    // 弹幕在弹幕数据库中rowID
    private long Id;
    // 弹幕内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getVideoSecond() {
        return videoSecond;
    }

    public void setVideoSecond(double videoSecond) {
        this.videoSecond = videoSecond;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getPoolType() {
        return poolType;
    }

    public void setPoolType(int poolType) {
        this.poolType = poolType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    @Override
    public int compareTo(Danmaku o) {
        if ( this.getVideoSecond() == o.getVideoSecond() ) return 0;
        else if ( this.getVideoSecond() < o.getVideoSecond() ) return -1;
        else return 1;
    }
}
