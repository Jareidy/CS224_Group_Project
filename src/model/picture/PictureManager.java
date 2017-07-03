package model.picture;

import model.picture.Picture;

import java.util.ArrayList;
import java.util.HashMap;


public class PictureManager {

    private final ArrayList<Picture> images = new ArrayList<>();

    public void addImage(Picture picture){
       images.add(picture);
    }

    public ArrayList<Picture> getImages(){
        return images;
    }

    public ArrayList<Picture> searchImagesByLocation(String location) {
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for (Picture image : images) {
            if (image.getLocation().contains(location)) {
                searchImagesArrayList.add(image);
            }
        }
        return searchImagesArrayList;
    }

    public ArrayList<Picture> searchImagesByContinent(String continent){
        ArrayList<Picture> searchImagesArrayList = new ArrayList<>();
        for(Picture image : images){
            if(image.getContinent().contains(continent)){
                searchImagesArrayList.add(image);
            }
        }
        System.out.println(searchImagesArrayList);
        return searchImagesArrayList;
    }
}
