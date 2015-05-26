package com.example.felix_000.naievents_beta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by felix_000 on 5/21/2015.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private ParseQueryAdapter<ParseObject> parseAdapter;

    private ViewGroup parseParent;

    private EventsAdapter eventsAdapter = this;

    public EventsAdapter(Context context) {
        //parseParent = parentIn;
        parseAdapter = new ParseQueryAdapter<ParseObject>(context, "EventsData") {

            @Override
            public View getItemView(ParseObject object, View v, ViewGroup parent) {
                if (v == null) {
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventscard, parent, false);
                }
                super.getItemView(object, v, parent);

                ParseImageView imageView = (ParseImageView) v.findViewById(R.id.imageView);
                imageView.setParseFile(object.getParseFile("Logo"));
                imageView.loadInBackground();
                TextView title = (TextView) v.findViewById(R.id.title);
                title.setText(object.getString("Title"));
                TextView location = (TextView) v.findViewById(R.id.location);
                location.setText(object.getString("Location"));
                TextView date = (TextView) v.findViewById(R.id.date);
                date.setText(object.getString("fromDate"));
                return v;
            }
        };
        parseAdapter.addOnQueryLoadListener(new OnQueryLoadListener());
        parseAdapter.loadObjects();
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventscard, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        parseAdapter.getView(position, holder.cardView, parseParent);
    }

    @Override
    public int getItemCount() {
        return parseAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = v;
        }
    }

    public class OnQueryLoadListener implements ParseQueryAdapter.OnQueryLoadListener<ParseObject> {

        public void onLoading() {

        }

        public void onLoaded(List<ParseObject> objects, Exception e) {
            eventsAdapter.notifyDataSetChanged();
        }
    }
}