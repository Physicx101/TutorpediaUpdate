package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prabowo on 14/02/2017.
 */

public class MyAdapterKonsultasi extends RecyclerView.Adapter<MyAdapterKonsultasi.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemKonsultasi> listItems;
    private Context context;

    public MyAdapterKonsultasi(List<ListItemKonsultasi> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterKonsultasi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item_konsultasi,parent,false);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(MyAdapterKonsultasi.ViewHolder holder, int position) {

        ListItemKonsultasi listItem = listItems.get(position);

        holder.TVheadkonsultasi.setText(listItem.getJudulkonsultasi());
        holder.TVdesckonsultasi.setText(listItem.getDeskripsikonsultasi());
        /*Picasso.with(context)
                .load(listItem.getImageUrlkonsultasi())
                .into(holder.IVgambarkonsultasi);*/

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVheadkonsultasi;
        private TextView TVdesckonsultasi;
        public ImageView IVgambarkonsultasi;


        public ViewHolder(View itemView) {
            super(itemView);

            TVheadkonsultasi = (TextView) itemView.findViewById(R.id.TVheadkonsultasi);
            TVdesckonsultasi = (TextView) itemView.findViewById(R.id.TVdesckonsultasi);
            IVgambarkonsultasi = (ImageView) itemView.findViewById(R.id.IVgambarkonsultasi);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }



        public void onClick(final View v) {
            int recyclerItemPosition;




            final Intent intent;
            Konsultasi datah=new Konsultasi();
            System.out.print("Tes 2" + datah.a + datah.b);
            switch (getAdapterPosition()){
                default:

                    recyclerItemPosition = getAdapterPosition();
                    intent = new Intent(context,IsiKonsultasi.class);
                    intent.putExtra("PosisiItemRecycler",recyclerItemPosition);
                    intent.putExtra("Matkuldis",datah.a);
                    intent.putExtra("Jenisdis", datah.b);

                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }
    }

}