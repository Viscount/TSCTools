package entity;

import java.util.List;

/**
 * Created by Jaric Liao on 2015/11/20.
 */
public class DictionaryItem {
    // �ϲ�ģʽ��WL ����� ���� RE ������ʽ
    private String mode;
    // ������ʽ��ʽ
    private String pattern;
    // �������ʽ
    private List<String> wordsList;
    // �ϲ���Ĵ�
    private String mergeTo;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public String getMergeTo() {
        return mergeTo;
    }

    public void setMergeTo(String mergeTo) {
        this.mergeTo = mergeTo;
    }
}
