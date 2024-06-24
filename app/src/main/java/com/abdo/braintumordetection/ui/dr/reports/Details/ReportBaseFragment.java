package com.abdo.braintumordetection.ui.dr.reports.Details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.PagerAdabterForHome;
import com.abdo.braintumordetection.databinding.FragmentPatientDetailsBinding;
import com.abdo.braintumordetection.databinding.FragmentReportBaseBinding;
import com.abdo.braintumordetection.models.PagerModelClass;
import com.abdo.braintumordetection.ui.dr.patientdetails.MriImagesFragment;
import com.abdo.braintumordetection.ui.dr.patientdetails.PatientDetailsViewModel;
import com.abdo.braintumordetection.ui.dr.patientdetails.PatientinfoFragment;
import com.abdo.braintumordetection.ui.dr.result.ResultFragment;
import com.abdo.braintumordetection.ui.dr.result.ShowResultFragment;
import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;
import java.util.Calendar;


public class ReportBaseFragment extends Fragment {

    FragmentReportBaseBinding binding;

    ArrayList<PagerModelClass> model = new ArrayList<>();
    PagerAdabterForHome adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding = FragmentReportBaseBinding.bind(view);

        adapter = new PagerAdabterForHome(getChildFragmentManager());

        model.clear();

        model.add(new PagerModelClass(new PatientinfoFragment(), "Info"));
        model.add(new PagerModelClass(new MriImagesFragment(), "Scans"));
        model.add(new PagerModelClass(new ResultFragment(), "Result"));
        //model.add(new PagerModelClass(new ReportFragment(), "Report"));


        adapter.setData(model);
        binding.Pager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.Pager);
        binding.paTxt.setText(SharedModel.getName() + "");

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });


        binding.bar.setVisibility(View.GONE);


        //viewModel = new ViewModelProvider(this).get(PatientDetailsViewModel.class);




    }

}