package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText title, genre, year;
    Button btnInsert, btnShowList;
    Spinner rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        title = findViewById(R.id.etTitle);
        genre = findViewById(R.id.etGenre);
        year = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnAdd);
        btnShowList = findViewById(R.id.btnReturn);
        rating = findViewById(R.id.spinnerRates);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!title.getText().toString().isEmpty()){
//                    may add later
//                }
                String inTitle = title.getText().toString();
                String inGenre = genre.getText().toString();
                String yearText = year.getText().toString();
                int inyear = Integer.parseInt(yearText);
                String inRating = rating.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(InsertActivity.this);
                long insert_id = dbh.insertMovie(inTitle, inGenre, inyear, inRating);

                if (insert_id != -1) {//if insertion is successful
                    Toast.makeText(InsertActivity.this, "Added " + inTitle + " to the database successfully", Toast.LENGTH_LONG).show();
                    title.setText("");
                    genre.setText("");
                    year.setText("");
                    rating.setSelection(1);
                } else {//insertion failed
                    Toast.makeText(InsertActivity.this, inTitle + " failed to insert into database", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
