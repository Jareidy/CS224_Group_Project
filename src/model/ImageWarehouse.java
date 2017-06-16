package model;

import java.util.HashMap;

public class ImageWarehouse {

    private HashMap<String,String>  imageHashMap= new HashMap<>();

    public HashMap<String,String> imageData(){
        imageHashMap.put("Picture","Arizona");
        imageHashMap.put("Photo","New York");


        return imageHashMap;
    }
}
