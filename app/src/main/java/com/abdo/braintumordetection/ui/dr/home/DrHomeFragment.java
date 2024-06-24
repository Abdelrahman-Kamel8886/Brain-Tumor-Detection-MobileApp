package com.abdo.braintumordetection.ui.dr.home;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.HomeBinding;
import com.abdo.braintumordetection.models.ModelAuthCache;
import com.abdo.braintumordetection.models.RegModel;
import com.abdo.braintumordetection.ui.auth.Login.LoginFragment;
import com.abdo.braintumordetection.ui.dr.chat.main.BaseChatFragment;
import com.abdo.braintumordetection.ui.dr.patients.PatientsFragment;
import com.abdo.braintumordetection.ui.dr.reports.ReportsFragment;
import com.abdo.braintumordetection.ui.dr.scan.ScanFragment;
import com.abdo.braintumordetection.ui.dr.settings.SettingsFragment;
import com.abdo.braintumordetection.ui.pa.home.PatientHomeFragment;
import com.abdo.braintumordetection.utils.SharedModel;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;


public class DrHomeFragment extends Fragment {


    HomeBinding binding;

    DrHomeViewModel viewModel;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = HomeBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(DrHomeViewModel.class);
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void getData() {


        viewModel.getData();
        viewModel.model.observe(getViewLifecycleOwner(), new Observer<RegModel>() {
            @Override
            public void onChanged(RegModel regModel) {
                SharedModel.setUsername(regModel.getUsername());
                SharedModel.setDrphone(regModel.getPhone());
                SharedModel.setDrmail(regModel.getEmail());
                SharedModel.setDrbirth(regModel.getBirth());
                SharedModel.setDrimage(regModel.getImage());

                caches.add(new ModelAuthCache(regModel.getUsername(), SharedModel.getId(), regModel.getPhone() , regModel.getEmail()));
                SharedModel.cache(caches);
                onClicks();
            }
        });

    }

    private void onClicks() {

        binding.userTxt.setText(SharedModel.getUsername() + "");
        binding.user2Txt.setText("");

        if(SharedModel.getDrimage()!=""){
            Glide.with(getContext())
                    .load(SharedModel.getDrimage())
                    .into(binding.drImg);
        }



        binding.drImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new SettingsFragment(), "sn").addToBackStack("sn").commit();
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new PatientsFragment(), "pa").addToBackStack("pa").commit();
                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
                //requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new PatientHomeFragment(), "pa").addToBackStack("pa").commit();
            }
        });

        binding.scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new ScanFragment(), "scan").addToBackStack("scan").commit();
                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isConnected()) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new BaseChatFragment(), "chat").addToBackStack("chat").commit();
                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.patientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new ReportsFragment(), "rep").addToBackStack("rep").commit();
                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(requireContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {

            if (networkInfo.isConnected()) {
                return true;
            } else {
                return false;


            }
        }
        else{
                return false;
            }
        }

}
