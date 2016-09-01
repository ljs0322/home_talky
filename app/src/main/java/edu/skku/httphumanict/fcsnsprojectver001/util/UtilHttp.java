package edu.skku.httphumanict.fcsnsprojectver001.util;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP 요청 응답을 관장하는 Util 클래스
 */
public class UtilHttp {
	public static final String TAG = UtilHttp.class.getSimpleName();

	public String postURL(String urlSpec, Map<String, String> mapParams) throws IOException {
		// mapParams의 전처리
		StringBuilder strBuilder = new StringBuilder();
		for(String strKey : mapParams.keySet()){
			strBuilder.append(String.format("%s=%s", strKey, mapParams.get(strKey)));
			strBuilder.append("&");
		}
		String strParams = strBuilder.substring(0, strBuilder.length() - 1);
		Log.d(TAG, "Post 요청 " + strParams);
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		// 요청 헤더를 정의함
		connection.setRequestProperty("Accept", "application/json");
		// 새로운 OutputStream에 요청할 OutputStream을 넣는다.
		OutputStream os = connection.getOutputStream();
		// write 메소드로 메시지로 작성된 파라미터 정보를 바이트 단위로 인코딩하여 요청한다.
		//! 기본 한글 요청에 대해서 UTF-8로 해보자. || 안될경우 EUC-KR로 설정
		os.write(strParams.getBytes("UTF-8"));
		// 스트림 버퍼를 비운다.
		os.flush();
		// 스트림을 닫는다.
		os.close();
		// 응답받은 메시지의 길이만큼 버퍼를 생성하여 읽어들인다.
		//! 이때도 역시 UTF-8로 인코딩하여 읽어들인다.| 한글이 안될경우 EUC-KR로 다시해보자.
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), connection.getContentLength());
		strBuilder = new StringBuilder();
		String strBuf;
		Log.d(TAG, "Post 요청 후 응답 수신 내역: ");
		// 표준출력을 이용하여 한라인씩 읽어들인다.
		while((strBuf = br.readLine()) != null){
			Log.d(TAG, strBuf); // 수신 내역 출력
			strBuilder.append(strBuf);
		}
		br.close();
		return strBuilder.toString();
	}

	/**
	 * url 주소로 부터 byte 값으로 서버로 부터 응답을 받음.
	 * @param urlSpec
	 * @return
	 * @throws IOException
	 */
	byte [] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();

			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			int bytesRead = 0;
			byte [] buffer = new byte[1024];
			while((bytesRead = in.read(buffer)) > 0){
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally {
			connection.disconnect();
		}
	}

	/**
	 * getURL로 부터 데이터를 받아와서 그것을 String으로 변환한다.
	 * @param urlSpec
	 * @return
	 * @throws IOException
	 */
	public String getURL(String urlSpec) throws IOException {
		return new String(getUrlBytes(urlSpec));
	}

	public String getURL(String urlSpec, Map<String, String> mapParam) throws IOException {

		Uri.Builder uriBuilder = Uri.parse(urlSpec).buildUpon();
		for(String strKey : mapParam.keySet()){
			uriBuilder.appendQueryParameter(strKey, mapParam.get(strKey));
		}
		String strURL = uriBuilder.toString();
		return getURL(strURL);
	}



	/*static final String TAG = UtilHttp.class.getSimpleName();
	String servUrl;
	private static UtilHttp instance = new UtilHttp();
	public static UtilHttp getInstance() {
		return instance;
	}

	// - body

	// singleton pattern
	private UtilHttp() {
		System.out.println("UtilHttp");
		// userCmd = "";
	}
	
	public void reqPost(final String strCmd,
			final String[] arrKeys, final String[] arrValues) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				String strData = null;
				if (arrKeys.length != arrValues.length)
					return;
				// cmd - gen GET type url
				String strServURL = getServUrl();
				if(strCmd != null && !strCmd.isEmpty())
					strServURL +=("?cmd=" + strCmd) ;

				HttpClient http = new DefaultHttpClient();
				try {
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					String strGet = "?";
					for (int i = 0; i < arrValues.length; i++) {
						nameValuePairs.add(new BasicNameValuePair(arrKeys[i], arrValues[i]));
						strGet += (arrKeys[i] + "=" + arrValues[i] + "&");
					}
					strGet = strGet.substring(0, strGet.length()-1);
					strServURL += strGet;
					// set post data key and value
					HttpPost httpPost = new HttpPost(strServURL);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();

					UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(
							nameValuePairs, "UTF-8");
					httpPost.setEntity(entityRequest);
					strData = http.execute(httpPost, responseHandler);
					strData = new String(strData.getBytes("iso-8859-1"), "euc-kr");
					Log.d(TAG + " Http Post Response: ", strData);
					//!!! Customizing !!!//
					MCAppManager.getInstance().sendPresActivityHandlerMsg(IMasCareable.MC_HDR_FLAG_HTTP_RX, strData); // 이데이터는 완전한 JSON 문구로 생각하면 된다.
					// 원래는 파서를 사용하는데...
					return;
				} catch (IOException e) {
					MCAppManager.getInstance().sendPresActivityHandlerMsg(IMasCareable.MC_HDR_HTTP_ERROR, e.getMessage());
					return;
				}
			}
		}).start();
	}// end of reqPost

	public String getServUrl() {
		return servUrl;
	}

	public void setServUrl(String servUrl) {
		this.servUrl = servUrl;
	}*/


}// end of class
