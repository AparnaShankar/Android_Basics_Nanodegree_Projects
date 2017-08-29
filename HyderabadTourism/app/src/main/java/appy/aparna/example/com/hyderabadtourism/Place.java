package appy.aparna.example.com.hyderabadtourism;

/**
 * Created by Administrator on 5/24/2017.
 */

public class Place {
    private String name;
    private String desc;
    private int imageReasourceId;

    //Constructor

    public Place(String name, String desc, int imageReasourceId) {
        this.name = name;
        this.desc = desc;
        this.imageReasourceId = imageReasourceId;
    }

    //Return place name
    public String getName() {
        return name;
    }

    //Return place description
    public String getDesc() {
        return desc;
    }

    //return image resource ID
    public int getImageReasourceId() {
        return imageReasourceId;
    }
}
