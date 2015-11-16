package entity;

/**
 * ��Ļ��ʵ��
 * Created by TongjiSSE on 2015/11/12.
 */
public class Danmaku implements Comparable<Danmaku>{
    // ��Ƶ�г��ֵ�����
    private double videoSecond;
    // ��Ļ��ģʽ 1..3 ������Ļ 4�׶˵�Ļ 5���˵�Ļ 6.����Ļ 7��׼��λ 8�߼���Ļ
    private int mode;
    // �ֺ�,12�ǳ�С,16��С,18С,25��,36��,45�ܴ�,64�ر��
    private int fontSize;
    // �������ɫ,HTML��ɫ��ʮ����
    private long color;
    // Unix��ʽ��ʱ���
    private long timestamp;
    // ��Ļ�� 0��ͨ�� 1��Ļ�� 2�����
    private int poolType;
    // ������ID
    private String senderId;
    // ��Ļ�ڵ�Ļ���ݿ���rowID
    private long Id;
    // ��Ļ����
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
