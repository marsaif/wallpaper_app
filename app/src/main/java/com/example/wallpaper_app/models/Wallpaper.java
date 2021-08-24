package com.example.wallpaper_app.models;

public class Wallpaper {
    private int id ;
    private String photographer ;
    private String urlImage ;

    public Wallpaper(int id, String photographer, String urlImage) {
        this.id = id;
        this.photographer = photographer;
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "wallpaper{" +
                "id=" + id +
                ", photographer='" + photographer + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
