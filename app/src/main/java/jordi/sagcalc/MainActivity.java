package jordi.sagcalc;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    Button cerrar;

    FloatingActionButton start;
    FloatingActionButton btn_como ;
    FloatingActionButton btn_info;
    FloatingActionButton btn_linea;
    FloatingActionButton btn_circulos ;
    FloatingActionButton btn_calculadora;
    ImageView portada;
    FrameLayout submenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submenu=(FrameLayout)findViewById(R.id.submenu);
        start=(FloatingActionButton)findViewById(R.id.btn_start);
        start.setOnClickListener( this);
        btn_info=(FloatingActionButton)findViewById(R.id.btn_info);
        btn_info.setOnClickListener(this);
        btn_como=(FloatingActionButton)findViewById(R.id.btn_como);
        btn_como.setOnClickListener(this);
        btn_linea=(FloatingActionButton)findViewById(R.id.btn_linea);
        btn_linea.setOnClickListener( this);
        btn_circulos=(FloatingActionButton)findViewById(R.id.circulos);
        btn_circulos.setOnClickListener(this);
        btn_calculadora=(FloatingActionButton)findViewById(R.id.calculadora);
        btn_calculadora.setOnClickListener(this);
        cerrar=(Button)findViewById(R.id.btn_cerrar);
        cerrar.setOnClickListener(this);
        portada =(ImageView)findViewById(R.id.imgportada);

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
                Intent inte4 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(inte4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupNavigationDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //  textView = (TextView) findViewById(R.id.textView);
                        switch (menuItem.getItemId()) {


                            case R.id.btn_linea:
                                menuItem.setChecked(true);
                                Intent inte2 = new Intent(MainActivity.this, Linea.class);
                                startActivity(inte2);
                                return true;
                            case R.id.circulos:
                                menuItem.setChecked(true);
                                Intent inte22 = new Intent(MainActivity.this, Circulos.class);
                                startActivity(inte22);
                                return true;
                            case R.id.calculadora:
                                menuItem.setChecked(true);
                                Intent inte23 = new Intent(MainActivity.this, Calculadora.class);
                                startActivity(inte23);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_info:
                                menuItem.setChecked(true);
                                Intent inte1 = new Intent(MainActivity.this, Info_sag.class);
                                startActivity(inte1);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_como:
                                menuItem.setChecked(true);
                                Intent inte19 = new Intent(MainActivity.this, Info_app.class);
                                startActivity(inte19);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;



                            case R.id.about:
                                menuItem.setChecked(true);
                                Intent inte4 = new Intent(MainActivity.this, AboutActivity.class);
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
            case R.id.btn_start:
                submenu.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_como:
                Intent inte22 = new Intent(MainActivity.this, Info_app.class);
                startActivity(inte22);
                break;
            case R.id.btn_info:
                Intent inte25 = new Intent(MainActivity.this, Info_sag.class);
                startActivity(inte25);
                break;
            case R.id.btn_linea:
                Intent inte2 = new Intent(MainActivity.this, Linea.class);
                startActivity(inte2);
                break;
            case R.id.circulos:
                Intent inte24 = new Intent(MainActivity.this, Circulos.class);
                startActivity(inte24);
                break;
            case R.id.calculadora:
                Intent inte23 = new Intent(MainActivity.this, Calculadora.class);
                startActivity(inte23);

                drawerLayout.closeDrawer(GravityCompat.START);
               break;
            case R.id.btn_cerrar:
                submenu.setVisibility(View.INVISIBLE);
                break;
        }


    }

}

