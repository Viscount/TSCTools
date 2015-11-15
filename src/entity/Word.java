package entity;

import java.util.List;

/**
 * Created by Jaric Liao on 2015/11/15.
 */
public class Word {
    private long id;
    private String cont;
    private String ne;
    private long parent;
    private String relate;
    private long semparent;
    private String semrelate;
    private List<SRLItem> args;

    public Word(long id, String cont, String ne, long parent, String relate, long semparent, String semrelate, List<SRLItem> args) {
        this.id = id;
        this.cont = cont;
        this.ne = ne;
        this.parent = parent;
        this.relate = relate;
        this.semparent = semparent;
        this.semrelate = semrelate;
        this.args = args;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getRelate() {
        return relate;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }

    public long getSemparent() {
        return semparent;
    }

    public void setSemparent(long semparent) {
        this.semparent = semparent;
    }

    public String getSemrelate() {
        return semrelate;
    }

    public void setSemrelate(String semrelate) {
        this.semrelate = semrelate;
    }

    public List<SRLItem> getArgs() {
        return args;
    }

    public void setArgs(List<SRLItem> args) {
        this.args = args;
    }
}
