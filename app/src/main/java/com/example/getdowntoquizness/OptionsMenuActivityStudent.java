package com.example.getdowntoquizness;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class OptionsMenuActivityStudent extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AppBarLayout mAppBarLayout;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentTemp = fm.findFragmentById(R.id.fragContStudent);
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.view_pager);
        mAppBarLayout = findViewById(R.id.studentTitle);
        switch (item.getItemId()){
            case R.id.menu_student_home:

                showMessage("Home");
                if(fragmentTemp != null){
                    showStudentTabs();
                    fm.beginTransaction().remove(fragmentTemp).commit();
                }
                break;
            case R.id.menu_student_logout:
                showMessage("You logged out successfully");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_student_editProfile:
                showMessage("Edit Profile");
                break;
            case R.id.menu_student_changePassword:
                showMessage("Change password");
                break;
            case R.id.menu_student_help:
                hideStudentTabs();
                showMessage("Help");
                fragmentTemp = new HelpFragment();
                fm.beginTransaction().replace(R.id.fragContStudent, fragmentTemp)
                        .addToBackStack(null).commit();
                break;
            case R.id.menu_student_about:
                hideStudentTabs();
                showMessage("About");
                fragmentTemp = new AboutFragment();
                fm.beginTransaction().replace(R.id.fragContStudent, fragmentTemp)
                        .addToBackStack(null).commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void hideStudentTabs(){
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.INVISIBLE);
        mAppBarLayout.setVisibility(View.INVISIBLE);
    }

    public void showStudentTabs(){
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        mAppBarLayout.setVisibility(View.VISIBLE);
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
