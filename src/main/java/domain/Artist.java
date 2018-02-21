package domain;

import javax.xml.crypto.Data;
import java.util.Objects;

public class Artist
{
    private int id;
    private String name;
    private String biography;
    private int country_code;

    public Artist() { }

    public Artist(int id, String name, String biography, int country_code)
    {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.country_code = country_code;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getId_country() {
        return country_code;
    }

    public void setId_country(int country_code) {
        this.country_code = country_code;
    }
}
