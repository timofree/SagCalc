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
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.HoughCircles;
import static org.opencv.imgproc.Imgproc.circle;

/**
 * Created by Jordi on 23/09/2015.
 */
public class Circulos extends Activity implements View.OnTouchListener, CameraBridgeViewBase.CvCameraViewListener2, View.OnClickListener {


    private FloatingActionButton fijar1;
    private FloatingActionButton fijar2;
    private FloatingActionButton calc;
    private FloatingActionButton disbtn;
    private Button calc2;
    private EditText di;
    private EditText di2;
    private boolean var = true;
    private boolean var2 = true;

    private int o = 0;


    private int x;
    private int y;
    private int ancho;
    private int cont = 0;
    private int cont2 = 0;
    private int rojo = 0;
    private boolean l = true;
    int dis;
    private   double dis1=0;
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
    private Scalar color = new Scalar(255, 0, 0, 255);
    private Scalar color2 = new Scalar(255, 255, 255, 255);
    private Scalar color3 = new Scalar(0, 0, 255, 255);
    private Scalar color4 = new Scalar(0, 0, 0, 255);
    private Scalar hsv;
    private int colorA;
    private int colorB;
    private boolean d11= true;
    private boolean d22= true;
    private  Point[] points1 = new Point[2];
    private Point[] points2 = new Point[2];
    private      int[] radi1 = new int[2];

    private      int[] radi2 = new int[2];;
    private double dis2=0;
    private TextView hun ;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {


        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {

                    mOpenCvCameraView.enableView();
                    mOpenCvCameraView.setOnTouchListener(Circulos.this);
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
        setContentView(R.layout.circulos);


        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.linea);
        mOpenCvCameraView.setCvCameraViewListener(this);
        fijar1 = (FloatingActionButton) findViewById(R.id.btnfijar1);
        fijar1.setOnClickListener(this);
        fijar2 = (FloatingActionButton) findViewById(R.id.btnfijar2);
        fijar2.setOnClickListener(this);
        reset = (FloatingActionButton) findViewById(R.id.btnreset);
        reset.setOnClickListener(this);

        mas = (FloatingActionButton) findViewById(R.id.btnmas);



        di = (EditText) findViewById(R.id.txtnum);
        hun = (TextView)findViewById(R.id.texthun);





    }

    public void onCameraViewStarted(int width, int height) {

        mOpenCvCameraView.enableView();
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);


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

        circulos();
        if(!d11){
            for(x=0;x<2;x++){
                circle(mRgba, points1[x], 3, new Scalar(0, 255, 0), -1, 8, 0);
                // draw the circle outline
                circle(mRgba,  points1[x], radi1[x], color2, 3, 8, 0);

            }
        }
        if(!d22){
            for(x=0;x<2;x++){
                circle(mRgba, points2[x], 3, new Scalar(0, 255, 0), -1, 8, 0);
                // draw the circle outline
                circle(mRgba,  points2[x], radi1[x], color, 3, 8, 0);

            }
        }

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


        return false;
    }


    @Override
    public void onClick(View v) {
        int punto1;
        int punto4;
        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnfijar1:
                d11=false;

                dis1 = Math.sqrt(Math.pow(points1[1].x -points1[0].x,2) - Math.pow(points1[1].y - points1[0].y,2));


                break;
            case R.id.btnfijar2:
                d22=false;

                dis2 = Math.sqrt(Math.pow(points2[1].x -points2[0].x,2) - Math.pow(points2[1].y - points2[0].y,2));
                double res;
                res = 100 - (dis2 * 100 / dis1);
                Toast toast = Toast.makeText(getApplicationContext(), "Hundimiento: (" + res + "%)", Toast.LENGTH_SHORT);
                toast.show();
                if(res!=0){
                hun.setText(""+String.format("%1.2f",res)+"%");
                }else{
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Medicion incorrecta", Toast.LENGTH_SHORT);
                    toast.show();

                }
                break;

            case R.id.btnreset:
                    d11=true;
                   d22=true;
                hun.setText("      ");

                break;
            case R.id.btndis:


                break;

            case R.id.btnmas:

                break;
            case R.id.btnmenos:


                break;

        }
    }

    private void circulos() {
        Imgproc.GaussianBlur(mGray, mGray, new Size(9, 9), 2, 2);
        Mat circles = new Mat();
        int distance = 50;
        int minRadius = 20;
        int maxRadius = 50;
        Point[] points = new Point[105];
        HoughCircles(mGray, circles, Imgproc.CV_HOUGH_GRADIENT, 1, distance, 120, 10, minRadius, maxRadius);

        for (int x = 0; x < circles.cols(); x++)
        {
            double vCircle[]=circles.get(0,x);

            Point center=new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
            int radius = (int)Math.round(vCircle[2]);
            // draw the circle centerf
            if(x<2) {
                circle(mRgba, center, 3, new Scalar(0, 255, 0), -1, 8, 0);
                // draw the circle outline
                circle(mRgba, center, radius, new Scalar(0, 0, 255), 3, 8, 0);

                if(d11==true){
                    points1[x]=center;
                    radi1[x] = radius;
                }
                if(d22==true){
                    points2[x]=center;
                    radi2[x] = radius;
                }


            }else{
                x=circles.cols();
            }

        }


        }

}

