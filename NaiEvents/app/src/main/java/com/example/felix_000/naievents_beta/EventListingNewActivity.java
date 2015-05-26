package com.example.felix_000.naievents_beta;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class EventListingNewActivity extends ActionBarActivity {
    private RecyclerView rv;
    private ArrayList<EventsFormAtt> eventsFormAtts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_listing_new);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        initializeData();
        initializeAdapter();
    }
    private void initializeAdapter() {
        RVAdapter adapter;
        adapter = new RVAdapter(eventsFormAtts);
        rv.setAdapter(adapter);
    }
    private void initializeData() {
        eventsFormAtts = new ArrayList<>();
        eventsFormAtts.add(new EventsFormAtt("Ruby Conference", "2/3/2015 at 2pm", " venue:Nairobi garage", R.drawable.img1));
        eventsFormAtts.add(new EventsFormAtt("enterprenuors conf", "4/5/2015 from 9 am","venue:ihub", R.drawable.img2));
        eventsFormAtts.add(new EventsFormAtt("groove awards","2/7/2015 from 6 pm","venue: kicc",R.drawable.img4));
        eventsFormAtts.add(new EventsFormAtt("Ruby Conference", "2/3/2015 at 2pm", " venue:Nairobi garage", R.drawable.img3));
        eventsFormAtts.add(new EventsFormAtt("enterprenuors conf", "4/5/2015 from 9 am", "venue:ihub", R.drawable.img5));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_listing_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    }}
