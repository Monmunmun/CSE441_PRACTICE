package com.example.btth003;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private OnStudentListener onStudentListener;

    public StudentAdapter(List<Student> studentList, OnStudentListener onStudentListener) {
        this.studentList = studentList;
        this.onStudentListener = onStudentListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView, onStudentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvHoTen.setText(student.getHoTen());
        holder.tvNgaySinh.setText(student.getNgaySinh());
        holder.tvGpa.setText(String.valueOf(student.getGpa()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void updateStudentList(List<Student> newStudentList) {
        this.studentList = newStudentList;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvHoTen;
        public TextView tvNgaySinh;
        public TextView tvGpa;
        OnStudentListener onStudentListener;

        public StudentViewHolder(View view, OnStudentListener onStudentListener) {
            super(view);
            tvHoTen = view.findViewById(R.id.tvHoTen);
            tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
            tvGpa = view.findViewById(R.id.tvGPA);
            this.onStudentListener = onStudentListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onStudentListener.onStudentClick(getAdapterPosition());
        }
    }

    public interface OnStudentListener {
        void onStudentClick(int position);
    }
}


