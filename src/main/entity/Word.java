package main.entity;

import java.util.List;

/**
 * Created by Jaric Liao on 2015/11/15.
 */
public class Word {
    private long id;
    // 词语内容
    private String cont;
    // 词性标注
    private String pos;
    // 命名实体内容
    private String ne;
    // parent 为依存句法分析的父亲结点id 号，relate 为相对应的关系
    private long parent;
    private String relate;
    // semparent 为语义依存分析的父亲结点id 号，semrelate 为相对应的关系
    private long semparent;
    private String semrelate;
    // srl级别的分析,每个对象是一项语义角色
    private List<SRLItem> args;

    public Word(long id, String cont, String pos, String ne, long parent, String relate, long semparent, String semrelate, List<SRLItem> args) {
        this.id = id;
        this.cont = cont;
        this.pos = pos;
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

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
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
