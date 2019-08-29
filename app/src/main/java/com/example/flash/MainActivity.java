package com.example.flash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
 private ImageButton switchOff,switchOn;
 private Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchOff = findViewById(R.id.switchOff);
        switchOn = findViewById(R.id.switchOn);
        camera = Camera.open();
        final Camera.Parameters parameters = camera.getParameters();
        switchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchOff.setVisibility(View.GONE);
                switchOn.setVisibility(View.VISIBLE);
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        });
        switchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchOff.setVisibility(View.VISIBLE);
                switchOn.setVisibility(View.GONE);
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.AboutId){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.HelpId){
            Intent intent = new Intent(this,Main3Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder bulider = new AlertDialog.Builder(this);
        bulider.setMessage("Are you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("yea", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = bulider.create();
        alertDialog.show();

    }
}
