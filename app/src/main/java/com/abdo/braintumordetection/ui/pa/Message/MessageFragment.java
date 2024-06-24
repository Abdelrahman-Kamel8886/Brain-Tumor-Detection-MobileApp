package com.abdo.braintumordetection.ui.pa.Message;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.databinding.FragmentMessageBinding;
import com.abdo.braintumordetection.utils.SharedModel;


public class MessageFragment extends Fragment {


    FragmentMessageBinding binding;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMessageBinding.bind(view);
        binding.message.setText(SharedModel.getMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}