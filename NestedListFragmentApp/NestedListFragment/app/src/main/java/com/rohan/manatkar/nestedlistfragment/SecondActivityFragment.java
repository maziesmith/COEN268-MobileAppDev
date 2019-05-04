package com.rohan.manatkar.nestedlistfragment;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondActivityFragment extends Fragment {
    private ArrayList<String> Breed;
    private ArrayAdapter<String> breedAdapter;
    private ListView lvItems;
    public final static String EXTRA_MESSAGE = "com.rohan.manatkar.MESSAGE";

    public SecondActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        final Integer position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        lvItems = (ListView) getActivity().findViewById(R.id.list_of_items);
        Breed = new ArrayList<String>();
        breedAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Breed);
        lvItems.setAdapter(breedAdapter);

        switch (position) {
            case 0: Breed.add("Bulldog");
                    Breed.add("German Shepherd");
                    Breed.add("Labrador Retriever");
                    Breed.add("Poodle");
                break;

            case 1: Breed.add("Persian Cat");
                    Breed.add("Siamese Cat");
                    Breed.add("Russian Blue");
                    Breed.add("Sphynx Cat");
                break;

            case 2: Breed.add("Parrot");
                    Breed.add("Cockatoo");
                    Breed.add("Macaw");
                    Breed.add("Chicken");
                break;

            case 3: Breed.add("Koi");
                    Breed.add("Goldfish");
                    Breed.add("Shark");
                    Breed.add("Octopus");
                break;

            case 4: Breed.add("Snake");
                    Breed.add("Lizard");
                    Breed.add("Crocodile");
                    Breed.add("Alligator");
                break;
        }

        SecondActivityFragment2 obj = (SecondActivityFragment2) getActivity().getSupportFragmentManager().findFragmentById(R.id.frame);

        if (obj == null) {
            obj = new SecondActivityFragment2();
            obj.init(Breed.get(0));
            android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame, obj);

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String type = (String) lvItems.getItemAtPosition(i);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Intent myIntent = new Intent(getActivity(), WebActivity.class);
                    myIntent.putExtra(EXTRA_MESSAGE, type);
                    startActivity(myIntent);
                }
                else {
                    SecondActivityFragment2 obj = (SecondActivityFragment2) getActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.frame);
                    obj.updateWebView(type);

                }
            }
        });
    }
}
