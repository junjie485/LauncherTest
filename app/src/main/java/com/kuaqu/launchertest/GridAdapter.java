package com.kuaqu.launchertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResolveInfo> mApps;
    private Context context;

    public GridAdapter(List<ResolveInfo> mApps, Context context) {
        this.mApps = mApps;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemViewHolder, int position) {
            ViewHolder holder= (ViewHolder) itemViewHolder;
            final ResolveInfo info=mApps.get(position);
            holder.ivIcon.setImageDrawable(info.activityInfo.loadIcon(context.getPackageManager()));
            holder.tvName.setText(info.loadLabel(context.getPackageManager()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ComponentName componentName=new ComponentName(info.activityInfo.packageName,info.activityInfo.name);
                    Intent intent=new Intent();
                    intent.setComponent(componentName);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivIcon;
        private TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.ivIcon);
            tvName=itemView.findViewById(R.id.tvName);
        }


    }

}
