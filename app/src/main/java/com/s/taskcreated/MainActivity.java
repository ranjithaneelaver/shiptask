package com.s.taskcreated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.s.taskcreated.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int initialvalue = 0;
    int day = 1;
    int prev = 0;
    private ActivityMainBinding binding;
    private ArrayList<Integer> dynamiclist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dynamiclist.add(3);
        dynamiclist.add(6);
        dynamiclist.add(2);
        dynamiclist.add(7);
        dynamiclist.add(5);
        getList();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dynamiclist.size() >= 10) {
                    Toast.makeText(MainActivity.this, "Add Total items between 5 to 10 ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(binding.editTxt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Item should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                dynamiclist.add(Integer.valueOf(binding.editTxt.getText().toString()));
                binding.txtitem.setText("Item list" + dynamiclist.toString());
                binding.editTxt.setText("");

            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dynamiclist.size()< 5) {
                    Toast.makeText(MainActivity.this, "Add Total items between 5 to 10 ", Toast.LENGTH_SHORT).show();
                    return;
                }
                getList();
            }
        });
    }

    private void getList() {
        if (dynamiclist.size() < 1) {
            Toast.makeText(MainActivity.this, "Add Minimum 5 items", Toast.LENGTH_SHORT).show();
            return;
        }
        prev = dynamiclist.size();

        binding.textShp.setText(String.format("Ship Size - %d", dynamiclist.size()));
        binding.print.setText("Ship Values -" + dynamiclist.toString());


        for (int m = 0; m < day; m++) {
            prev = dynamiclist.size();
            for (int i = 0; i < dynamiclist.size() - 1; i++) {

                initialvalue = dynamiclist.get(i);
                if (dynamiclist.get(i + 1) > dynamiclist.get(i)) {
                    dynamiclist.remove(i + 1);
                } else {

                }
            }

            if (prev == dynamiclist.size()) {
                binding.txtremain.setText("Ships Day -" + day + " \n Remaining Ships List -" + dynamiclist.toString());
                day = 1;
                dynamiclist.clear();
                break;
            } else {
                day = day + 1;
            }

        }
    }


}