package com.abdo.braintumordetection.ui.dr.chat.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.UsersRecyclerAdapter;
import com.abdo.braintumordetection.databinding.FragmentBaseChatBinding;
import com.abdo.braintumordetection.models.ModelUser;
import com.abdo.braintumordetection.ui.dr.chat.endtoend.ChatFragment;
import com.abdo.braintumordetection.utils.SharedModel;

import java.util.ArrayList;


public class BaseChatFragment extends Fragment {


    FragmentBaseChatBinding binding;

    BaseChatViewModel viewModel;
    UsersRecyclerAdapter adapter = new UsersRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBaseChatBinding.bind(view);
        binding.bar.setVisibility(View.VISIBLE);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        viewModel =new  ViewModelProvider(this).get(BaseChatViewModel.class);
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData(){
        viewModel.getData();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelUser>>() {
            @Override
            public void onChanged(ArrayList<ModelUser> modelUsers) {
                show(modelUsers);
            }
        });

    }
    private void show(ArrayList<ModelUser> list){

        binding.bar.setVisibility(View.GONE);
        adapter.setList(list);
        binding.recyclerChat.setAdapter(adapter);
        binding.recyclerChat.smoothScrollToPosition(adapter.getItemCount());

        adapter.setOnItemClick(new UsersRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(String phone, String name , String img) {
                Long p1 = Long.parseLong(phone);
                Long p2 = Long.parseLong(SharedModel.getDrphone());
                Long p = p1+p2;

                SharedModel.setChat(p+"");
                SharedModel.setChattitle(name);

                SharedModel.setChatimg(img);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new ChatFragment() , "chat").addToBackStack("chat").commit();
            }
        });

    }



}
