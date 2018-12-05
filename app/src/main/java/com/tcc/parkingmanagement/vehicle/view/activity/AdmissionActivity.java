package com.tcc.parkingmanagement.vehicle.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parkingwang.keyboard.KeyboardInputController;
import com.parkingwang.keyboard.PopupKeyboard;
import com.parkingwang.keyboard.view.InputView;
import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.base.baseView.BaseActivity;
import com.tcc.parkingmanagement.util.DeepAssetUtil;
import com.tcc.parkingmanagement.util.DeepCarUtil;
import com.tcc.parkingmanagement.util.PermissionUtils;
import com.tcc.parkingmanagement.util.TimeUtil;
import com.tcc.parkingmanagement.vehicle.view.widget.camera.CameraPreview;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;


public class AdmissionActivity extends BaseActivity implements View.OnClickListener,CameraPreview.OnPickListener {

    public final static int    REQUEST_CODE           = 0X11;//请求码
    public final static int    RESULT_CODE            = 0X12;//结果码
    public final static int    PERMISSION_CODE_SECOND = 0x13;//权限请求码
    public final static String TAKE_TYPE              = "take_type";//拍摄类型标记
    public final static String IMAGE_PATH             = "image_path";//图片路径标记
    public static int      mType;//拍摄类型
    public static Activity mActivity;
    private boolean isToast = true;//是否弹吐司，为了保证for循环只弹一次

    private Bitmap        mCropBitmap;
    private CameraPreview mCameraPreview;


    private boolean isLoadOpenCVSuccess = false;

    private static String filePath = null;

    public long handle;

    private Bitmap bmp;
    //备份 bitmap
    private Bitmap originBitmap = bmp;

    private InputView input_view;

    private PopupKeyboard mPopupKeyboard;

    private Button btn_switch;

    private ImageView im;

    @BindView(R.id.tv_time_y)
    TextView tv_time_y;

    @BindView(R.id.tv_time_h)
    TextView tv_time_h;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    bmp = (Bitmap) msg.obj;
                    if (bmp != null){
                        new PlateAsyncTask().execute();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*动态请求需要的权限*/
        boolean checkPermissionSecond = PermissionUtils.checkPermissionFirst(this, PERMISSION_CODE_SECOND,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
        if (checkPermissionSecond) {
            init();
        }
    }

    @OnClick({R.id.btn_rk})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_rk:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //初始化openCV

        if (!isLoadOpenCVSuccess && !OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_3_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    /**
     * 处理请求权限的响应
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 请求权限结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isPermissions = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                isPermissions = false;
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) { //用户选择了"不再询问"
                    if (isToast) {
                        Toast.makeText(this, "请手动打开该应用需要的权限", Toast.LENGTH_SHORT).show();
                        isToast = false;
                    }
                }
            }
        }
        isToast = true;
        if (isPermissions) {
            Log.d("onRequestPermission", "onRequestPermissionsResult: " + "允许所有权限");
            init();
        } else {
            Log.d("onRequestPermission", "onRequestPermissionsResult: " + "有权限不允许");
            finish();
        }
    }

    private void init() {
        setContentView(R.layout.activity_camera2);
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra(TAKE_TYPE, 0);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initView();
        initListener();
    }

    private void initView() {
        mCameraPreview = (CameraPreview) findViewById(R.id.camera_preview);

        input_view = (InputView) findViewById(R.id.input_view);

        btn_switch = (Button) findViewById(R.id.btn_switch);

        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
// 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(input_view, this);

// KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
        mPopupKeyboard.getController()
                .setDebugEnabled(true)
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(btn_switch) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);
                        if (isNewEnergyType) {
                            btn_switch.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        } else {
                            btn_switch.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mPopupKeyboard.isShown()){
                mPopupKeyboard.dismiss(this);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initListener() {
        mCameraPreview.setOnClickListener(this);
        mCameraPreview.setOnPickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        mCameraPreview.setEnabled(false);
        mCameraPreview.takePhoto(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(final byte[] data, Camera camera) {
                camera.stopPreview();
                //子线程处理图片，防止ANR
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                        Message msg = new Message();
                        msg.what = 100;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    /**
     * 设置裁剪布局
     */
    private void setCropLayout() {
        mCameraPreview.setVisibility(View.GONE);
    }

    /**
     * 设置拍照布局
     */
    private void setTakePhotoLayout() {
        mCameraPreview.setVisibility(View.VISIBLE);

        mCameraPreview.focus();
    }

    /**
     * 点击确认，返回图片路径
     */
    private void confirm() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCameraPreview != null) {
            mCameraPreview.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCameraPreview != null) {
            mCameraPreview.onStop();
        }
    }

    @Override
    public void onPickListener(Camera camera) {

        takePhoto();
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
                mCameraPreview.setCount(0);
                mCameraPreview.setEnabled(true);
                mCameraPreview.startPreview();
                return;
            }

            input_view.updateNumber(license);
            tv_time_y.setText(TimeUtil.getCurrentDay());
            tv_time_h.setText(TimeUtil.getCurrentHous());

        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
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
                            handle = DeepAssetUtil.initRecognizer(AdmissionActivity.this);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (filePath != null) {
                                bmp = BitmapFactory.decodeFile(filePath);
                                im.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        im.setImageBitmap(bmp);
                                    }
                                });
                                originBitmap = bmp;
                                if (bmp != null) {
                                    new PlateAsyncTask().execute();
                                }
                            }
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
