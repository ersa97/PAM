package ersa97.raven.eyeson;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PopUpFragment extends DialogFragment {

    private PopUpListener listener;

    public void setListener(PopUpListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_popup, container);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_izin_pulang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnIjinPulangListener();
            }
        });

        view.findViewById(R.id.button_izin_keluar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnIjinKeluarListener();
            }
        });

        view.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpFragment.this.dismiss();
            }
        });

        view.findViewById(R.id.kembali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnSantriKembali();
            }
        });
    }

    interface PopUpListener {
        void OnIjinKeluarListener();
        void OnIjinPulangListener();
        void OnSantriKembali();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(listener != null){
            listener = null;
        }
    }
}
