package com.example.vrstyproject.home.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vrstyproject.R;
import com.example.vrstyproject.bookread.ReadBookActivity;
import com.example.vrstyproject.model.PdfModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PdfBookAdapter extends FirebaseRecyclerAdapter<PdfModel,PdfBookAdapter.PdfViewHolder> {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public PdfBookAdapter(@NonNull FirebaseRecyclerOptions<PdfModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PdfViewHolder holder, int position, @NonNull PdfModel model) {
          holder.title.setText(model.getPdfName());

          holder.image1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(holder.image1.getContext(), ReadBookActivity.class);
                  intent.putExtra("filename",model.getPdfName());
                  intent.putExtra("fileUrl",model.getPdfUrl());

                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  holder.image1.getContext().startActivity(intent);
              }
          });
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_book_item,parent,false);
        return new PdfViewHolder(view);
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView title;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            image1=itemView.findViewById(R.id.ivImage);
            title=itemView.findViewById(R.id.tvTitle);

        }
    }
}
