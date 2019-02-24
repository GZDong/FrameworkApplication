package com.gzd.example.frameworkapplication.presenter;

import com.gzd.example.frameworkapplication.model.DataListener;
import com.gzd.example.frameworkapplication.model.ImageModel;
import com.gzd.example.frameworkapplication.view.ImageInterface;

import java.util.List;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class ImagePresenter extends BasePresenter<ImageInterface> {

    private ImageModel mImageModel = ImageModel.getInstance();

    public void loadImageToActivity(){
        mImageModel.getImageUrl(new DataListener<List<String>>() {
            @Override
            public void onFinish(List<String> result) {
                getView().loadImage(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
