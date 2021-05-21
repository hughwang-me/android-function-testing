package com.uwjx.function.tank;

/**
 * 探针类型
 * @author wanghuan
 */
public enum ProbeType {

    /**
     * MBAR ZERO
     */
    ZERO(0, 0),
    /**
     * PROBE 200MBAR
     */
    P_200(1, 200),
    /**
     * PROBE 300MBAR
     */
    P_300(2, 300),
    /**
     * PROBE 500MBAR
     */
    P_500(3, 500);

    private int type;
    private int value;

    ProbeType(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
