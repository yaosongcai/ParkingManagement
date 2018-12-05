package com.tcc.parkingmanagement.task.callback;


import com.tcc.parkingmanagement.task.ImageBean;

public interface ProgramImageCallback {

    void onProcessImageComplete(boolean isError, ImageBean imageBean);
}
