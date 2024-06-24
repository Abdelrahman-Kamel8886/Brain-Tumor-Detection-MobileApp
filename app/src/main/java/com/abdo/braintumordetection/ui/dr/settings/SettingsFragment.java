package com.abdo.braintumordetection.ui.dr.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentSettingsBinding;
import com.abdo.braintumordetection.models.ModelAuthCache;
import com.abdo.braintumordetection.ui.auth.Login.LoginFragment;
import com.abdo.braintumordetection.utils.SharedModel;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSettingsBinding.bind(view);
        caches.add(new ModelAuthCache(SharedModel.getUsername(), SharedModel.getId(), SharedModel.getPhone() , SharedModel.getDrmail()));
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){

        binding.mailTxt.setText(SharedModel.getDrmail()+"");
        binding.nameTxt.setText(SharedModel.getUsername()+"");
        binding.phoneTxt.setText(SharedModel.getDrphone()+"");
        binding.birthTxt.setText(SharedModel.getDrbirth());

        if(SharedModel.getDrimage()!=""){
            Glide.with(getContext())
                    .load(SharedModel.getDrimage())
                    .into(binding.img);
        }

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedModel.delete(caches.get(0));
                SharedModel.cache = false;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
            }
        });
    }
}