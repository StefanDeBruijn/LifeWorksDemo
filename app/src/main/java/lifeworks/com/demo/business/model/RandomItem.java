package lifeworks.com.demo.business.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RandomItem implements Parcelable {

    private String title;
    private String fullText;
    private String image;

    public RandomItem(String title, String fullText, String image) {
        this.title = title;
        this.fullText = fullText;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected RandomItem(Parcel in) {
        title = in.readString();
        fullText = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(fullText);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RandomItem> CREATOR = new Parcelable.Creator<RandomItem>() {
        @Override
        public RandomItem createFromParcel(Parcel in) {
            return new RandomItem(in);
        }

        @Override
        public RandomItem[] newArray(int size) {
            return new RandomItem[size];
        }
    };
}