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
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;


/**
 * Created by Jordi on 22/02/2016.
 */
public class Info_app extends AppCompatActivity implements View.OnClickListener {

    TextView numc;
    TextView numca;
    TextView numl;
    TextSwitcher swtxt;
    TextSwitcher swpag;
    // ViewFlipper flipe;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    FrameLayout linea;
    FrameLayout circulos;
    FrameLayout calculadora;
    FrameLayout principal;
    FloatingActionButton P1;
    FloatingActionButton P2;
    FloatingActionButton P3;
    FloatingActionButton L1;
    FloatingActionButton L2;
    FloatingActionButton L3;
    FloatingActionButton C1;
    FloatingActionButton C2;
    FloatingActionButton C3;
    FloatingActionButton O1;
    FloatingActionButton O2;
    FloatingActionButton O3;
    ViewFlipper Viewlinea;
    ViewFlipper  Viewcirculos;
    ViewFlipper  Viewcalculadora;
    TextView link;
private int contl, contc, contca = 1;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_app);
        linea=(FrameLayout)findViewById(R.id.Linea);
       principal=(FrameLayout)findViewById(R.id.Principal);
        circulos=(FrameLayout)findViewById(R.id.Circulos);
        calculadora=(FrameLayout)findViewById(R.id.Calculadora);
        L1=(FloatingActionButton)findViewById(R.id.l1);
        L1.setOnClickListener( this);
        L2=(FloatingActionButton)findViewById(R.id.l2);
        L2.setOnClickListener( this);
        L3=(FloatingActionButton)findViewById(R.id.l3);
        L3.setOnClickListener( this);
        C1=(FloatingActionButton)findViewById(R.id.c1);
        C1.setOnClickListener( this);
        C2=(FloatingActionButton)findViewById(R.id.c2);
        C2.setOnClickListener( this);
        C3=(FloatingActionButton)findViewById(R.id.c3);
        C3.setOnClickListener( this);
        O1=(FloatingActionButton)findViewById(R.id.o1);
        O1.setOnClickListener( this);
        O2=(FloatingActionButton)findViewById(R.id.o2);
        O2.setOnClickListener( this);
        O3=(FloatingActionButton)findViewById(R.id.o3);
        O3.setOnClickListener( this);
        P1=(FloatingActionButton)findViewById(R.id.p1);
        P1.setOnClickListener( this);
        P2=(FloatingActionButton)findViewById(R.id.p2);
        P2.setOnClickListener( this);
        P3=(FloatingActionButton)findViewById(R.id.p3);
        P3.setOnClickListener( this);
        Viewlinea=(ViewFlipper )findViewById(R.id.viewSwitcherLinea) ;
        Viewcirculos=(ViewFlipper )findViewById(R.id.viewSwitchercirculos) ;
        Viewcalculadora=(ViewFlipper )findViewById(R.id.viewSwitchercalculadora) ;
        link=(TextView)findViewById(R.id.textlink);
        link.setOnClickListener(this);
        numc = (TextView)findViewById(R.id.numc);
        numca = (TextView)findViewById(R.id.numca);
        numl = (TextView)findViewById(R.id.numl) ;

        numc.setText("1/12");
        numl.setText("1/18");
        numca.setText("1/2");
        contl=1;
        contc=1;
        contca = 1;




        // flipe = (ViewFlipper) findViewById(R.id.viewFlipper);
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
                Intent inte4 = new Intent(Info_app.this, AboutActivity.class);
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
                                Intent inte2 = new Intent(Info_app.this, Linea.class);
                                startActivity(inte2);
                                return true;
                            case R.id.circulos:
                                menuItem.setChecked(true);
                                Intent inte22 = new Intent(Info_app.this, Circulos.class);
                                startActivity(inte22);
                                return true;
                            case R.id.calculadora:
                                menuItem.setChecked(true);
                                Intent inte23 = new Intent(Info_app.this, Calculadora.class);
                                startActivity(inte23);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_info:
                                menuItem.setChecked(true);
                                Intent inte1 = new Intent (Info_app.this, Info_sag.class);
                                startActivity(inte1);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_como:
                                menuItem.setChecked(true);
                                Intent inte19 = new Intent(Info_app.this, Info_app.class);
                                startActivity(inte19);


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;




                            case R.id.about:
                                menuItem.setChecked(true);
                                Intent inte4 = new Intent(Info_app.this, AboutActivity.class);
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
            case R.id.p1:
                principal.setVisibility(View.INVISIBLE);
                linea.setVisibility(View.VISIBLE);
                circulos.setVisibility(View.INVISIBLE);
                calculadora.setVisibility(View.INVISIBLE);


                break;
            case R.id.p2:
                principal.setVisibility(View.INVISIBLE);
                linea.setVisibility(View.INVISIBLE);
                circulos.setVisibility(View.VISIBLE);
                calculadora.setVisibility(View.INVISIBLE);


                break;
            case R.id.p3:
                principal.setVisibility(View.INVISIBLE);
                linea.setVisibility(View.INVISIBLE);
                circulos.setVisibility(View.INVISIBLE);
                calculadora.setVisibility(View.VISIBLE);


                break;
            case R.id.o2:
                principal.setVisibility(View.VISIBLE);
                linea.setVisibility(View.INVISIBLE);
                circulos.setVisibility(View.INVISIBLE);
                calculadora.setVisibility(View.INVISIBLE);

                break;
            case R.id.l2:
                principal.setVisibility(View.VISIBLE);
                linea.setVisibility(View.INVISIBLE);
                circulos.setVisibility(View.INVISIBLE);
                calculadora.setVisibility(View.INVISIBLE);
                break;
            case R.id.c2:
                principal.setVisibility(View.VISIBLE);
                linea.setVisibility(View.INVISIBLE);
                circulos.setVisibility(View.INVISIBLE);
                calculadora.setVisibility(View.INVISIBLE);

                break;
            case R.id.l1:
                contl --;
                if(contl<1){
                    contl=1;
                    numl.setText("1/18");

                }else{

                    numl.setText(contl+"/18");
                    Viewlinea.showPrevious();
                }


                break;
            case R.id.l3:
                contl ++;
                if(contl>18){
                    numl.setText("18/18");
                    contl=18;
                }else{

                    numl.setText(contl+"/18");
                    Viewlinea.showNext();
                }

                break;
            case R.id.c1:
                contca --;

                if(contca<1){
                    contca=1;
                    numca.setText("1/2");

                }else{

                    numca.setText(contca+"/2");
                    Viewcalculadora.showPrevious();
                }
                break;
            case R.id.c3:
                contca ++;
                if(contca>2){
                    numca.setText("2/2");
                    contca = 2;
                }else{

                    numca.setText(contca+"/2");
                    Viewcalculadora.showNext();
                }

                break;
            case R.id.o1:
                contc --;
                if(contc<1){
                    contc=1;
                    numc.setText("1/12");

                }else{

                    numc.setText(contc+"/12");
                    Viewcirculos.showPrevious();
                }

                break;
            case R.id.o3:
                contc ++;
                if(contc>12){
                    numc.setText("12/12");
                    contc=12;
                }else{

                    numc.setText(contc+"/12");
                    Viewcirculos.showNext();
                }

                break;
            case R.id.textlink:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://drive.google.com/open?id=0B2m64bGBaEa_a0x4Wm1Mb2pPVzg"));
                startActivity(intent);

                break;
        }


    }
}
