package ersa97.raven.eyeson;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class List_Dashboard extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference studentRef = db.collection("Student");

    private StudentsAdapter adapter;

    FloatingActionButton fab;

    TextView textView;
    String email;

    Toolbar appBarLayout;
    CollapsingToolbarLayout toolbarLayout;

    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    private int ADD_STUDENT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__dashboard);


        user = firebaseAuth.getInstance().getCurrentUser();
        textView = findViewById(R.id.current_user);
        toolbarLayout = findViewById(R.id.collapse_bar);

        toolbarLayout.setTitle(email);

        email = user.getEmail();
        textView.setText(email);


        appBarLayout = findViewById(R.id.top_toolbar);




        fab = (FloatingActionButton) findViewById(R.id.add_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(List_Dashboard.this,AddStudentActivity.class), ADD_STUDENT_CODE);

            }
        });

        recyclerView = findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpRecyclerView();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_STUDENT_CODE && resultCode == RESULT_OK){
            setUpRecyclerView();
        }
    }

    private void setUpRecyclerView(){
        Query query = studentRef.orderBy("nama", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Students> options = new FirestoreRecyclerOptions.Builder<Students>()
                .setQuery(query, Students.class).build();

        adapter = new StudentsAdapter(options);

        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

                new AlertDialog.Builder(viewHolder.itemView.getContext())
                        .setMessage("Yakin?")
                        .setPositiveButton("iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int position = viewHolder.getAdapterPosition();
                                adapter.deleteItem(position);
                            }
                        }).setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                }).create().show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.log_out_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.go_to_account:
                Intent intent = new Intent(List_Dashboard.this, AccountActivity.class);
                startActivity(intent);
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }
}

