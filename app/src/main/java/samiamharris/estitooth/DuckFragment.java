package samiamharris.estitooth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by SamMyxer on 1/29/16.
 */
public class DuckFragment extends Fragment{
    TextView animalTitleTextView;

    private String[] animales = {"Elephant", "Tiger", "Rooster", "Hippo", "Jeff Zhao"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_duck, container, false);

        animalTitleTextView = (TextView) rootView.findViewById(R.id.fragment_swipe_animal_title);
        ImageView duckImage = (ImageView) rootView.findViewById(R.id.duck_image_view);
        Picasso.with(getActivity()).load(R.drawable.rubber_ducky).centerCrop().fit().into(duckImage);
        return rootView;
    }


    public void setAnimalTitle(int position) {
        animalTitleTextView.setText(animales[position]);
    }

}
