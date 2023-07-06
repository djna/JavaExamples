package org.djna.home;

import java.util.Objects;

public class ThermostatReading {
    private String location;
    private long time;
    private int temperature;

    @Override
    public String toString() {
        return "ThermostatReading{" +
                "location='" + location + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermostatReading that = (ThermostatReading) o;
        return time == that.time && location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, time);
    }

    /**
     * get field
     *
     * @return location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * set field
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * get field
     *
     * @return time
     */
    public long getTime() {
        return this.time;
    }

    /**
     * set field
     *
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * get field
     *
     * @return temperature
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * set field
     *
     * @param temperature
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
