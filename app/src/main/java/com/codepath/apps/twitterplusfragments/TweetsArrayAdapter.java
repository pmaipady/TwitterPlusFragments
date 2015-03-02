package com.codepath.apps.twitterplusfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterplusfragments.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prit on 2/19/2015.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_tweet,parent,false);
        }
        ImageView ivProfileImage=(ImageView)convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName= (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvCreatedAt= (TextView)convertView.findViewById(R.id.tvCreatedAt);
        TextView tvBody= (TextView)convertView.findViewById(R.id.tvBody);
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvCreatedAt.setText(tweet.getCreatedAt());
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        return convertView;
    }
}
