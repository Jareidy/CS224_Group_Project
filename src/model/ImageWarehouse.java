package model;

import java.util.ArrayList;

public class ImageWarehouse {

   ArrayList<Image> images = new ArrayList<>();

   public void addImage(String imageLink, String location, String description){
       Image newImage = new Image(imageLink, location, description);
       images.add(newImage);
   }

   public ArrayList getImages(){
       return images;
   }

}
