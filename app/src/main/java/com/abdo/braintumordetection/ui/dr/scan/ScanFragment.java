package com.abdo.braintumordetection.ui.dr.scan;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.RecyclerImages2Adapter;
import com.abdo.braintumordetection.databinding.FragmentScanBinding;
import com.abdo.braintumordetection.network.RetroConnection;
import com.abdo.braintumordetection.ui.dr.result.ShowResultFragment;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;


import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanFragment extends Fragment {

    FragmentScanBinding binding;

    ArrayList<Uri> mArrayUri = new ArrayList<>();
    RecyclerImages2Adapter adapter = new RecyclerImages2Adapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentScanBinding.bind(view);
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArrayUri.clear();
                openfile();
            }
        });

        binding.nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bar.setVisibility(View.VISIBLE);
                Predict();

            }
        });
    }

    private void openfile(){

        Intent intent = new Intent();

        intent.setType("image/*");

        // allowing multiple image to be selected
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pictures"),100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == 100 && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                }
                Show();


            }
            else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
                Show();

            }
        }
        else {
            // show this if no image is selected
            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void Predict(){

        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[mArrayUri.size()];

        for (int index = 0; index < mArrayUri.size(); index++) {
            Log.d("TAG", "requestUploadSurvey: survey image " + index + "  " + SharedModel.getPathFromUri(getContext() , mArrayUri.get(index)));

            File file = new File(SharedModel.getPathFromUri(getContext(),mArrayUri.get(index)));

            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file);

            surveyImagesParts[index] = MultipartBody.Part.createFormData("files", file.getName(), surveyBody);
        }

        RetroConnection.getServices().getmulti(surveyImagesParts).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                SharedModel.setResult_upload(response.body());
                if (SharedModel.getResult_upload().equals("notumor")){
                    fin();
                }
                else{
                    segmulti();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                binding.bar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void segmulti(){
        RetroConnection.getServices().segmulti().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                SharedModel.setLinks_upload(response.body());
                fin();

            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                binding.bar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void Show(){
        binding.txt2.setVisibility(View.GONE);
        binding.nxtBtn.setVisibility(View.VISIBLE);
        binding.uploadBtn.setVisibility(View.GONE);
        binding.recyclerMri.setVisibility(View.VISIBLE);
        SharedModel.setUri_Array(mArrayUri);
        adapter.setList(mArrayUri);
        binding.recyclerMri.setAdapter(adapter);
    }
    private void fin(){
        binding.bar.setVisibility(View.GONE);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new ShowResultFragment() , "v")
                .addToBackStack("v").commit();
    }


}