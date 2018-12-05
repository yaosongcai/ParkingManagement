package com.tcc.parkingmanagement.vehicle.persenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.tcc.parkingmanagement.database.ParkManagerTable;
import com.tcc.parkingmanagement.database.PlateInfoTable;
import com.tcc.parkingmanagement.database.SQLHelper;
import com.tcc.parkingmanagement.util.DeepAssetUtil;
import com.tcc.parkingmanagement.util.DeepCarUtil;
import com.tcc.parkingmanagement.util.TimeUtil;
import com.tcc.parkingmanagement.vehicle.contracts.WarehousingContracts;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by admin on 2018/8/21 19:07
 * 邮箱：yaosongcai@ujifu.com
 */

public class WarehousingPersenter implements WarehousingContracts.Persenter {

    private Context context;

    private WarehousingContracts.Views views;

    private String time;//入库时间

    private PlateInfoTable plateInfoTable;

    private ParkManagerTable parkManagerTable;

    private boolean isLoadOpenCVSuccess = false;
    private Bitmap bmp;
    public long handle;

    public WarehousingPersenter(Activity context, WarehousingContracts.Views views) {
        this.context = context;
        this.views = views;

        init();
        initButterknife(context);
        views.initView();
    }

    private void init(){
        plateInfoTable = new PlateInfoTable();
        parkManagerTable = SQLHelper.getInstance().getParkManagerTableDao().queryBuilder().unique();
        if (parkManagerTable == null){
            parkManagerTable = new ParkManagerTable();
        }
        plateInfoTable.setParkName("沙坪坝停车场");
        plateInfoTable.setParkManagerId("03dwasdwad30");
    }

    @Override
    public void initButterknife(Activity activity) {
        ButterKnife.bind(activity);
    }

    @Override
    public void initOpenCV() {
        //初始化openCV

        if (!isLoadOpenCVSuccess && !OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_3_0, context, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void verifyPlateNo(Bitmap bitmap) {
        this.bmp = bitmap;
        new PlateAsyncTask().execute();
    }

    @Override
    public void enterRK() {
        SQLHelper.getInstance().getPlateInfoTableDao().insert(plateInfoTable);
    }

    @SuppressLint("StaticFieldLeak")
    private class PlateAsyncTask extends AsyncTask<String, Integer, String> {
        private long startTime ;

        @Override
        protected String doInBackground(String... params) {
            startTime = System.currentTimeMillis();
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            Mat m = new Mat(width, height, CvType.CV_8UC4);
            Utils.bitmapToMat(bmp, m);
            if(width > 1000 || height > 1000){
                Size sz = new Size(600,800);
                Imgproc.resize(m,m,sz);
            }
            try {
                return  DeepCarUtil.SimpleRecognization(m.getNativeObjAddr(), handle);
            } catch (Exception e) {
                Log.d(TAG, "exception occured!");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String license) {
            super.onPostExecute(license);
            long endTime = System.currentTimeMillis();
            Log.i(TAG, "total time is : " + (endTime - startTime));

            if (license == null || license.isEmpty()){
                views.Identificationfailure();
                return;
            }

            String times = TimeUtil.getCurrentDay();
            String times1 = TimeUtil.getCurrentHous();
            time = times + times1;
            views.updateView(license,times,times1);
            plateInfoTable.setPlateNo(license);
            plateInfoTable.setWarehousingTime(time);

        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(context) {
        @SuppressLint("StaticFieldLeak")
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    //在加载openCV 成功后, 开始加载 hyperlpr so 文件
                    if (!isLoadOpenCVSuccess){
                        System.loadLibrary("hyperlpr");
                    }
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            handle = DeepAssetUtil.initRecognizer(context);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                        }
                    }.execute();

                    isLoadOpenCVSuccess = true;
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

}
