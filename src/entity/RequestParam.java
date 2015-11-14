package entity;

/**
 * Created by Jaric Liao on 2015/11/14.
 */
public class RequestParam {

    private String api_key;
    private String text;
    private String pattern;
    private String format;

    public RequestParam(String api_key, String text, String pattern, String format) {
        this.api_key = api_key;
        this.text = text;
        this.pattern = pattern;
        this.format = format;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
