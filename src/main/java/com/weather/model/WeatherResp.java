package com.weather.model;

import java.util.List;

public class WeatherResp {

	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<CityWeather> getCityWeather() {
		return cityWeather;
	}

	public void setCityWeather(List<CityWeather> cityWeather) {
		this.cityWeather = cityWeather;
	}

	private List<CityWeather> cityWeather;
	

}
