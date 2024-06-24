package com.abdo.braintumordetection.ui.auth.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentLoginBinding;
import com.abdo.braintumordetection.ui.auth.Reg.RegFragment;
import com.abdo.braintumordetection.ui.dr.home.DrHomeFragment;
import com.abdo.braintumordetection.ui.pa.home.PatientHomeFragment;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    Button Login;
    TextView Reg;
    EditText Email , Password;
    ProgressBar progressBar;
    BottomSheetDialog bottomSheetDialog;

    LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void showdialog(){
        bottomSheetDialog = new BottomSheetDialog(getContext() , R.style.BottomSheetTheme);
        bottomSheetDialog.setContentView(R.layout.login);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        Login = bottomSheetDialog.findViewById(R.id.login_btn);
        progressBar = bottomSheetDialog.findViewById(R.id.bar_login);
        Email = bottomSheetDialog.findViewById(R.id.email_edit);
        Password = bottomSheetDialog.findViewById(R.id.pass_edit);
        Reg = bottomSheetDialog.findViewById(R.id.reg_btn);


        onClicks();
    }

    private void onClicks() {




        progressBar.setVisibility(View.GONE);




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validation();
            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new RegFragment(),"reg").addToBackStack("reg").commit();
            }
        });


    }

    private void Validation() {


        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();


        if (email.isEmpty()) {
            Email.setError("required");
        } else if (password.isEmpty()) {
            Password.setError("required");
        }


        else{
            progressBar.setVisibility(View.VISIBLE);
            login(email, password);

            }
        }
    private void login (String email ,String password){
        viewModel.login(email,password);

        viewModel.loged.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bottomSheetDialog.dismiss();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new DrHomeFragment()).commit();
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.notloged.observe(getViewLifecycleOwner(), new Observer<Exception>() {
            @Override
            public void onChanged(Exception e) {
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}