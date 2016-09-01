package edu.skku.httphumanict.fcsnsprojectver001.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilSPrefer {
    /*Singleton Pattern*/
    private static UtilSPrefer instance = new UtilSPrefer();
    private UtilSPrefer(){
        // null
    }
    public static UtilSPrefer getInstance(){
        return instance;
    }

    /*instance 사용하지 않아도 되는 구조*/
    public static SharedPreferences getSharedPreference(Context _cContext, String _strPreferKey){
        return _cContext.getSharedPreferences(_strPreferKey, Context.MODE_PRIVATE);
    }

    public static void saveStrData(Context _cContext, String _strPreferKey, String _strStrKey, String _strData){
        SharedPreferences.Editor editor = _cContext.getSharedPreferences(_strPreferKey, Context.MODE_PRIVATE).edit();
        editor.putString(_strStrKey, _strData);
        editor.apply();
    }

    /**
     * 디폴트인 경우
     * @param _cContext 현재 실행하는 Context
     * @param _strPreferKey 받고자하는 SP의 키
     * @param _strStrKey 그 중 불러오고자 하는 데이터의 식별 값
     * @return 데이터 불러오기
     */
    public static String loadStrData(Context _cContext, String _strPreferKey, String _strStrKey){
        return _cContext.getSharedPreferences(_strPreferKey, Context.MODE_PRIVATE).getString(_strStrKey, "");
    }

}// end of class
