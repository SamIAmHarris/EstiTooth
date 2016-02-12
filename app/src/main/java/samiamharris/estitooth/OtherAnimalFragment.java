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
public class OtherAnimalFragment extends Fragment {

    TextView animalTitleTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_other_animal, container, false);

        animalTitleTextView = (TextView) rootView.findViewById(R.id.fragment_swipe_animal_title);

        ImageView otherAnimalImage = (ImageView) rootView.findViewById(R.id.other_animal_image);
        Picasso.with(getActivity()).load(R.drawable.elephant).centerInside().fit().into(otherAnimalImage);

        return rootView;
    }


}
