package com.weather.service;

import java.util.List;

import com.weather.model.WeatherResp;

public interface WeatherService {

	/**
	 * 
	 * @param country
	 * @return
	 */
	public abstract WeatherResp getWeatherDetailsByCountry(String country);
	
	
}
