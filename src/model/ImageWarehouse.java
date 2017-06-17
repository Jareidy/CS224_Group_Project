package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.HashMap;


public class ImageWarehouse {


    HashMap<String,String> imageInfo = new HashMap<>();

   ArrayList<Image> images = new ArrayList<>();

   public void addImage(String title, String imageLink, String location, String description){
       Image newImage = new Image(title,imageLink, location, description);
       images.add(newImage);
       imageInfo.put(title,description);
   }

    public HashMap<String, String> getImageInfo() {
        return imageInfo;
    }

   public ArrayList<Image> getImages(){
       return images;
   }

}
