package com.example.felix_000.naievents_beta;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;


public class EventListingActivity extends ActionBarActivity {
    private RecyclerView rv;
    private ProgressBar progressBar;
    private ArrayList<EventsFormAtt> eventsFormAtts;
   private Object[] events ;
    private ParseQueryAdapter<ParseObject> mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_listing);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        rv = (RecyclerView) findViewById(R.id.rv);
       // ListView listView = (ListView) findViewById(R.id.listView);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        ParseCrashReporting.enable(this);
        //Enable Local Datastores
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "yKTdQtjm9QjPKlym7zVFjJrXjbmAX5EncrgUZCbV", "pMSZxLIDpkcgqxIiTYAM1Ybh58uGesRKt5N1nXHf");
        getEvents();

        initializeAdapter();


    }

    private void initializeAdapter() {
//        RVAdapter adapter;
//
//        adapter = new RVAdapter(eventsFormAtts);
//        rv.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        EventsAdapter adapter;
        adapter = new EventsAdapter(this);
        rv.setAdapter(adapter);

    }


    private void initializeData() {
        eventsFormAtts = new ArrayList<>();
        eventsFormAtts.add(new EventsFormAtt("Ruby Conference", "2/3/2015 at 2pm", " venue:Nairobi garage", R.drawable.rubyconf));
        eventsFormAtts.add(new EventsFormAtt("Entrepreneurs conf", "4/5/2015 from 9 am","venue:ihub", R.drawable.saflive));
        eventsFormAtts.add(new EventsFormAtt("Groove awards","2/7/2015 from 6 pm","venue: kicc",R.drawable.sports));
        eventsFormAtts.add(new EventsFormAtt("Ruby Conference", "2/3/2015 at 2pm", " venue:Nairobi garage", R.drawable.grooveimg));
        eventsFormAtts.add(new EventsFormAtt("Entrepreneurs conf", "4/5/2015 from 9 am", "venue:iHub", R.drawable.bussiness));
    }

   private void getEvents(){

       ParseQuery<ParseObject> query = ParseQuery.getQuery("EventsData");
       query.findInBackground(new FindCallback<ParseObject>() {
           public void done(List<ParseObject> eventList, ParseException e) {
               if (e == null) {
                   events = eventList.toArray();
                   Log.d("score", "Retrieved " + eventList.size() + " scores");
               } else {

               }
           }
       });
   }
}












