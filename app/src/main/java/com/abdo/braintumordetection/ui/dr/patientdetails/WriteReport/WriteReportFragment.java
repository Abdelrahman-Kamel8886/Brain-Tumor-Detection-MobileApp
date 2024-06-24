package com.abdo.braintumordetection.ui.dr.patientdetails.WriteReport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.TitlesRecyclerAdapter;
import com.abdo.braintumordetection.databinding.FragmentWriteReportBinding;
import com.abdo.braintumordetection.models.TitleModel;
import com.abdo.braintumordetection.ui.dr.home.DrHomeFragment;

import java.util.ArrayList;


public class WriteReportFragment extends Fragment {

    FragmentWriteReportBinding binding;
    ArrayList<TitleModel> titleModels = new ArrayList<>();
    TitlesRecyclerAdapter adapter = new TitlesRecyclerAdapter();

    WriteReportViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWriteReportBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(WriteReportViewModel.class);
        onClick();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClick(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bar.setVisibility(View.VISIBLE);
                SendData();
            }
        });
    }

    private void Validation(){

        String title = binding.titleEdit.getText().toString().trim();
        String des = binding.descriptionEdit.getText().toString().trim();


        if (title.isEmpty()){
            binding.titleEdit.setError("Required");
        }

        else if (des.isEmpty()){
            binding.descriptionEdit.setError("Required");
        }
        else {
            titleModels.add(new TitleModel(title,des));
            adapter.setList(titleModels);
            binding.recyclerTitles.setAdapter(adapter);
            binding.titleEdit.setText("");
            binding.descriptionEdit.setText("");
            binding.confirmBtn.setVisibility(View.VISIBLE);
        }

    }


    private void SendData(){
        viewModel.SendData();
        viewModel.success.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                binding.bar.setVisibility(View.GONE);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DrHomeFragment()).commit();
            }
        });

    }




}