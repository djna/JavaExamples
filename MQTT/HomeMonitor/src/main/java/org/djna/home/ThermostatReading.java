package org.djna.home;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

public class ThermostatReading {
    private String location;
    private long time;
    private int temperature;

    public ThermostatReading(String location, long time, int temperature) {
        this.location = location;
        this.time = time;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ThermostatReading{" +
                "location='" + location + '\'' +
                ", time=" + getFormattedTime() +
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
     * get field
     *
     * @return time as a human readable String
     */
    public String getFormattedTime() {
        Instant instant = Instant.ofEpochMilli (this.time);

        ZoneId zoneId = ZoneId.of ( "GMT" );
        ZonedDateTime zdt = instant.atZone ( zoneId );

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL );
        formatter = formatter.withLocale ( Locale.UK );
        return zdt.format(formatter);
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
