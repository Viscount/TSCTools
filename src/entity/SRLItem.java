package entity;

/**
 * Created by Jaric Liao on 2015/11/15.
 */
public class SRLItem {
    private long id;
    private String type;
    private long beg;
    private long end;

    public SRLItem(long id, String type, long beg, long end) {
        this.id = id;
        this.type = type;
        this.beg = beg;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBeg() {
        return beg;
    }

    public void setBeg(long beg) {
        this.beg = beg;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
