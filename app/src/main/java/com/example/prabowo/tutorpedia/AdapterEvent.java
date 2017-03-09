package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabowo.tutorpedia.CekSoal.CekSoal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tommy on 2/17/2017.
 */

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ViewHolder> {

        private FirebaseAuth firebaseAuth;

        DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

        private List<ListEventItemBeneran> listItems;
        private Context context;

        public AdapterEvent(List<ListEventItemBeneran> listItems, Context context) {
            this.listItems = listItems;
            this.context = context;
        }

    @Override
    public AdapterEvent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event,parent,false);
        return new ViewHolder(v);
    }



        @Override
        public void onBindViewHolder(AdapterEvent.ViewHolder holder, int position) {

            ListEventItemBeneran listItem = listItems.get(position);

            holder.TVheadeventBeneran.setText(listItem.getJuduleventbeneran());
            holder.TVdesceventBeneran.setText(listItem.getDeskripsieventbeneran());
            holder.NilaiAkhir.setText(listItem.getNilai());
            holder.WaktuMengerjakan.setText(listItem.getTime());
            Picasso.with(context)
                    .load(listItem.getImageUrleventbeneran())
                    .into(holder.IVgambareventBeneran);
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }




        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView TVheadeventBeneran;
            private TextView TVdesceventBeneran;
            public ImageView IVgambareventBeneran;
            private TextView WaktuMengerjakan;
            private TextView NilaiAkhir;


            public ViewHolder(View itemView) {
                super(itemView);

                TVheadeventBeneran = (TextView) itemView.findViewById(R.id.TVheadeventBeneran);
                TVdesceventBeneran = (TextView) itemView.findViewById(R.id.TVdesceventBeneran);
                IVgambareventBeneran = (ImageView) itemView.findViewById(R.id.IVgambareventBeneran);
                WaktuMengerjakan = (TextView) itemView.findViewById(R.id.WaktuMengerjakan);
                NilaiAkhir = (TextView) itemView.findViewById(R.id.NilaiAkhir);
                itemView.setClickable(true);
                itemView.setOnClickListener(this);
            }

            public void onClick(final View v) {
                int recyclerItemPosition;
                if (CekSoal.selesai==1){
                Toast.makeText(v.getContext(), "Soal Sudah Dikerjakan", Toast.LENGTH_SHORT).show();}
                final Intent intent;
                if (CekSoal.selesai==0) {
                    switch (getAdapterPosition()) {
                        default:
                            recyclerItemPosition = getAdapterPosition();

                            intent = new Intent(context, Soal.class);
                            intent.putExtra("PosisiItemRecycler", recyclerItemPosition);


                            break;
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        }

    }


