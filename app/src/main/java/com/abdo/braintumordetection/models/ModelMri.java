package com.abdo.braintumordetection.models;

import android.graphics.Bitmap;
import android.net.Uri;


public class ModelMri {


    public int result;
    public Uri uri;

    public ModelMri(int result, Uri uri) {
        this.result = result;
        this.uri = uri;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
