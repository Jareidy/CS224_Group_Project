package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class Image {

    private final SimpleStringProperty imageLink;
    private final SimpleStringProperty location;

    public Image(String imageLink, String location){
        this.imageLink = new SimpleStringProperty(imageLink);
        this.location = new SimpleStringProperty(location);
    }

    public String getImageLink() {
        return imageLink.get();
    }

    public void setImageLink(String imageLink) {
        this.imageLink.set(imageLink);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
}
