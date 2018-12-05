package com.tcc.parkingmanagement.vehicle.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.parkingwang.keyboard.KeyboardInputController;
import com.parkingwang.keyboard.PopupKeyboard;
import com.parkingwang.keyboard.view.InputView;
import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.base.baseView.BaseActivity;
import com.tcc.parkingmanagement.util.ScreenUtils;
import com.tcc.parkingmanagement.vehicle.contracts.WarehousingContracts;
import com.tcc.parkingmanagement.vehicle.persenter.WarehousingPersenter;
import com.tcc.parkingmanagement.vehicle.view.widget.camera.CameraPreview;

import butterknife.BindView;

/**
 * 车辆入库
 */
public class WarehousingActivity extends BaseActivity implements WarehousingContracts.Views,CameraPreview.OnPickListener {

    private WarehousingContracts.Persenter mPersenter;

    @BindView(R.id.camera_preview)
    CameraPreview mCameraPreview;//相机
    @BindView(R.id.input_view)
    InputView input_view;//车牌输入框
    @BindView(R.id.tv_time_y)
    TextView tv_time_y;//入库时间 年月日
    @BindView(R.id.tv_time_h)
    TextView tv_time_h;//入库时间 时分秒
    @BindView(R.id.btn_switch)
    Button btn_switch;//新能源按钮
    private PopupKeyboard mPopupKeyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehousing);

        mPersenter = new WarehousingPersenter(this,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPersenter.initOpenCV();
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            findViewById(R.id.view_status).getLayoutParams().height = ScreenUtils.getStatusHeight(this);
        mCameraPreview.setOnPickListener(this);
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
    public void updateView(String plateNo, String time_y, String time_h) {

        input_view.updateNumber(plateNo);
        tv_time_y.setText(time_y);
        tv_time_h.setText(time_h);
    }

    @Override
    public void Identificationfailure() {

        mCameraPreview.setCount(0);
        mCameraPreview.setEnabled(true);
        mCameraPreview.startPreview();
    }

    @Override
    public void onPickListener(Camera camera) {

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
                        mPersenter.verifyPlateNo(bitmap);

                    }
                }).start();
            }
        });
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
}
