package com.abdo.braintumordetection.ui.dr.patientdetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.adapters.PagerAdabterForHome;
import com.abdo.braintumordetection.adapters.RecyclerImagesAdapter;
import com.abdo.braintumordetection.databinding.FragmentPatientDetailsBinding;
import com.abdo.braintumordetection.models.JavaMailAPI;
import com.abdo.braintumordetection.models.ModelPatient;
import com.abdo.braintumordetection.models.PagerModelClass;
import com.abdo.braintumordetection.ui.dr.home.DrHomeFragment;
import com.abdo.braintumordetection.ui.dr.patientdetails.WriteReport.WriteReportViewModel;
import com.abdo.braintumordetection.ui.dr.patients.PatientsFragment;
import com.abdo.braintumordetection.ui.dr.result.ShowResultFragment;
import com.abdo.braintumordetection.ui.pa.home.PatientHomeFragment;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class PatientDetailsFragment extends Fragment{


    FragmentPatientDetailsBinding binding;
    PatientDetailsViewModel viewModel;

    WriteReportViewModel writeReportViewModel;

    DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    String Date = "" ;
    String Time = "";
    int hour , minute ;

    ArrayList<PagerModelClass> model = new ArrayList<>();

    PagerAdabterForHome adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding = FragmentPatientDetailsBinding.bind(view);

        adapter = new PagerAdabterForHome(getChildFragmentManager());

        model.clear();

        model.add(new PagerModelClass(new PatientinfoFragment(), "Patient Details "));
        model.add(new PagerModelClass(new MriImagesFragment(), "Mri's Images"));



        adapter.setData(model);
        binding.Pager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.Pager);
        binding.paTxt.setText(SharedModel.getName()+"");

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });





        binding.bar.setVisibility(View.GONE);


        viewModel = new ViewModelProvider(this).get(PatientDetailsViewModel.class);
        writeReportViewModel =  new ViewModelProvider(this).get(WriteReportViewModel.class);

        Calendar calendar =Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SendData();
                // requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ShowResultFragment(), "sh").addToBackStack("sh").commit();


            }
        });


        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (i<10){
                    if(i1<10){
                        Time="0"+i+":"+"0"+i1;
                    }
                    else{
                        Time="0"+i+":"+i1;
                    }
                }
                else if (i1<10){
                    Time=i+":"+"0"+i1;
                }
                else{
                    Time=i+":"+i1;
                }
                showDialog();
            }
        };

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                Date=i+"-"+i1+"-"+i2;
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),onTimeSetListener , hour ,minute ,false );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                timePickerDialog.show();


            }
        };



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void showDialog(){
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);

        Button confirm = dialog.findViewById(R.id.confirm);
        Button connection = dialog.findViewById(R.id.conection);
        TextView date = dialog.findViewById(R.id.date);
        TextView time= dialog.findViewById(R.id.time);

        date.setText(Date);
        time.setText(Time);



        confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                if(connection.getStateDescription().equals("OFF")){
                    sendEmail(false);
                }
                else{
                    sendEmail(true);
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void sendEmail(boolean state){
        String mEmail = SharedModel.getEmail();
        String mSubject = Consts.MAIL_SUBJECT;
        String mMessage ="";

        if(state){
            mMessage ="BTD\n\n" +
                    "Dear Mr/Ms. "+SharedModel.getName()+" :" +
                    "\n\nThis letter is a notification that an appointment with the doctor has been scheduled on the following date "+Date+" at " +Time+
                    "\nIf you encounter any problem, please contact the following number : " +SharedModel.getDrphone()+
                    "\n\n["+SharedModel.getUsername()+"]";
        }
        else{
            mMessage ="BTD\n\n" +
                    "Dear Mr/Ms. "+SharedModel.getName()+" :" +
                    "\n\nThis letter is a notification that an appointment with the doctor has been scheduled on the following date "+Date+" at " +Time+
                    "\nIf you encounter any problem, please contact the following email : " +Consts.EMAIL+
                    "\n\n[Dr."+SharedModel.getUsername()+"]";
        }
        if (isConnected()) {
            JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), mEmail, mSubject, mMessage);
            javaMailAPI.execute();
            binding.bar.setVisibility(View.VISIBLE);
            showt();
        } else {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }






    }
    private void showt(){
        final Handler handler = new Handler();

        Timer timer2 = new Timer();

        TimerTask testing = new TimerTask() {

            public void run() {

                handler.post(new Runnable() {

                    public void run() {
                        binding.bar.setVisibility(View.GONE);
                        if (isConnected()) {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            requireActivity().onBackPressed();
                        } else {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                });

            }

        };

        timer2.schedule(testing, 4000);
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


    private void SendData(){
        writeReportViewModel.SendData();
        writeReportViewModel.success.observe(getViewLifecycleOwner(), new Observer<Integer>() {
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
