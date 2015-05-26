package com.example.felix_000.naievents_beta;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;


public class FeaturedEventsActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener {
    private SliderLayout myslider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_events);
        myslider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("venue:KICC  date:8/3/2015", R.drawable.grooveimg);
        file_maps.put("Barcelona  date:4/5/2015", R.drawable.rubyconf);
        file_maps.put("venue:KICC  date:10/10/2015", R.drawable.bussiness);
        file_maps.put("venue:Nyayo Stadium  date:8/3/2015 ", R.drawable.saflive);
        file_maps.put("venue:KRFUA date:8/3/2015 ", R.drawable.sports);

        for(String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra", name);

            myslider.addSlider(textSliderView);
        }
        myslider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        myslider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        myslider.setCustomAnimation(new DescriptionAnimation());
        myslider.setDuration(3000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_featured_events, menu);
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

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }
}
