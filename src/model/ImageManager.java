package model;

import controller.MainViewController;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;


public class ImageManager {

    HashMap<String,String> imageInfo = new HashMap<>();

    ArrayList<Picture> images = new ArrayList<>();

    public void addImage(String title, Image imageLink, String location, String description, String extension){
       Picture newImage = new Picture(title,imageLink, location, description,extension);
       images.add(newImage);
       imageInfo.put(title,description);
       XMLHandler xmlHandler = new XMLHandler();
       ImportFile importFile = new ImportFile();
       xmlHandler.XMLWriter();
    }

    public ArrayList<Picture> getImages(){
        return images;
    }

    public ArrayList<Picture> searchImages(String location) {
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).getLocation().contains(location)) {
                searchImagesArrayList.add(images.get(i));
            }
        }
        return searchImagesArrayList;
    }
}
