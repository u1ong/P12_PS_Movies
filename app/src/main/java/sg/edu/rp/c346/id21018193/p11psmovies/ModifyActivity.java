package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ModifyActivity extends AppCompatActivity {
    EditText title, genre, year, id;
    Button btnUpdate, btnDelete, btnCancel;
    Spinner rating;
    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        id = findViewById(R.id.etID);
        title = findViewById(R.id.etTitle);
        genre = findViewById(R.id.etGenre);
        year = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rating = findViewById(R.id.spinnerRates);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        id.setText(data.getId()+"");
        title.setText(data.getTitle());
        genre.setText(data.getGenre());
        year.setText(data.getYear()+"");
        if(data.getRating()=="G")
        {
            rating.setSelection(0);
        }else if(data.getRating()=="PG"){
            rating.setSelection(1);
        }else if(data.getRating()=="PG13"){
            rating.setSelection(2);
        }else if(data.getRating()=="NC16"){
            rating.setSelection(3);
        }else if(data.getRating()=="M18"){
            rating.setSelection(4);
        }else if(data.getRating()=="R21"){
            rating.setSelection(5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setGenre(genre.getText().toString());
                data.setId(Integer.parseInt(id.getText().toString()));
                data.setTitle(title.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                data.setRating(rating.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteMovie(data.getId());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(ModifyActivity.this,
                        MainActivity.class);
                startActivity(j);
            }
        });

    }


}