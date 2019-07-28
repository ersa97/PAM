package ersa97.raven.eyeson;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class IzinFragment extends Fragment {
    IzinListener listener;
    Dialog myDialog;
    Button Izin;
    String pulang = "Izin Pulang";
    String keluar = "Izin Keluar";
    String kembali = "Tidak Izin";
    private Bundle bundle;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DocumentReference perizinan;

    /*public IzinFragment(Bundle bundle) {
        this.bundle = bundle;
    }*/


    public void setListener(IzinListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDialog = new Dialog(requireContext());
        return inflater.inflate(R.layout.izin_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //perizinan = db.collection("Student").document(NamaPatokan);

        Izin = view.findViewById(R.id.izin);


        Izin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }

    public void showPopUp(){

        FragmentManager fm = getChildFragmentManager();
        final PopUpFragment myDialogFragment = new PopUpFragment();
        myDialogFragment.setListener(new PopUpFragment.PopUpListener() {
            @Override
            public void OnIjinKeluarListener() {
                listener.izin(keluar);
                Toast.makeText(requireContext(), "murid terdaftar izin keluar", Toast.LENGTH_SHORT).show();
                //perizinan.update("izin","Izin Keluar");
                myDialogFragment.dismiss();
            }

            @Override
            public void OnIjinPulangListener() {
                listener.izin(pulang);
                Toast.makeText(requireContext(),"murid terdaftar izin pulang", Toast.LENGTH_SHORT).show();
                myDialogFragment.dismiss();

            }

            @Override
            public void OnSantriKembali() {
                listener.izin(kembali);
                Toast.makeText(requireContext(), "santri telah kembali", Toast.LENGTH_SHORT).show();
                myDialogFragment.dismiss();
            }
        });
        myDialogFragment.show(fm, "dialog_fragment");
    }

    interface IzinListener{
        void izin(final String message);
    }

}

