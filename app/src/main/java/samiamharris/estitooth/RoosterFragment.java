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
 * Created by SamMyxer on 12/8/15.
 */
public class RoosterFragment extends Fragment {

    TextView animalTitleTextView;

    private String[] animales = {"Elephant", "Tiger", "Rooster", "Hippo", "Jeff Zhao"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_rooster, container, false);

        animalTitleTextView = (TextView) rootView.findViewById(R.id.fragment_swipe_animal_title);
        ImageView roosterImage = (ImageView) rootView.findViewById(R.id.rooster_image_view);
        Picasso.with(getActivity()).load(R.drawable.page_1).centerInside().fit().into(roosterImage);

        return rootView;
    }


    public void setAnimalTitle(int position) {
        animalTitleTextView.setText(animales[position]);
    }

}
