package com.abdo.braintumordetection.ui.auth.Reg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentRegBinding;
import com.abdo.braintumordetection.ui.auth.Login.LoginFragment;
import com.abdo.braintumordetection.ui.dr.home.DrHomeFragment;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;

import java.io.IOException;
import java.util.Calendar;


public class RegFragment extends Fragment {

    FragmentRegBinding binding;
    DatePickerDialog.OnDateSetListener setListener;
    RegViewModel regViewModel;
    Uri uri;

    int flag =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRegBinding.bind(view);
        regViewModel =new ViewModelProvider(this).get(RegViewModel.class);
        on_clicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }


    private void on_clicks(){

        Calendar calendar =Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.drregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Validation();


            }
        });

        binding.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openfile();
            }
        });

        binding.birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String Date=i+"-"+i1+"-"+i2;
                binding.birthEdit.setText(Date);
            }
        };
    }
    private void Validation(){

        String username = binding.drregUsrEdit.getText().toString().trim();
        String email = binding.drregMailEdit.getText().toString().trim();
        String phone = binding.drregPhoneEdit.getText().toString().trim();
        String password = binding.drregPassEdit.getText().toString().trim();
        String birth = binding.birthEdit.getText().toString().trim();
        String image = binding.imageEdit.getText().toString().trim();


        if (username.isEmpty()){
            binding.drregUsrEdit.setError("required");
        }
        else if (email.isEmpty()){
            binding.drregMailEdit.setError("required");
        }
        else if (phone.isEmpty()){
            binding.drregPhoneEdit.setError("required");
        }
        else if (password.isEmpty()){
            binding.drregPassEdit.setError("required");
        }
        else if (birth.isEmpty()){
            Toast.makeText(getContext(), "Birthday Field is empty", Toast.LENGTH_SHORT).show();
        }
        else if (image.isEmpty()){
            Toast.makeText(getContext(), "Personal Image Field is empty", Toast.LENGTH_SHORT).show();
        }


        else{
            binding.bar.setVisibility(View.VISIBLE);
            sign(username,email,phone , password , birth);


        }
    }
    private void sign( String username , String email ,String phone, String password , String birth){
        regViewModel.Sign(uri,username,email,phone , password , birth);

        regViewModel.loged.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                flag =1;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                binding.bar.setVisibility(View.GONE);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame,new DrHomeFragment()).commit();

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (flag==0){
            //requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new LoginFragment(),"dr").commit();
        }
    }

    private void openfile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            uri = data.getData();
            binding.imageEdit.setText(SharedModel.getPathFromUri(getContext(),uri));


        }

    }
}