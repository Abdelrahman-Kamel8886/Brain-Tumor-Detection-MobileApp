package com.abdo.braintumordetection.ui.dr.patientdetails;

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
import com.abdo.braintumordetection.databinding.FragmentMriImagesBinding;
import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;


public class MriImagesFragment extends Fragment {

    PatientDetailsViewModel viewModel;
    RecyclerImagesAdapter adapter = new RecyclerImagesAdapter();
    FragmentMriImagesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mri_images, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMriImagesBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(PatientDetailsViewModel.class);
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
                SharedModel.setMri_Images(strings);
                show(strings);

            }
        });
    }
    private void show(ArrayList<String> list) {
        adapter.setList(list);
        binding.recyclerImages.setAdapter(adapter);
    }
}