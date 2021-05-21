package com.uwjx.function.tank;

/**
 * 计算容积、百分比、安全容积
 * @author wanghuan
 */
public class VpsflModel {

    private Double volume;

    private Double percentage;

    private Double safeFillLevel;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getSafeFillLevel() {
        return safeFillLevel;
    }

    public void setSafeFillLevel(Double safeFillLevel) {
        this.safeFillLevel = safeFillLevel;
    }
}
