package com.weather.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.weather.model.CityWeather;
import com.weather.model.WeatherResp;
import com.weather.thirdparty.client.CityWorkerThread;

public class WeatherServiceImpl implements WeatherService {

	@Override
	public WeatherResp getWeatherDetailsByCountry(String country) {

		List<CityWeather> cites = new ArrayList<CityWeather>();

		WeatherResp resp = new WeatherResp();

		resp.setCountry(country);

		Map<String, CityWeather> data = CityWorkerThread.getWeatherDataByCountry(country);
		Set<String> sets = data.keySet();

		for (String key : sets) {
			cites.add(data.get(key));
		}
		resp.setCityWeather(cites);

		return resp;
	}

	
	
	
}
