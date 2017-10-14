package com.weather.model;

public class CityWeather {

	private int id;
	private String name;
	private double temp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private double pressure;
	private  int humidity;
	private double temp_min;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
	private double temp_max;
	@Override
	public String toString() {
		return "CityWeather [id=" + id + ", name=" + name + ", temp=" + temp + ", pressure=" + pressure + ", humidity="
				+ humidity + ", temp_min=" + temp_min + ", temp_max=" + temp_max + "]";
	}
	
	
	
}
