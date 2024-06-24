package com.abdo.braintumordetection.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import com.abdo.braintumordetection.local.MyRoomDatabase;
import com.abdo.braintumordetection.models.ModelAuthCache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SharedModel {

    public static boolean cache =false;
    //patients
    private static String name;
    private static String phone;
    private static String email;
    private static String age;
    private static String des;
    private static String message;

    private static String Api ;

    public static String getApi() {

        return Api;
    }

    public static void setApi(String api) {
        Api = api;
    }

    private static String result_upload;

    private static ArrayList<String> Mri_Images;
    private static ArrayList<Uri> Uri_Array;

    public static ArrayList<Uri> getUri_Array() {
        return Uri_Array;
    }

    public static void setUri_Array(ArrayList<Uri> uri_Array) {
        Uri_Array = uri_Array;
    }

    public static ArrayList<String> getMri_Images() {
        return Mri_Images;
    }

    public static void setMri_Images(ArrayList<String> mri_Images) {
        Mri_Images = mri_Images;
    }

    private static ArrayList<String> links_upload;

    public static String getResult_upload() {
        return result_upload;
    }

    public static void setResult_upload(String result_upload) {
        SharedModel.result_upload = result_upload;
    }

    public static ArrayList<String> getLinks_upload() {
        return links_upload;
    }

    public static void setLinks_upload(ArrayList<String> links_upload) {
        SharedModel.links_upload = links_upload;
    }

    private static String date;


    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        SharedModel.date = date;
    }

    //dr
    private static String username;
    private static String drmail;
    private static String id;
    private static String chat;
    private static String chattitle;
    private static String chatimg;
    private static String drphone;
    private static String drbirth;
    private static String drimage;

    public static String getChatimg() {
        return chatimg;
    }

    public static void setChatimg(String chatimg) {
        SharedModel.chatimg = chatimg;
    }

    public static String getDrbirth() {
        return drbirth;
    }

    public static void setDrbirth(String drbirth) {
        SharedModel.drbirth = drbirth;
    }

    public static String getDrimage() {
        return drimage;
    }

    public static void setDrimage(String drimage) {
        SharedModel.drimage = drimage;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        SharedModel.age = age;
    }

    public static String getDes() {
        return des;
    }

    public static void setDes(String des) {
        SharedModel.des = des;
    }

    public static String getDrmail() {
        return drmail;
    }

    public static void setDrmail(String drmail) {
        SharedModel.drmail = drmail;
    }

    public static String getDrphone() {
        return drphone;
    }

    public static void setDrphone(String drphone) {
        SharedModel.drphone = drphone;
    }

    public static String getChattitle() {
        return chattitle;
    }

    public static void setChattitle(String chattitle) {
        SharedModel.chattitle = chattitle;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        SharedModel.message = message;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        SharedModel.name = name;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        SharedModel.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SharedModel.email = email;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SharedModel.username = username;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        SharedModel.id = id;
    }


    public static String getChat() { return chat; }

    public static void setChat(String chat) { SharedModel.chat = chat; }


    public static void cache(List<ModelAuthCache> list){

        MyRoomDatabase.getInstance().getDao().insert(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public static void delete(ModelAuthCache model){

        MyRoomDatabase.getInstance().getDao().delete(model).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }



}
