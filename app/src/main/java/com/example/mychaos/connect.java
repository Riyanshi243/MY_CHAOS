package com.example.mychaos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class connect extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_connect, container, false);
        Toast.makeText(getActivity(),"THIS SECTION WILL BE USED TO CONNECT WITH FRIENDS",Toast.LENGTH_LONG).show();
        return v;
    }
}
