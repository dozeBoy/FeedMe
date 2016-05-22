package POJOs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by vasin on 5/22/2016.
 */

public class RecipeCardViewList extends ArrayList<RecipeCardView> implements Parcelable {

    protected RecipeCardViewList(Parcel in) {
        readFromParcel(in);
    }

    public RecipeCardViewList(){

    }

    public static final Creator<RecipeCardViewList> CREATOR = new Creator<RecipeCardViewList>() {
        @Override
        public RecipeCardViewList createFromParcel(Parcel in) {
            return new RecipeCardViewList(in);
        }

        @Override
        public RecipeCardViewList[] newArray(int size) {
            return new RecipeCardViewList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        int size = this.size();

        //We have to write the list size, we need him recreating the list

        dest.writeInt(size);

        for (int i = 0; i < size; i++) {

            RecipeCardView recipeCardView = this.get(i);

            dest.writeString(recipeCardView.getUrl());
            dest.writeString(recipeCardView.getDescription());
            dest.writeLong(recipeCardView.getId());
            dest.writeString(recipeCardView.getImageURL());
            dest.writeString(recipeCardView.getName());
            dest.writeInt(recipeCardView.getTime());
            dest.writeInt(recipeCardView.getCalories());
        }
    }

    private void readFromParcel(Parcel in) {

        this.clear();


        //First we have to read the list size

        int size = in.readInt();


        //Reading remember that we wrote first the Name and later the Phone Number.

        //Order is fundamental


        for (int i = 0; i < size; i++) {

            RecipeCardView recipeCardView = new RecipeCardView();


            recipeCardView.setUrl(in.readString());
            recipeCardView.setDescription(in.readString());
            recipeCardView.setId(in.readLong());
            recipeCardView.setImageURL(in.readString());
            recipeCardView.setName(in.readString());
            recipeCardView.setTime(in.readInt());
            recipeCardView.setCalories(in.readInt());


            this.add(recipeCardView);

        }


    }

}
