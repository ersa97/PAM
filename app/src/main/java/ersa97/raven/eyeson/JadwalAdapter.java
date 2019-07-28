package ersa97.raven.eyeson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {

    List<Jadwal> jadwalList = new ArrayList<>();
    private OnDeleteListener listener;


    @NonNull
    @Override
    public JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JadwalViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jadwal_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalViewHolder holder, int position) {
        holder.bind(jadwalList.get(position));
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public void addData(List<Jadwal> data){
        jadwalList.clear();
        jadwalList.addAll(data);
        notifyDataSetChanged();
    }

    public void deleteItem(int position, OnDeleteListener listener) {
        listener.OnDelete(jadwalList.get(position));
    }

    class JadwalViewHolder extends RecyclerView.ViewHolder{

        TextView tvJadwal, tvDosen, tvSubject;

        public JadwalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJadwal = itemView.findViewById(R.id.TextViewJadwal);
            tvDosen = itemView.findViewById(R.id.TextViewDosen);
            tvSubject = itemView.findViewById(R.id.TextViewSubjek);
        }

        public void bind(Jadwal jadwal){
            tvJadwal.setText(itemView.getContext().getString(R.string.date_format, jadwal.getTime()));
            tvSubject.setText(jadwal.getSubject());
            tvDosen.setText(jadwal.getTeacher());
        }
    }

    interface OnDeleteListener{
        void OnDelete(Jadwal jadwal);
    }
}
