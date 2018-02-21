package domain;

import java.util.Objects;

public class Clip {
    private int id;
    private String name;
    private String url;
    private int performer_id;
    private int style_id;


    public Clip(){ }
    public Clip(int id, String name, String url, int performer_id, int style_id) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.performer_id = performer_id;
        this.style_id = style_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPerformer_id() {
        return performer_id;
    }

    public void setPerformer_id(int performer_id) {
        this.performer_id = performer_id;
    }

    public int getStyle_id() {
        return style_id;
    }

    public void setStyle_id(int style_id) {
        this.style_id = style_id;
    }


    @Override
    public String toString() {
        return "Clip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", performer_id=" + performer_id +
                ", style_id=" + style_id +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clip)) return false;
        Clip clip = (Clip) o;
        return getId() == clip.getId() &&
                getPerformer_id() == clip.getPerformer_id() &&
                getStyle_id() == clip.getStyle_id() &&
                Objects.equals(getName(), clip.getName()) &&
                Objects.equals(getUrl(), clip.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getUrl(), getPerformer_id(), getStyle_id());
    }
}
