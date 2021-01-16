package com.lokendrasingh.welcome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter extends FirebaseRecyclerAdapter<model, adapter.myviewholder> {

    public adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull model model) {

        myviewholder.name.setText(model.getName());
        myviewholder.fathername.setText(model.getFatherName());
        myviewholder.email.setText(model.getEmail());
        myviewholder.gender.setText(model.getGender());
// we use it to fetch image from database         Glide.with(myviewholder.img.getContext()).load(model.ge)

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent,false);
    return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, fathername, email, gender;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            fathername= itemView.findViewById(R.id.father);
            gender= itemView.findViewById(R.id.gender);
            email= itemView.findViewById(R.id.email);
        }
    }

}
