package com.weather.thirdparty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.weather.model.CityWeather;

public class WeatherClient {

	public static CityWeather getWeatherByCity(String cityName) {

		CityWeather weather = new CityWeather();

		try {
			HttpURLConnection con = (HttpURLConnection) (new URL("http://api.openweathermap.org/data/2.5/weather?q="
					+ cityName + "&appid=0990533a25c57201db6a0eebae764030")).openConnection();

			System.out.println("Connection is opened to " + cityName);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			int responseCode = con.getResponseCode();

			if (responseCode >= 200 && responseCode < 300) {

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("Got the response::" + response);
				JSONObject resObj = new JSONObject(response.toString());

				if (resObj != null) {

					if (resObj.has("name")) {
						weather.setName(resObj.getString("name"));
					}

					if (resObj.has("id")) {
						weather.setId(resObj.getInt("id"));
					}
					if (resObj.has("main")) {

						JSONObject mainData = resObj.getJSONObject("main");
						if (mainData != null) {
							if (mainData.has("temp")) {
								weather.setTemp(mainData.getDouble("temp"));
							}
							if (mainData.has("pressure")) {
								weather.setPressure(mainData.getDouble("pressure"));
							}
							if (mainData.has("humidity")) {
								weather.setHumidity(mainData.getInt("humidity"));
							}
							if (mainData.has("temp_min")) {
								weather.setTemp_min(mainData.getDouble("temp_min"));
							}
							if (mainData.has("temp_max")) {
								weather.setTemp_max(mainData.getDouble("temp_max"));
							}
							if (mainData.has("temp")) {
								weather.setTemp(mainData.getDouble("temp"));
							}
						}
					}

				}

			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("Got the failure response from topic::" + response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return weather;
	}
}
