package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnShow;
    ListView lvMovies;
    ArrayList<Movies> alMoviesList;
    CustomAdapter caMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test
        btnShow = findViewById(R.id.buttonShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lvMovies = findViewById(R.id.listViewMovies);
        alMoviesList = new ArrayList<>();


        caMovies = new CustomAdapter(this, R.layout.row, alMoviesList);
        lvMovies.setAdapter(caMovies);


    }

    @Override
    protected void onResume(){
        super.onResume();
        DBHelper dbh = new DBHelper(MainActivity.this);
        alMoviesList.clear();
        alMoviesList.addAll(dbh.getAllMovie());
        caMovies.notifyDataSetChanged();
    }
}