package com.sentisense.projectfy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sentisense.projectfy.Choose;
import com.sentisense.projectfy.patientInfo;
import com.sentisense.projectfy.Model.AddPatient;
import com.sentisense.projectfy.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    List<AddPatient> ls;
    Context context;

    public RVAdapter(Context context,List<AddPatient> list) {
        this.ls=list;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myrow= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(myrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getUsername());
        holder.phno.setText(ls.get(position).getPhno());
       // holder.address.setText(ls.get(position).getAddress());
        holder.age.setText(ls.get(position).getAge());

        final String a=holder.name.getText().toString();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, patientInfo.class);
                intent.putExtra("patid",a);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phno,address,age;
        LinearLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            phno=itemView.findViewById(R.id.phno);
          //  address=itemView.findViewById(R.id.add);
            age=itemView.findViewById(R.id.age);
            layout=itemView.findViewById(R.id.design);
        }
    }
}
