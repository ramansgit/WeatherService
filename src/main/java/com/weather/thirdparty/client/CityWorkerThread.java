package com.weather.thirdparty.client;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.weather.model.CityWeather;

public class CityWorkerThread implements Callable<CityWeather> {

	String cityName;

	public CityWorkerThread(String cityName) {

		this.cityName = cityName;

	}

	@Override
	public CityWeather call() throws Exception {
		return WeatherClient.getWeatherByCity(cityName);

	}

	public static Map<String, CityWeather> getWeatherDataByCountry(String countryName) {

		LinkedHashMap<String, String> cityMapping = new LinkedHashMap<String, String>();

		cityMapping.put("CH", "Chennai");
		cityMapping.put("BGR", "Bangalore");
		cityMapping.put("VLR", "Vellore");
		cityMapping.put("HYD", "Hyderabad");

		ExecutorService pool = Executors.newFixedThreadPool(cityMapping.size());
		Map<String, Future<CityWeather>> result = new HashMap<String, Future<CityWeather>>();
		Map<String, CityWeather> statusResp = new HashMap<String, CityWeather>();

		for (String city : cityMapping.keySet()) {
			Callable<CityWeather> callable = new CityWorkerThread(city);
			Future<CityWeather> future = pool.submit(callable);
			result.put(cityMapping.get(city), future);

		}

		for (String city : result.keySet()) {

			// set.add(future);
			try {
				if (result.get(city).isDone()) {
					statusResp.put(city, result.get(city).get());
				} else {
					statusResp.put(city, result.get(city).get(10000, TimeUnit.MILLISECONDS));
				}
			} catch (TimeoutException e) {
				statusResp.put(city, null);
				// e.printStackTrace();
			} catch (Exception e) {
				statusResp.put(city, null);
				e.printStackTrace();

			}

		}

		System.out.println("statusResp"+statusResp );
		
		return statusResp;

	}

}
