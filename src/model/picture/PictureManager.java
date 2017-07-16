package model.picture;

import model.picture.Picture;

import java.util.ArrayList;
import java.util.HashMap;


public class PictureManager {

    public static final ArrayList<Picture> images = new ArrayList<>();

    public static void addImage(Picture picture){
       images.add(picture);
    }

    public static ArrayList<Picture> getImages(){
        return images;
    }

    public static ArrayList<Picture> searchImagesByLocation(String location) {
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for (Picture image : images) {
            if (image.getLocation().contains(location)) {
                searchImagesArrayList.add(image);
            }
        }
        return searchImagesArrayList;
    }

    public static ArrayList<Picture> searchImagesByContinent(String continent){
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for(Picture image : images){
            if(image.getContinent().contains(continent)){
                searchImagesArrayList.add(image);
            }
        }
        return searchImagesArrayList;
    }

    public static void clearPictures(){
        images.clear();
    }
}
