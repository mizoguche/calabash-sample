package info.mizoguche.tiqavviewer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mizoguche on 2014/08/01.
 */
public class TiqavImage {
    private String id;

    @SerializedName("ext")
    private String extension;
    private int width;
    private int height;

    public String getUrl(){
        return "http://img.tiqav.com/" + id + "." + extension;
    }

    @Override
    public String toString() {
        return "TiqavImage{" +
                "id='" + id + '\'' +
                ", extension='" + extension + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", url='" + getUrl() + '\'' +
                '}';
    }
}
