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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jordi on 18/07/2016.
 */
public class Calculadora extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton ok;
    EditText distancia;
    TextView resul;
    RadioButton xc;
    RadioButton enduro;
    RadioButton dh;
    RadioButton am;
    RadioGroup moda;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;;

    public int mod = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);
        ok = (FloatingActionButton) findViewById(R.id.btnok);
        ok.setOnClickListener(this);
        distancia = (EditText) findViewById(R.id.distancia);
        resul = (TextView) findViewById(R.id.resultado);
        xc = (RadioButton) findViewById(R.id.XC);
        xc.setOnClickListener(this);
        enduro = (RadioButton) findViewById(R.id.Enduro);
        enduro.setOnClickListener(this);
        dh = (RadioButton) findViewById(R.id.descenso);
        dh.setOnClickListener(this);
        am = (RadioButton) findViewById(R.id.am);
        am.setOnClickListener(this);
        moda = (RadioGroup) findViewById(R.id.modal);
        moda.setOnClickListener(this);

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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();


        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.XC:
                if (checked) {
                    mod = 10;
                }
                break;
            case R.id.Enduro:
                if (checked) {
                    mod = 20;
                }
                break;
            case R.id.descenso:

                if (checked) {

                    mod = 30;
                }
                break;
            case R.id.am:

                if (checked) {

                    mod = 15;
                }
                break;
        }
        }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.XC:

                mod = 10;

                break;
            case R.id.Enduro:

                mod = 20;

                break;
            case R.id.descenso:



                mod = 28;

                break;
            case R.id.am:

                mod = 15;

                break;
            case R.id.btnok:

                if(distancia.getText()==null || distancia.getText().length()==0 ) {

                    Toast toast23 = Toast.makeText(getApplicationContext(), "Introduce distancia", Toast.LENGTH_SHORT);
                    toast23.show();

            }else{
              int d=0;
                    d = Integer.parseInt(distancia.getText().toString());
                    int result = d-( (d * mod) / 100);
                    String res = Integer.toString(result);
                    resul.setText(res+"mm");}
            break;
        }
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
                Intent inte4 = new Intent(Calculadora.this, AboutActivity.class);
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
                                Intent inte2 = new Intent(Calculadora.this, Linea.class);
                                startActivity(inte2);
                                return true;
                            case R.id.circulos:
                                menuItem.setChecked(true);
                                Intent inte22 = new Intent(Calculadora.this, Circulos.class);
                                startActivity(inte22);
                                return true;
                            case R.id.calculadora:
                                menuItem.setChecked(true);
                                Intent inte23 = new Intent(Calculadora.this, Calculadora.class);
                                startActivity(inte23);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_info:
                                menuItem.setChecked(true);
                                Intent inte1 = new Intent (Calculadora.this, Info_sag.class);
                                startActivity(inte1);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_como:
                                menuItem.setChecked(true);
                                Intent inte19 = new Intent(Calculadora.this, Info_app.class);
                                startActivity(inte19);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;




                            case R.id.about:
                                menuItem.setChecked(true);
                                Intent inte4 = new Intent(Calculadora.this, AboutActivity.class);
                                startActivity(inte4);
                                return true;




                        }
                        return true;
                    }
                });
    }

}