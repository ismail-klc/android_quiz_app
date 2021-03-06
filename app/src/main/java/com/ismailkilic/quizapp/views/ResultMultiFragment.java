package com.ismailkilic.quizapp.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.StaticData;

public class ResultMultiFragment extends Fragment {
    TextView username1, username2, user1Point,user2Point, txtResultMulti;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_multi, container, false);

        username1 = view.findViewById(R.id.user1Name);
        username2 = view.findViewById(R.id.user2Name);
        user1Point = view.findViewById(R.id.user1Point);
        user2Point = view.findViewById(R.id.user2Point);
        txtResultMulti = view.findViewById(R.id.txtResultMulti);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initResult();
    }

    private void initResult() {
        username1.setText(StaticData.user1);
        username2.setText(StaticData.user2);
        user1Point.setText(String.valueOf(StaticData.user1Point));
        user2Point.setText(String.valueOf(StaticData.user2Point));

        if (StaticData.user2Point == StaticData.user1Point){
            txtResultMulti.setText("Draw");
        }
        else if (StaticData.user2Point > StaticData.user1Point){
            txtResultMulti.setText(StaticData.user2 + " Won");
        }
        else {
            txtResultMulti.setText(StaticData.user1 + " Won");
        }
    }

}
