package edu.bhcc.networking.model;

import java.beans.JavaBean;
import java.io.Serializable;

/**
 * Weather model. This class records the weather condition of one day.
 * @author James (Zhuojian Chen)
 */
@JavaBean
public class Weather implements Serializable {
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

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
