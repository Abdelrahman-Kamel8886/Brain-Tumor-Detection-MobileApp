package com.abdo.braintumordetection.ui.dr.patientdetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentPatientinfoBinding;
import com.abdo.braintumordetection.utils.SharedModel;


public class PatientinfoFragment extends Fragment {


    FragmentPatientinfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_patientinfo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPatientinfoBinding.bind(view);
        binding.paPhone.setText(SharedModel.getPhone());
        binding.paAge.setText(SharedModel.getAge());
        binding.paDate.setText(SharedModel.getDate());
        binding.paEmail.setText(SharedModel.getEmail());
        binding.description.setText(SharedModel.getDes());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}