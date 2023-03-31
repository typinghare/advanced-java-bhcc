package edu.bhcc.database.model;

import java.beans.JavaBean;
import java.io.Serializable;

/**
 * Weather model. This class records the weather condition of a day.
 * @author James (Zhuojian Chen)
 */
@SuppressWarnings("unused")
@JavaBean
public class Weather implements Serializable {
    /**
     * The primary key.
     */
    private Long id;

    /**
     * The date string.
     */
    private String date;

    /**
     * Precipitation in ml for the day.
     */
    private Double precipitation;

    /**
     * Maximum temperature (Celsius) for the day.
     */
    private Double tempMax;

    /**
     * Minimum temperature (Celsius) for the day.
     */
    private Double tempMin;

    /**
     * The strength of wind for the day.
     */
    private Double wind;

    /**
     * The weather for the day.
     */
    private String weather;

    /**
     * Returns the primary key.
     * @return the primary key.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key.
     * @param id the primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the date of the day.
     * @return the date of the day.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the day.
     * @param date the date of the day
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the precipitation.
     * @return the precipitation.
     */
    public Double getPrecipitation() {
        return precipitation;
    }

    /**
     * Sets the precipitation.
     * @param precipitation precipitation of the day
     */
    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * Returns the maximum temperature
     * @return the precipitation.
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * Sets the maximum temperature
     * @param tempMax the maximum temperature of the day
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * Returns the minimum temperature.
     * @return the precipitation.
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * Sets the minimum temperature.
     * @param tempMin the minimum temperature of the day
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Returns the wind.
     * @return the precipitation.
     */
    public Double getWind() {
        return wind;
    }

    /**
     * Sets the wind.
     * @param wind the wind of the day
     */
    public void setWind(Double wind) {
        this.wind = wind;
    }

    /**
     * Returns the weather.
     * @return the precipitation.
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Sets the weather.
     * @param weather the weather of the day
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }
}
