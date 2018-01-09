package com.jwsdigital.poa.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by eko on 10/26/17.
 */

public class WebServiceHelper {
	public static OkHttpClient client;
	public static void initialize(){
		if ( client == null ){
			client = new OkHttpClient();
			client.newBuilder().connectTimeout(200, TimeUnit.SECONDS);
		}
	}
	public static String doGET(String url ,String token) throws IOException{
		Request request = new Request.Builder()
				.addHeader("Authorization","Bearer " +token)
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		return  response.body().string();
	}
	public static String doPOST( String url, RequestBody body, String token)throws IOException{

		Headers headers = new Headers.Builder()
				.add("Authorization","Bearer " +token)
				.build();
		Request request = new Request.Builder()
				.url(url)
				.headers(headers)
				.post(body)
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}
