package com.uwjx.function.tank;

/**
 * 标准油罐类型
 * @author wanghuan
 */
public enum PreSaveTank {

    /**
     * FT55
     */
    FT55(1 ,2170 , 55000),

    /**
     * FT30
     */
    FT30(2 ,2528 , 29350),

    /**
     * FT12
     */
    FT12(3 ,2519 , 11240),

    /**
     * FT68
     */
    FT68(4 ,2527 , 65570),

    /**
     * FT110
     */
    FT110(5 ,3151 , 107656),

    /**
     * PFS13
     */
    PFS13(6 ,2170 , 14211),

    /**
     * PFS5
     */
    PFS5(7 ,2152 , 5263),

    /**
     * PFS17
     */
    PFS17(8 ,2217 , 17895),

    /**
     * PFS23
     */
    PFS23(9 ,2191 , 23158);


    private Integer type;

    private Integer high;

    private Integer volume;

    PreSaveTank(Integer type, Integer high, Integer volume) {
        this.type = type;
        this.high = high;
        this.volume = volume;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
