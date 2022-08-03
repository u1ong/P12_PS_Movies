package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {
    EditText title, genre, year, id;
    Button btnUpdate, btnDelete, btnCancel;
    Spinner setRate;
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
        setRate = findViewById(R.id.spRating);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        id.setText(data.getId()+"");
        title.setText(data.getTitle());
        genre.setText(data.getGenre());

        year.setText(data.getYear()+"");
        switch(data.getRating()){
            case "G":
                setRate.setSelection(0);
                break;
            case "PG":
                setRate.setSelection(1);
                break;
            case "PG13":
                setRate.setSelection(2);
                break;
            case "NC16":
                setRate.setSelection(3);
                break;
            case "M18":
                setRate.setSelection(4);
                break;
            case "R21":
                setRate.setSelection(5);
                break;
        }
//        if(data.getRating().equals("G")){
//            setRate.setSelection(0);
//        }else if(data.getRating().equals("PG")){
//            setRate.setSelection(1);
//        }else if(data.getRating().equals("PG13")){
//            setRate.setSelection(2);
//        }else if(data.getRating().equals("NC16")){
//            setRate.setSelection(3);
//        }else if(data.getRating().equals("M18")){
//            setRate.setSelection(4);
//        }else if(data.getRating().equals("R21")){
//            setRate.setSelection(5);
//        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setGenre(genre.getText().toString());
                data.setId(Integer.parseInt(id.getText().toString()));
                data.setTitle(title.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                data.setRating(setRate.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();

                Toast.makeText(ModifyActivity.this, "Update successful", Toast.LENGTH_LONG).show();
                Intent back = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                myBuilder.setTitle("Delete");
                myBuilder.setMessage("Are you sure you want to delete: " + getTitle());
                myBuilder.setCancelable(true);
                //Configure the 'positive' button
                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh.deleteMovie(data.getId());
                        Toast.makeText(ModifyActivity.this, "Delete successful", Toast.LENGTH_LONG).show();
                        Intent back = new Intent(ModifyActivity.this, MainActivity.class);
                        startActivity(back);
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);
                myBuilder.setTitle("Discard Changes");
                myBuilder.setMessage("Are you sure you want to discard changes? ");
                myBuilder.setCancelable(true);
                //Configure the 'positive' button
                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(ModifyActivity.this,
                                MainActivity.class);
                        startActivity(j);
                    }
                });

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Do not discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }


}