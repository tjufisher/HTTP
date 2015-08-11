package com.fisher.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fisher.entity.Result;
import com.google.gson.Gson;

public class HttpClientTest {

	public static void main(String[] args) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://127.0.0.1:8080/HTTP/GetDataServlet");
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			
			out.write("messageName=login&username=fisher&password=123");
			out.flush();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	
		String strLine = "";
		String strResponse = "";
		InputStream in;
		try {
			in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((strLine = reader.readLine()) != null)
			{
				strResponse += strLine + "\n";
			}
			System.out.print(strResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
//		Result result = gson.fromJson(strResponse, Result.class);
//		System.out.print(result.result);
//		System.out.print(result.message);
	}

}
