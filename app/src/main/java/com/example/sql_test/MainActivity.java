package com.example.sql_test;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button button1, button2, button3, button4;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        editText1 = findViewById( R.id.edit1 );
        editText2 = findViewById( R.id.edit2 );
        editText3 = findViewById( R.id.edit3 );
        editText4 = findViewById( R.id.edit4 );
        button1 = findViewById( R.id.bt1 );
        button2 = findViewById( R.id.bt2 );
        button3 = findViewById( R.id.bt3 );
        button4 = findViewById( R.id.bt4 );
        database = new Database( this );
        button1.setOnClickListener( view -> {
            String s2 = editText2.getText().toString();
            String s3 = editText3.getText().toString();
            String s4 = editText4.getText().toString();
            boolean add = database.add( s2, s3, s4 );
            if (add) {
                Toast.makeText( this, "Data has been added", Toast.LENGTH_SHORT ).show();
            } else {
                Toast.makeText( this, "Data not added", Toast.LENGTH_SHORT ).show();
            }
        } );
        button4.setOnClickListener( view -> {
            String s1 = editText1.getText().toString();
            Integer delete = database.delete( s1 );
            if (delete > 0) {
                Toast.makeText( this, "Data deleted", Toast.LENGTH_SHORT ).show();
            } else {
                Toast.makeText( this, "Data not deleted", Toast.LENGTH_SHORT ).show();
            }
        } );
        button3.setOnClickListener( view -> {
            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();
            String s3 = editText3.getText().toString();
            String s4 = editText4.getText().toString();
            boolean update = database.update( s1, s2, s3, s4 );
            if (update) {
                Toast.makeText( this, "Data Update", Toast.LENGTH_SHORT ).show();
            } else {
                Toast.makeText( this, "Data not updated", Toast.LENGTH_SHORT ).show();
            }

        } );
        button2.setOnClickListener( view -> {
            Cursor show = database.show();
            if (show.getCount() == 0) {
                show_data( "SQL DATA", "Data not found" );
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                while (show.moveToNext()) {
                    stringBuilder.append( "ID : " ).append( show.getString( 0 ) ).append( "\n" );
                    stringBuilder.append( "Name : " ).append( show.getString( 1 ) ).append( "\n" );
                    stringBuilder.append( "Email : " ).append( show.getString( 2 ) ).append( "\n" );
                    stringBuilder.append( "Age : " ).append( show.getString( 3 ) ).append( "\n\n" );
                }
                show_data( "SQL DATA", stringBuilder.toString() );
            }
        } );
    }

    public void show_data(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }
}
