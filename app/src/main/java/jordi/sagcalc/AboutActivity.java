package jordi.sagcalc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener  {
    FloatingActionButton ok;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        ok=(FloatingActionButton)findViewById(R.id.btnok);
        ok.setOnClickListener( this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);
    }


    @Override
    public void onClick(View v) {
        Intent inte3 = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(inte3);
    }
    public void openslopes( View slopes) throws Exception
    {
        String link = "http://www.slopes.es";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.about:
                Intent inte4 = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(inte4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //  textView = (TextView) findViewById(R.id.textView);
                        switch (menuItem.getItemId()) {

                            case R.id.btn_linea:
                                menuItem.setChecked(true);
                                Intent inte2 = new Intent(AboutActivity.this, Linea.class);
                                startActivity(inte2);
                                return true;
                            case R.id.circulos:
                                menuItem.setChecked(true);
                                Intent inte22 = new Intent(AboutActivity.this, Circulos.class);
                                startActivity(inte22);
                                return true;
                            case R.id.calculadora:
                                menuItem.setChecked(true);
                                Intent inte23 = new Intent(AboutActivity.this, Calculadora.class);
                                startActivity(inte23);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_info:
                                menuItem.setChecked(true);
                                Intent inte1 = new Intent(AboutActivity.this, Info_sag.class);
                                startActivity(inte1);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_como:
                                menuItem.setChecked(true);
                                Intent inte19 = new Intent(AboutActivity.this, Info_app.class);
                                startActivity(inte19);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;




                            case R.id.about:
                                menuItem.setChecked(true);
                                Intent inte4 = new Intent(AboutActivity.this, AboutActivity.class);
                                startActivity(inte4);
                                return true;






                        }
                        return true;
                    }
                });
    }

}