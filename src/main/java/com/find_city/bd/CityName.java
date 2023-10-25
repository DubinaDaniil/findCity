package com.find_city.bd;

public class CityName {

    private int id;
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl () {
        return url;
    }

    @Override
    public String toString() {
        return "CityName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}