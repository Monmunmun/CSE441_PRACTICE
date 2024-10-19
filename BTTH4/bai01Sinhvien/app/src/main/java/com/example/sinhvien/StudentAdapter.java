package com.example.sinhvien;

import com.example.sinhvien.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.nameTextView.setText(student.getHoten());
        holder.classTextView.setText(student.getLop());
        holder.gpaTextView.setText(String.valueOf(student.getDiem()));

        holder.editButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditStudentActivity.class);
            intent.putExtra("studentKey", student.getKey());
            intent.putExtra("studentName", student.getHoten());
            intent.putExtra("studentClass", student.getLop());
            intent.putExtra("studentGpa", String.valueOf(student.getDiem()));
            view.getContext().startActivity(intent);
        });
        holder.deleteButton.setOnClickListener(view -> {

            String key = studentList.get(position).getKey();
            DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("sinhvien").child(key);

            studentRef.removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(view.getContext(), "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                        studentList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .addOnFailureListener(e -> Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, classTextView, gpaTextView;
        Button editButton,deleteButton;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_student_name);
            classTextView = itemView.findViewById(R.id.tv_student_class);
            gpaTextView = itemView.findViewById(R.id.tv_student_gpa);
            editButton = itemView.findViewById(R.id.btn_edit_student);
            deleteButton = itemView.findViewById(R.id.btn_delete_student);
        }
    }
}
