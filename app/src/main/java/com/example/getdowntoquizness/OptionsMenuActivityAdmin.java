package com.example.getdowntoquizness;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class OptionsMenuActivityAdmin extends AppCompatActivity implements CommunicatorManageAcc {
    //private CommunicatorManageAcc mCommunicatorManageAcc;
    private Button btnManageAcc, btnCreateQuiz, btnAssignQuiz;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.admin_menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        btnCreateQuiz = findViewById(R.id.btnCreateQuiz);
        btnAssignQuiz = findViewById(R.id.btnAssignQuiz);
        btnManageAcc = findViewById(R.id.btnManageAcc);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentTemp = fm.findFragmentById(R.id.fragment_container);

        switch (item.getItemId()){
            case R.id.menu_home:
                // Toast ....
                showMessage("Home");
//                if(fragmentTemp != null){
////                    showButtons();
//                    fm.beginTransaction().remove(fragmentTemp).commit();
//                }
                break;
                //return true;
            case R.id.menu_admin_logout:
                //return true;
                showMessage("You logged out successfully");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
//            case R.id.menu_admin_quizArchive:
//                //return true;
//                showMessage("quiz archive");
//                break;
            case R.id.menu_admin_help:
                showMessage("Help");
//                hideButtons();
                //fragmentTemp = fm.findFragmentById(R.id.fragContAdminManageAcc);
                fragmentTemp = new HelpFragment();
                fm.beginTransaction().replace(R.id.fragment_container, fragmentTemp)
                        .addToBackStack(null).commit();
                break;
            case R.id.menu_admin_about:
                showMessage("About");
//                hideButtons();
                fragmentTemp = new AboutFragment();
                fm.beginTransaction().replace(R.id.fragment_container, fragmentTemp)
                        .addToBackStack(null).commit();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        //if(fragmentTemp == null){

        //}
        return true;
    }

    public void hideButtons(){
        btnManageAcc.setVisibility(View.INVISIBLE);
        btnCreateQuiz.setVisibility(View.INVISIBLE);
        btnAssignQuiz.setVisibility(View.INVISIBLE);
    }

    public void showButtons(){
        btnManageAcc.setVisibility(View.VISIBLE);
        btnCreateQuiz.setVisibility(View.VISIBLE);
        btnAssignQuiz.setVisibility(View.VISIBLE);
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passDataAdminToManageAcc(Data data) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentAdminMA = fm.findFragmentById(R.id.fragment_container);
    }

}
