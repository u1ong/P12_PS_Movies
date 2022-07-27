package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnShow, btnInsert;
    ListView lvMovies;
    ArrayList<Movies> alMoviesList;
    CustomAdapter caMovies;
    Spinner spnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test
        btnShow = findViewById(R.id.buttonShow);
        btnInsert = findViewById(R.id.buttonInsert);
        lvMovies = findViewById(R.id.listViewMovies);
        spnFilter = findViewById(R.id.spinner);
        alMoviesList = new ArrayList<>();
        caMovies = new CustomAdapter(this, R.layout.row, alMoviesList);
        lvMovies.setAdapter(caMovies);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                alMoviesList.clear();
                alMoviesList.addAll(dbh.getAllMovie());
                caMovies.notifyDataSetChanged();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                       InsertActivity.class);
                startActivity(i);
            }
        });

        //filter isnt working for now
        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String filtertext ="";
                if(spnFilter.getSelectedItem().equals("G")){
                    filtertext = "G";
                } else if(spnFilter.getSelectedItem().equals("PG")) {
                    filtertext = "PG";
                } else if(spnFilter.getSelectedItem().equals("PG13")) {
                    filtertext = "PG13";
                } else if(spnFilter.getSelectedItem().equals("NC16")) {
                    filtertext = "NC16";
                } else if(spnFilter.getSelectedItem().equals("M18")){
                    filtertext = "M18";
                }else if(spnFilter.getSelectedItem().equals("R21")){
                    filtertext = "R21";
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                alMoviesList.clear();
                alMoviesList.addAll(dbh.getFilterMovie(filtertext));
                caMovies.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Movies data = alMoviesList.get(position);
                Intent editI = new Intent(MainActivity.this, ModifyActivity.class);
                editI.putExtra("data", data);
                startActivity(editI);
            }
        });

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