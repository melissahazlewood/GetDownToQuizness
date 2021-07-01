package com.example.getdowntoquizness;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenuActivityStudent extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_student_home:
                showMessage("Home");
                break;
            case R.id.menu_student_logout:
                showMessage("Logout");
                break;
            case R.id.menu_student_editProfile:
                showMessage("Edit Profile");
                break;
            case R.id.menu_student_changePassword:
                showMessage("Change password");
                break;
            case R.id.menu_student_help:
                showMessage("Help");
                break;
            case R.id.menu_student_about:
                showMessage("About");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.student_menu_layout, menu);
        return true;
    }
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
