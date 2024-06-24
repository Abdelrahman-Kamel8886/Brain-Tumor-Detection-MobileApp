package com.abdo.braintumordetection.ui.dr.chat.endtoend;

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
import com.abdo.braintumordetection.adapters.AdapterRecyclerChat;
import com.abdo.braintumordetection.databinding.FragmentChatBinding;
import com.abdo.braintumordetection.models.ModelChat;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ChatViewModel viewModel;

    AdapterRecyclerChat adapter = new AdapterRecyclerChat();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentChatBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        getData();
        onClicks();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });


        binding.userTxt.setText("Dr."+SharedModel.getChattitle());

        if(SharedModel.getChatimg()!=""){
            Glide.with(getContext())
                    .load(SharedModel.getChatimg())
                    .into(binding.chatImg);
        }

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        String msg = binding.msgEdit.getText().toString().trim();
        String ChatId = SharedModel.getChat();

        if(msg.isEmpty()){
            binding.msgEdit.setError("Required");
        }
        else{
            ref.child(Consts.CHATS).child(ChatId).push().setValue(new ModelChat(SharedModel.getUsername(),msg))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            binding.msgEdit.setText("");

                        }
                    });
        }

    }

    private void getData(){
        viewModel.getMassages();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelChat>>() {
            @Override
            public void onChanged(ArrayList<ModelChat> list) {
                show(list);
            }
        });
    }

    private void show(ArrayList<ModelChat> list){
        adapter.setmMessageList(list);
        binding.recyclerChat.setAdapter(adapter);
        binding.recyclerChat.smoothScrollToPosition(adapter.getItemCount());

    }

}