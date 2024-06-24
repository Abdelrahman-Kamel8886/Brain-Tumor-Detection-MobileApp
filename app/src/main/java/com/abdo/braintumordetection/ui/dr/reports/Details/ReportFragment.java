package com.abdo.braintumordetection.ui.dr.reports.Details;

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
import com.abdo.braintumordetection.adapters.ReportsRecyclerAdapter;
import com.abdo.braintumordetection.databinding.FragmentReportBinding;
import com.abdo.braintumordetection.databinding.FragmentReportsBinding;
import com.abdo.braintumordetection.models.TitleModel;

import java.util.ArrayList;


public class ReportFragment extends Fragment {



    FragmentReportBinding binding;
    ReportsRecyclerAdapter adapter = new ReportsRecyclerAdapter();
    ReportDetailsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentReportBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ReportDetailsViewModel.class);

        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void getData(){

        viewModel.getData();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<TitleModel>>() {
            @Override
            public void onChanged(ArrayList<TitleModel> titleModels) {
                adapter.setList(titleModels);
                binding.recyclerTitles.setAdapter(adapter);
            }
        });

    }
}