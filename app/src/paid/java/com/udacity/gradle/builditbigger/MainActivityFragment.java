package com.udacity.gradle.builditbigger;

/**
 * Created by arbalan on 1/22/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button tell_joke = (Button) root.findViewById(R.id.tell_joke_button);

        tell_joke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JokeTellerAsyncTask(getActivity()).execute();
            }
        });
        return root;
    }
}
