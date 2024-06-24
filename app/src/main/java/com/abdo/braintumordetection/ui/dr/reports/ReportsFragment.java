package com.abdo.braintumordetection.ui.dr.reports;

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
import com.abdo.braintumordetection.adapters.PatientsRecyclerAdapter;
import com.abdo.braintumordetection.databinding.FragmentPatientsBinding;
import com.abdo.braintumordetection.databinding.FragmentReportsBinding;
import com.abdo.braintumordetection.models.ModelPatient;
import com.abdo.braintumordetection.ui.dr.patientdetails.PatientDetailsFragment;
import com.abdo.braintumordetection.ui.dr.patients.PatientsViewModel;
import com.abdo.braintumordetection.ui.dr.reports.Details.ReportBaseFragment;
import com.abdo.braintumordetection.ui.pa.home.PatientHomeFragment;
import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;


public class ReportsFragment extends Fragment {

    FragmentReportsBinding binding;
    PatientsRecyclerAdapter adapter = new PatientsRecyclerAdapter();
    ReportsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentReportsBinding.bind(view);

        binding.bar.setVisibility(View.VISIBLE);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });



        viewModel = new ViewModelProvider(this).get(ReportsViewModel.class);
        getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.getData();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelPatient>>() {
            @Override
            public void onChanged(ArrayList<ModelPatient> modelPatients) {
                show(modelPatients);
            }
        });

    }
    private void show(ArrayList<ModelPatient> list){

        binding.bar.setVisibility(View.GONE);
        adapter.setList(list);
        binding.recyclerPatients.setAdapter(adapter);

        adapter.setOnItemClick(new PatientsRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(String phone, String name, String email, String date, String age, String des) {
                SharedModel.setName(name);
                SharedModel.setPhone(phone);
                SharedModel.setEmail(email);
                SharedModel.setDate(date);
                SharedModel.setAge(age);
                SharedModel.setDes(des);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new ReportBaseFragment(),"rb").addToBackStack("rb").commit();
            }
        });
    }
}