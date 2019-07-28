package ersa97.raven.eyeson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class JadwalFragment extends Fragment {

    JadwalAdapter mAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerViewJadwal;
    CardView cardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.jadwal_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new JadwalAdapter();
        recyclerViewJadwal = view.findViewById(R.id.recyclerview);
        recyclerViewJadwal.setAdapter(mAdapter);
        recyclerViewJadwal.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        getData();

    }

    private void getData() {
        db.collection("Schedule").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Jadwal> list = new ArrayList<>();
                        for (DocumentSnapshot doc : task.getResult()) {
                            list.add(doc.toObject(Jadwal.class));
                        }
                        mAdapter.addData(list);
                    }
                });
    }
}

