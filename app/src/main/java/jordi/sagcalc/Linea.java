package jordi.sagcalc;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Jordi on 23/09/2015.
 */
public class Linea extends Activity implements View.OnTouchListener, CameraBridgeViewBase.CvCameraViewListener2, View.OnClickListener {


    private FloatingActionButton fijar1;
    private FloatingActionButton fijar2;
    private FloatingActionButton calc;
    private FloatingActionButton disbtn;
    private Button calc2;
    private EditText di;
    private EditText di2;
    private boolean var = true;
    private boolean var2 = true;
    //puntos de la funcion calcular
    Point p2 = new Point();
    Point p1 = new Point();
    //linea horizontal
    Point p3 = new Point();
    Point p4 = new Point();
    //linea inicial , linea de referencia
    Point pi1 = new Point();
    Point pi2 = new Point();
    //linea de la primera seleccion
    Point py1 = new Point();
    Point px1 = new Point();
    //linea de la segunda seleccion
    Point px2 = new Point();
    Point py2 = new Point();

    private int o = 25;


    private int x;
    private int y;
    private int ancho;
    private int cont = 0;
    private int cont2 = 0;
    private int rojo = 0;
    private boolean l = true;
    int dis;
    //barra de tolerancia
    private SeekBar volumeControl = null;
    // switch de color o gris
    private Switch swt;
    //botones flotantes
    private FloatingActionButton reset;
    private FloatingActionButton mas;
    private FloatingActionButton menos;
    private Mat mRgba;
    private Mat mRgba1;
    private Mat mIntermediateMat;
    private Mat mGray;
    private boolean clr = true;
    private CameraBridgeViewBase mOpenCvCameraView;
    private Scalar color = new Scalar(0, 255, 0, 255);
    private Scalar naranja = new Scalar(255, 163, 0, 255);
    private Scalar rojolinea = new Scalar(255, 3, 16, 255);
    private Scalar color4 = new Scalar(0, 0, 0, 255);
    private Scalar hsv;
    private int colorA;
    private int colorB;
    private EditText hun ;
    private TextView campo1;
    private TextView campo2;



    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {


        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {

                    mOpenCvCameraView.enableView();
                    mOpenCvCameraView.setOnTouchListener(Linea.this);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.linea);


        volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                o = progressChanged; // este valor se puede modificar con la seekbar
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.linea);
        mOpenCvCameraView.setCvCameraViewListener(this);
        fijar1 = (FloatingActionButton) findViewById(R.id.btnfijar1);
        fijar1.setOnClickListener(this);
        fijar2 = (FloatingActionButton) findViewById(R.id.btnfijar2);
        fijar2.setOnClickListener(this);
        reset = (FloatingActionButton) findViewById(R.id.btnreset);
        reset.setOnClickListener(this);
        disbtn = (FloatingActionButton) findViewById(R.id.btndis);
        disbtn.setOnClickListener(this);
        mas = (FloatingActionButton) findViewById(R.id.btnmas);
        mas.setOnClickListener(this);
        menos = (FloatingActionButton) findViewById(R.id.btnmenos);
        menos.setOnClickListener(this);
        di = (EditText) findViewById(R.id.txtnum);
        hun=(EditText)findViewById(R.id.hundimiento);
        campo1=(TextView)findViewById(R.id.reco);
        campo1.setText("Ancho(mm): ");








    }

    public void onCameraViewStarted(int width, int height) {

        mOpenCvCameraView.enableView();
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
        p3 = new Point(mRgba.width() / 2 - 25, mRgba.height() / 2);
        p4 = new Point(mRgba.width() / 2 + 15, mRgba.height() / 2);


    }

    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {


        mRgba1 = inputFrame.rgba();
        mRgba1 = Imgproc.cvtColor(mRgba1, mRgba1, Imgproc.COLOR_RGB2HSV);
        mGray = inputFrame.gray();
        mRgba = inputFrame.rgba();

        x = mRgba.width() / 2 - 5;
        pi1 = new Point(x, mRgba.height() / 2 - mRgba.height() / 3);
        pi2 = new Point(x, mRgba.height() / 2 + mRgba.height() / 3);

        if (var) {
            calcular2();
            px1 = p1;
            py1 = p2;

        }else if (var2){
            calcular2();
            px2 = p1;
            py2 = p2;

        }
        Imgproc.line(mRgba, pi2, pi1, color4, 3);
        Imgproc.line(mRgba, p3, p4, color, 3);
        Imgproc.line(mRgba, px1, py1, naranja, 5);
        Imgproc.line(mRgba, px2, py2, rojolinea, 5);


        return mRgba;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {

            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {

            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        double[] colors2;
        double[] colors ;
        mRgba1 = Imgproc.cvtColor(mRgba1, mRgba1, Imgproc.COLOR_RGB2HSV); // en teoria esto lo cambia a HSV
        colors2 = mRgba.get(mRgba.height() / 2 + 1, mRgba.width() / 2 - 1);
        int color2 = (int) ((colors2[0] + colors2[1] + colors2[2]) / 3);
        colors = mRgba1.get(mRgba.height() / 2 + 1, mRgba1.width() / 2 - 1);
        int color = (int) ((colors[0] + colors[1] + colors[2]) / 3);
        cont = 0;
        color=(color2+color)/2;
        Toast toast = Toast.makeText(getApplicationContext(), "Hsv: (" + colors2[0] + " ---" + colors2[1] + " ---" + colors2[2] + "   "+color+")", Toast.LENGTH_SHORT);
        toast.show();

        return false;
    }


    @Override
    public void onClick(View v) {
        int punto1;
        int punto4;
        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnfijar1: // Pausa la medicion de pixels
                fijar1.setVisibility(View.INVISIBLE);
                fijar2.setVisibility(View.VISIBLE);
                var = false;
                var2= true;
                calcular2();
                px1 = p1;
                py1 = p2;


                break;
            case R.id.btnfijar2: // enciende el buscador de pixels
                fijar1.setVisibility(View.VISIBLE);
                fijar2.setVisibility(View.INVISIBLE);
                punto1 = (int) ((int) py1.y - px1.y);
                punto4 = (int) ((int) py2.y - px2.y);
                int u = 100 - (punto4 * 100 / punto1);
                Toast toast = Toast.makeText(getApplicationContext(), "Hundimiento: (" + u + "%)", Toast.LENGTH_SHORT);
                toast.show();
                hun.setText(""+u+"%");
                var2=false;

                break;

            case R.id.btnreset:
                var= true;
                var2 = false;
                px1 = new Point(0, 0);
                py1 = new Point(0, 0);
                px2 = new Point(0, 0);
                py2 = new Point(0, 0);
                p3 = new Point(mRgba.width() / 2 - 25, mRgba.height() / 2);
                p4 = new Point(mRgba.width() / 2 + 15, mRgba.height() / 2);
                hun.setText("");
                fijar1.setVisibility(View.VISIBLE);
                fijar2.setVisibility(View.INVISIBLE);
                campo1.setText("Ancho(mm): ");
                di.setText("");

                break;
            case R.id.btndis:
                int x1 = (int) p4.x;
                int x2 = (int) p3.x;
                int lendis = x1 - x2;
                int y1 = (int) p1.y;
                int y2 = (int) p2.y;
                int len = y2 - y1;
                if(di.length()<4 && di.length() >0) {
                    int n = Integer.parseInt(di.getText().toString());

                    int dis = (n * len) / lendis;
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Distancia: (" + dis + " mm)", Toast.LENGTH_SHORT);
                    toast2.show();
                    campo1.setText("Recorrido (mm): ");
                    di.setText("" + dis + " mm");
                }else {
                    Toast toast2 = Toast.makeText(getApplicationContext(), "El valor introducido es erróneo : reinicie )", Toast.LENGTH_SHORT);
                    toast2.show();

                }


                break;

            case R.id.btnmas:
                ancho = ancho + 2;
                p3 = new Point(mRgba.width() / 2 - 25 - ancho, mRgba.height() / 2);
                p4 = new Point(mRgba.width() / 2 + 15 + ancho, mRgba.height() / 2);
                break;
            case R.id.btnmenos:
                ancho = ancho - 2;
                p3 = new Point(mRgba.width() / 2 - 25 - ancho, mRgba.height() / 2);
                p4 = new Point(mRgba.width() / 2 + 15 + ancho, mRgba.height() / 2);

                break;

        }
    }


    private void calcular2() {  // calcula los puntos/pixels HSV
        rojo = 0;
        int rojo2 = 0;
        int y1 = (int) pi1.y;
        int y2 = (int) pi2.y;
        int len = y2 - y1;
        int a = 0;
        int color2=0;
        int color=0;
        double[] colores = new double[0];
        double[] colores2 = new double[0];
        if (clr) {

            // for (int i = 0; i < 10; i++) {


            colores2 = mRgba.get(mRgba.height() / 2 + 1, mRgba.width() / 2 - 1);
            color2 += (int) ((colores2[0] + colores2[1] + colores2[2]) / 3);
            colores = mRgba1.get(mRgba1.height() / 2 + 1, mRgba1.width() / 2 - 1);
            color += (int) ((colores[0] + colores[1] + colores[2]) / 3);
            //  }

            cont = 0;
            color=((color2)+(color))/2;

            for (a = 1; a < len / 2; a++) { // este for mira de mitad pantalla hacia abajo
                a++;
                colores = mRgba1.get(mRgba.height() / 2 - a, mRgba1.width() / 2 - 1);
                colores2 = mRgba.get(mRgba.height() / 2 + 1, mRgba.width() / 2 - 1);
                rojo = (int) ((colores[0] + colores[1] + colores[2]) / 3);
                rojo2 = (int)((colores2[0] + colores2[1] + colores2[2]) / 3);

                rojo=(rojo+rojo2)/2;

                if (rojo > color + o || rojo < color - o) {
                    cont++;
                    if (cont == 5) {
                        p1 = new Point(mRgba1.width() / 2 - 10, (mRgba1.height() / 2 - a)+10);
                        colorA = rojo;
                        a = len;
                    }
                }


            }
            cont = 0;
            for (a = 0; a < len / 2; a++) { // este for mira de mitad pantalla hacia arriba
                a++;
                colores = mRgba1.get((mRgba.height() / 2 - a)+5, mRgba1.width() / 2 - 1);
                rojo = (int) ((colores[0] + colores[1] + colores[2]) / 3);
                rojo2 = (int)((colores2[0] + colores2[1] + colores2[2]) / 3);
                rojo=(rojo+rojo2)/2;

                if (rojo > color + o || rojo < color - o) {
                    cont++;
                    if (cont == 5) {
                        p2 = new Point(mRgba1.width() / 2 - 10, (mRgba1.height() / 2 + a)-10);
                        colorB = rojo;

                        a = len;
                    }
                }


            }


        }
    }
}

