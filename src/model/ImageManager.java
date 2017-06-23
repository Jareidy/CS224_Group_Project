package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;


public class ImageManager {

    private final HashMap<String,String> imageInfo = new HashMap<>();

    private final ArrayList<Picture> images = new ArrayList<>();

    public void addImage(String title, Image imageLink, String location, String description, String extension){
       Picture newImage = new Picture(title,imageLink, location, description,extension);
       images.add(newImage);
       imageInfo.put(title,description);
    }

    public ArrayList<Picture> getImages(){
        return images;
    }

    public ArrayList<Picture> searchImages(String location) {
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for (Picture image : images) {
            if (image.getLocation().contains(location)) {
                searchImagesArrayList.add(image);
            }
        }
        return searchImagesArrayList;
    }
}
