package com.example.felix_000.african_stories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListStoriesActivity extends ActionBarActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stories);
      //  AddListenerOnButton();
        String[] stories =
       {"That Same Night", "Stolen Soup Aroma", "The Tortoise and The Drum", "How the Chipmunk Got His Stripes", "How The Tortoise Became Bald","Three Sisters"};
        ListAdapter storyListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, stories);
        ListView storyView = (ListView) findViewById(R.id.listView);
        storyView.setAdapter(storyListAdapter);
        storyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hadithi = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListStoriesActivity.this, hadithi, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();

                switch (position){
                    case 0:
                         intent = new Intent(getApplicationContext(), ThatNightActivity.class);
                         break;
                    case 1:
                         intent = new Intent(getApplicationContext(), StolenSoupActivity.class);

                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), DrumActivity.class);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), ChipmunkActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(), BaldTortoiseActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getApplicationContext(), ThreeSistersActivity.class);
                        break;

                }
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_stories, menu);
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
    }

}
