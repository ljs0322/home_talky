package edu.skku.httphumanict.fcsnsprojectver001.util;

import com.google.gson.Gson;

public class UtilGJSON {
	private static Gson s_cGJSON = new Gson();
	
	/*{
		System.out.print("test - ");
		s_cGJSON = new Gson();
		System.out.println("end");
	}*/
	
	public static Object fromJSON(String _strJSON, Class<?> classOfT){
		return s_cGJSON.fromJson(_strJSON, classOfT);
	}
	
	public static String toJSON(Object _objToJSON){
		return s_cGJSON.toJson(_objToJSON);
	}
}// end of class
