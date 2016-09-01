package edu.skku.httphumanict.fcsnsprojectver001.util;

import android.app.Activity;
import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;

public class UtilPhoneInfo {
	
	private static UtilPhoneInfo instance = new UtilPhoneInfo();
	private UtilPhoneInfo() {
		// null
	}
	public static UtilPhoneInfo getInstance() {
		return instance;
	}
	
	Activity m_cActivity;
	
	@SuppressWarnings("deprecation")
	public String getUserPhoneNumber(boolean isHypenType){
		TelephonyManager systemService = (TelephonyManager) m_cActivity.getSystemService(Context.TELEPHONY_SERVICE);
		String strPhoneNumber = systemService.getLine1Number();
		strPhoneNumber = strPhoneNumber.substring(strPhoneNumber.length()-10,strPhoneNumber.length());
		strPhoneNumber="0"+strPhoneNumber;
		if(isHypenType)
			strPhoneNumber = PhoneNumberUtils.formatNumber(strPhoneNumber);
		return strPhoneNumber;
	}

	public void setActivity(Activity cActivity) {
		this.m_cActivity = cActivity;
	}
	
}// end of class
