package model;

import javafx.scene.image.Image;

public final class PictureBuilder {

    protected String title;
    protected Image imageLink;
    protected String location;
    protected String description;
    protected String extension;
    public String contintent;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageLink(Image imageLink) {
        this.imageLink = imageLink;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContintent(String contintent) {
        this.contintent = contintent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Picture build(){
        return new Picture(this);
    }

}
