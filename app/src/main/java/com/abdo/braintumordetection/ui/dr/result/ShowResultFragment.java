package com.abdo.braintumordetection.ui.dr.result;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.RecyclerImages2Adapter;
import com.abdo.braintumordetection.adapters.RecyclerImagesAdapter;
import com.abdo.braintumordetection.databinding.FragmentShowResultBinding;
import com.abdo.braintumordetection.ui.dr.patientdetails.WriteReport.WriteReportFragment;
import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;


public class ShowResultFragment extends Fragment {


    FragmentShowResultBinding binding;
    RecyclerImagesAdapter adapter = new RecyclerImagesAdapter();
    RecyclerImages2Adapter adapter2 = new RecyclerImages2Adapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentShowResultBinding.bind(view);
        binding.paTxt.setText("");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        getData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData(){

        if(SharedModel.getResult_upload().equals("notumor")){
            binding.paMa.setText("No");
            binding.paType.setText("NoTumor");
            binding.paS.setText("-");
            binding.mriTxt.setText("Patient Mri Images");
            adapter2.setList(SharedModel.getUri_Array());
            binding.recyclerImages.setAdapter(adapter2);
        }
        else{
            binding.paMa.setText("Yes");
            binding.paType.setText(""+SharedModel.getResult_upload());
            binding.paS.setText("|||");
            show(SharedModel.getLinks_upload());
        };
    }
    private void show(ArrayList<String> list) {
        adapter.setList(list);
        binding.recyclerImages.setAdapter(adapter);

    }
}