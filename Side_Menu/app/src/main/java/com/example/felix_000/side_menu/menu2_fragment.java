package com.example.felix_000.side_menu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by felix_000 on 4/12/2015.
 */
public class menu2_fragment extends Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
     rootView = inflater.inflate(R.layout.menu2_layout, container, false);
     return rootView;
    }
}
