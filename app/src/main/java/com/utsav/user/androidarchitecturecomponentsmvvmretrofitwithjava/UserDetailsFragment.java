package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.resources.TextAppearance;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view.UserDetails;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view.WebView;

public class UserDetailsFragment extends Fragment {
    User user;
    public UserDetailsFragment() {
        super(R.layout.userdetailsfragment);
    }

    EditText name,email,url;

    TextView urlText;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Button edit  = view.findViewById(R.id.edit);

        name = (EditText) view.findViewById(R.id.textView);
        email = (EditText) view.findViewById(R.id.textView3);
        url = (EditText)view.findViewById(R.id.textView11);
        urlText = (TextView) view.findViewById(R.id.textView12);

        name.setText(user.getName().toString());
        email.setText(user.getEmail().toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameText = name.getText().toString();

                user.setFirst_name(fullNameText.substring(0, fullNameText.indexOf(' ')));
                user.setLast_name(fullNameText.substring(fullNameText.indexOf(' ')+1));
                user.setEmail(email.getText().toString());
                ((UserDetails)getActivity()).editUser(user);
            }
        });
        //sending through webview
        urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = url.getText().toString();
                if (text.equalsIgnoreCase("url"))
                {
                    Toast.makeText(view.getContext(), "Please Enter URL", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(view.getContext(), WebView.class).putExtra("url", text));
                }
            }
        });

    }

    public void getUser(User user)
    {
        this.user = user;


    }


}
