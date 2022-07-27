package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
                String inTitle = title.getText().toString();
                String inGenre = genre.getText().toString();
                String yearText = year.getText().toString();
                int inyear = Integer.parseInt(yearText);
                String inRating = rating.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(InsertActivity.this);

            }

        });

    }
}