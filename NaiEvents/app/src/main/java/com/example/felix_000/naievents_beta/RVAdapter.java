package com.example.felix_000.naievents_beta;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.ViewHolder;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    //List<ParseObject> eventsFormAtts;
    ArrayList<EventsFormAtt> eventsFormAtts;
    private ParseQueryAdapter<ParseObject> parseAdapter;
    public  class PersonViewHolder extends ViewHolder {
        CardView cv;
        TextView textView;
        TextView textView2;
        ImageView imageView;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }



    RVAdapter(ArrayList<EventsFormAtt> eventsFormAtts) {
        this.eventsFormAtts = eventsFormAtts;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_technology, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVAdapter.PersonViewHolder holder, int i) {

        holder.textView.setText(eventsFormAtts.get(i).title);
        holder.textView2.setText(eventsFormAtts.get(i).location);
        holder.imageView.setImageResource(eventsFormAtts.get(i).imageid);
        final Context context=  holder.cv.getContext();
        final String name = String.valueOf(i);
//        holder.cv.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(final View v) {
//
//                Toast.makeText(context, "You selected cardview at index:" + name, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }




    @Override
    public int getItemCount() {
        return eventsFormAtts.size();
    }
}

