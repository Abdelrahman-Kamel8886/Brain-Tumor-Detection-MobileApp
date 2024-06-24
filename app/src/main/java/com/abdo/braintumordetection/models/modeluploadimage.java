package com.abdo.braintumordetection.models;

import android.net.Uri;

public class modeluploadimage {

    public Uri uri;
    public String filename;



    public modeluploadimage(Uri uri, String filename) {
        this.uri = uri;
        this.filename = filename;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
