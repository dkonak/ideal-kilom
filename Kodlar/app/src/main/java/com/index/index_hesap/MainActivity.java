package com.index.index_hesap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edittext;
    private TextView boy_tv,durum_tv,ideal_tv,kilo_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean erkekmi=true;
    private Double boy=0.0;
    private int kilo=30;
    private RadioGroup.OnCheckedChangeListener radioGroupolayisleyicisi= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (checkedId==R.id.erkek)
                erkekmi=true;
            else if (checkedId==R.id.kadın)
                erkekmi=false;
            guncelle();
        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarolayisleyicisi =new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
           kilo=30+progress;
           guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext=findViewById(R.id.editTextNumber2);
        boy_tv=findViewById(R.id.boy_tv);
        durum_tv=findViewById(R.id.textView27);
        ideal_tv=findViewById(R.id.textView26);
        kilo_tv=findViewById(R.id.kilo_tv);
        radioGroup=findViewById(R.id.radioGrup);
        seekBar=findViewById(R.id.seekBar4);
        seekBar.setOnSeekBarChangeListener(seekBarolayisleyicisi);
        radioGroup.setOnCheckedChangeListener(radioGroupolayisleyicisi);

        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    boy=Double.parseDouble(s.toString())/100.0;

                }catch (NumberFormatException e){
                    boy=0.0;
                }
                guncelle();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        guncelle();
    }

    private void guncelle() {
        kilo_tv.setText(String.valueOf(kilo) + " kg");
        int ideal_kiloerkek= (int) (50+2.3*(boy*100*0.4-60));
        int ideal_kilokadın= (int) (45.5+2.3*(boy*100*0.4-60));
        double vki = kilo/(boy*boy);
        if(erkekmi){
             //erkek ise:
             ideal_tv.setText(String.valueOf(ideal_kiloerkek));
         if(vki<=20.7){
             durum_tv.setBackgroundResource(R.color.zayıf);
             durum_tv.setText(R.string.zayıf);
         }else if (20.7<vki && vki <=26.4){
             //ideal kilo
             durum_tv.setText(R.string.ideal);
             durum_tv.setBackgroundResource(R.color.ideal);
         }else if(26.4<vki && vki <=27.8){
             //normal kilodan fazla
             durum_tv.setBackgroundResource(R.color.normalden_fazla);
             durum_tv.setText(R.string.normalden_fazla);
         }else if(27.8<vki && vki <=31.1){
             //fazla kilolu
             durum_tv.setBackgroundResource(R.color.fazla_kilolu);
             durum_tv.setText(R.string.fazla_kilolu);
         }else if(31.1<vki && vki <=34.9){
              //obez
             durum_tv.setBackgroundResource(R.color.obez);
             durum_tv.setText(R.string.obez);
         }else{
             //doktor tedavisi
             durum_tv.setBackgroundResource(R.color.doktor_tedavisi);
             durum_tv.setText(R.string.doktor_tedavisi);
         }
        }else{
            //kadın ise:
            ideal_tv.setText(String.valueOf(ideal_kilokadın));
            if(vki<=19.1){
                durum_tv.setBackgroundResource(R.color.zayıf);
                durum_tv.setText(R.string.zayıf);
            }else if (19.1<vki && vki <=25.8){
                //ideal kilo
                durum_tv.setText(R.string.ideal);
                durum_tv.setBackgroundResource(R.color.ideal);
            }else if(25.8<vki && vki <=27.3){
                //normal kilodan fazla
                durum_tv.setBackgroundResource(R.color.normalden_fazla);
                durum_tv.setText(R.string.normalden_fazla);
            }else if(27.3<vki && vki <=32.3){
                //fazla kilolu
                durum_tv.setBackgroundResource(R.color.fazla_kilolu);
                durum_tv.setText(R.string.fazla_kilolu);
            }else if(32.3<vki && vki <=34.9){
                //obez
                durum_tv.setBackgroundResource(R.color.obez);
                durum_tv.setText(R.string.obez);
            }else{
                //doktor tedavisi
                durum_tv.setBackgroundResource(R.color.doktor_tedavisi);
                durum_tv.setText(R.string.doktor_tedavisi);
            }
        }
    }
}
