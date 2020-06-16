package com.example.wintecwaiata;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Wintec Waiata");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainPageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_mainpage);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_mainpage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainPageFragment()).commit();
                getSupportActionBar().setTitle("Wintec Waiata");
                break;
            case R.id.nav_aboutapp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutAppFragment()).commit();
                getSupportActionBar().setTitle("About App");
                break;
            case R.id.nav_relationshipwithtainui:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TainuiFragment()).commit();
                getSupportActionBar().setTitle("Relationshop with Tainiu");
                break;
            case R.id.nav_wintecmarae:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MareaFragment()).commit();
                getSupportActionBar().setTitle("Wintec Marae");
                break;
            case R.id.nav_carvings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CarvingListFragment()).commit();
                getSupportActionBar().setTitle("Carvings");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finishAffinity();
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }

}
