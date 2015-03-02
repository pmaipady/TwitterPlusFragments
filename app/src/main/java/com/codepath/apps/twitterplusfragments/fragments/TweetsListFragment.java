package com.codepath.apps.twitterplusfragments.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.twitterplusfragments.R;
import com.codepath.apps.twitterplusfragments.TweetsArrayAdapter;
import com.codepath.apps.twitterplusfragments.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prit on 2/24/2015.
 */
public class TweetsListFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_tweets_list, parent, false);
        lvTweets=(ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        return v;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }{
    //    tweets= new ArrayList<>();
//        aTweets= new TweetsArrayAdapter(getActivity(),tweets);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
         tweets= new ArrayList<>();
          aTweets= new TweetsArrayAdapter(getActivity(),tweets);
    }

    public void addAll(List<Tweet> tweets){
    aTweets.addAll(tweets);
   }

}
