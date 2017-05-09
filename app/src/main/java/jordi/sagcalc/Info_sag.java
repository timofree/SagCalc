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
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Jordi on 22/02/2016.
 */
public class Info_sag extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    FloatingActionButton atras;
    FloatingActionButton adelante;
    ViewFlipper fliper;
    private  int cont =0;


    TextView info;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_sag);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        atras = (FloatingActionButton) findViewById(R.id.btn_atras);
        atras.setOnClickListener(this);
        adelante = (FloatingActionButton) findViewById(R.id.btn_adelante);
        adelante.setOnClickListener(this);




        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        fliper=(ViewFlipper)findViewById(R.id.viewSwitcher);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);

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
                Intent inte4 = new Intent(Info_sag.this, AboutActivity.class);
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
                                Intent inte2 = new Intent(Info_sag.this, Linea.class);
                                startActivity(inte2);
                                return true;
                            case R.id.circulos:
                                menuItem.setChecked(true);
                                Intent inte22 = new Intent(Info_sag.this, Circulos.class);
                                startActivity(inte22);
                                return true;
                            case R.id.calculadora:
                                menuItem.setChecked(true);
                                Intent inte23 = new Intent(Info_sag.this, Calculadora.class);
                                startActivity(inte23);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_info:
                                menuItem.setChecked(true);
                                Intent inte1 = new Intent (Info_sag.this, Info_sag.class);
                                startActivity(inte1);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_como:
                                menuItem.setChecked(true);
                                Intent inte19 = new Intent(Info_sag.this, Info_app.class);
                                startActivity(inte19);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;



                            case R.id.about:
                                menuItem.setChecked(true);
                                Intent inte4 = new Intent(Info_sag.this, AboutActivity.class);
                                startActivity(inte4);
                                return true;



                        }
                        return true;
                    }
                });
    }

    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_adelante:
               cont++;
                if(cont<10)
                fliper.showNext();
                else
                    cont=9;


                break;
            case R.id.btn_atras:
                cont--;
                if(cont >= 0)
                fliper.showPrevious();
                else
                cont=0;
                break;

        }


    }
    public void openslopes( View slopes) throws Exception
    {
        String link = "http://www.slopes.es";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
}

