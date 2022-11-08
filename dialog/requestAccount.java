package com.mxn.soul.flowingdrawer.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mxn.soul.flowingdrawer.R;

import java.util.HashMap;

public class requestAccount extends AppCompatDialogFragment {

    private EditText ename,epassword,ephone,eemail;
    private TextView heading;
    private requestAccount.EditProfileListener listener;

    private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_request_account, null);

        mRegProgress = new ProgressDialog(getContext());
        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        ename = view.findViewById(R.id.editname);
        eemail = view.findViewById(R.id.editemail);
        ephone = view.findViewById(R.id.editphone);
        heading = view.findViewById(R.id.heading);
        epassword = view.findViewById(R.id.editpassword);
        epassword.setVisibility(View.GONE);

        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(!TextUtils.isEmpty((CharSequence) ename) || !TextUtils.isEmpty((CharSequence) eemail) || !TextUtils.isEmpty((CharSequence) ephone)){

                    String name = ename.getText().toString();
                    String email = eemail.getText().toString();
                    String phone = ephone.getText().toString();
                    String password = epassword.getText().toString();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("PendingAccounts").child(name);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("Name", name);
                    userMap.put("Admission", email);
                    userMap.put("Phone", phone);

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                //                            Snackbar.make(getActivity().findViewById(android.R.id.content),
//                                    "Look at me, I'm a fancy snackbar", Snackbar.LENGTH_LONG).show();
//                            heading.setText("An SMS will be sent to your number about the login details!");

                            } else {

//                            heading.setText("An error occurred! Please Try Again");

                            }

                        }
                    });


                    listener.applyTexts(name,email,phone,password);


                }
                else{
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (requestAccount.EditProfileListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement DialogListener");
        }
    }

    public interface EditProfileListener{
        void applyTexts(String name, String email, String phone, String password);
    }
}
