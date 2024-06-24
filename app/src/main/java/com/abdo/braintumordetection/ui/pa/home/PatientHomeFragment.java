package com.abdo.braintumordetection.ui.pa.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentPatientHomeBinding;
import com.abdo.braintumordetection.ui.pa.predict.AddMriFragment;
import com.abdo.braintumordetection.utils.SharedModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PatientHomeFragment extends Fragment {

    FragmentPatientHomeBinding binding;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPatientHomeBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){

        binding.NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

    }
    private void check(){

        String name = binding.nameEdit.getText().toString().trim();
        String email = binding.emailEdit.getText().toString().trim();
        String age = binding.ageEdit.getText().toString().trim();
        String phone = binding.phoneEdit.getText().toString().trim();
        String des = binding.descriptionEdit.getText().toString().trim();


        if(name.isEmpty()){
            binding.nameEdit.setError("Required");
        }
        else if (email.isEmpty()){
            binding.emailEdit.setError("Required");
        }
        else if (phone.isEmpty()){
            binding.phoneEdit.setError("Required");
        }
        else if (age.isEmpty()){
            binding.ageEdit.setError("Required");
        }
        else if (des.isEmpty()){
            binding.descriptionEdit.setError("Required");
        }
        else{
            SharedModel.setName(name);
            SharedModel.setEmail(email);
            SharedModel.setPhone(phone);
            SharedModel.setAge(age);
            SharedModel.setDes(des);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            SharedModel.setDate(formattedDate);
            CheckData();
        }
    }

    private void CheckData(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame , new AddMriFragment(),"pa").addToBackStack("pa").commit();
    }
}