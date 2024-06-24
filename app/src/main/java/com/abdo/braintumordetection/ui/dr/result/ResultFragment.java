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
import com.abdo.braintumordetection.adapters.RecyclerImagesAdapter;
import com.abdo.braintumordetection.databinding.FragmentResultBinding;

import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;


public class ResultFragment extends Fragment {

    FragmentResultBinding binding;
    ShowResultViewModel viewModel;
    RecyclerImagesAdapter adapter = new RecyclerImagesAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentResultBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ShowResultViewModel.class);

        getData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData(){

        viewModel.getData();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                if(SharedModel.getResult_upload().equals("notumor")){
                    binding.paMa.setText("No");
                    binding.paType.setText("NoTumor");
                    binding.paS.setText("-");
                    binding.mriTxt.setText("Patient Mri Images");
                    show(SharedModel.getMri_Images());
                }
                else{
                    binding.paMa.setText("Yes");
                    binding.paType.setText(""+SharedModel.getResult_upload());
                    binding.paS.setText("|||");
                    show(strings);
                }

            }
        });
    }
    private void show(ArrayList<String> list) {
        adapter.setList(list);
        binding.recyclerImages.setAdapter(adapter);
    }
}