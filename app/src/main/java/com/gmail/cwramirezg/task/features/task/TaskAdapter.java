package com.gmail.cwramirezg.task.features.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.data.models.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    protected List<Task> objList;
    private ISelection iSelection;

    public TaskAdapter(List<Task> objList, ISelection iSelection) {
        this.objList = objList;
        this.iSelection = iSelection;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.getAdapterPosition();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task obj = objList.get(position);

        holder.tvDescripcion.setText(obj.getName());
        if ("0".equalsIgnoreCase(obj.getStatus())) {
            holder.tvCompletado.setText("En proceso");
        } else {
            holder.tvCompletado.setText("Completado");
        }

        holder.holder.setOnClickListener(view -> iSelection.onClick(obj));
        holder.holder.setOnLongClickListener(view -> {
            iSelection.onClickLong(obj);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    interface ISelection {
        void onClick(Task task);

        void onClickLong(Task task);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.holder)
        View holder;
        @BindView(R.id.tv_descripcion)
        TextView tvDescripcion;
        @BindView(R.id.tv_completado)
        TextView tvCompletado;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}


