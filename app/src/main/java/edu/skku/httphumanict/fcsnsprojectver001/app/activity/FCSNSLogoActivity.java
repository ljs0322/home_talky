package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import edu.skku.httphumanict.fcsnsprojectver001.R;
import edu.skku.httphumanict.fcsnsprojectver001.dto.User;
import edu.skku.httphumanict.fcsnsprojectver001.util.UtilSPrefer;

public class FCSNSLogoActivity extends AppCompatActivity {

    final static int DELAY_FINISH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        // 시간 측정 용
        long procTime = System.currentTimeMillis();

//        UtilSPrefer.saveStrData(this, , User.class.getSimpleName(), );


        // 1. 사용자 저장 정보 로딩
//        UtilSPrefer.getSharedPreference(,);
        // 2.1 사용자 정보 없는 경우
            // 사용자 가입으로 진행 - 액티비티 전환

        // 2.2 사용자 정보가 있는 경우
            // 사용자 정보를 이용하여 로그인 수행

        // 3. 방 정보 로딩

        // 4.1 방 정보가 없는 경우

        // 4.2 방 정보가 있는 경우

        procTime = procTime - System.currentTimeMillis();
        final FCSNSLogoActivity temp = this;
        if(procTime < 1000){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(temp, "방 화면으로 전환.", Toast.LENGTH_SHORT).show();
                    // 1초 이후 실행
                    Intent intent = new Intent(temp.getApplicationContext(), FCSNSRoomActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000 - procTime);
        }

       /* Intent intent = new Intent(this, FCSNSRoomActivity.class);
        startActivity(intent);
        finish();*/

    }// end of onCreate

    /*private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DELAY_FINISH:
                    *//*Intent intent = new Intent( FCSNSRoomActivity.class);
                    startActivity(intent);
                    finish();*//*
                    break;
            }
        }
    };*/

}// end of class
