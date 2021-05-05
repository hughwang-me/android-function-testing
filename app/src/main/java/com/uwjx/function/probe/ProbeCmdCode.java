package com.uwjx.function.probe;

public enum  ProbeCmdCode {

    QUERY_LIQUID_LEVEL("30", (byte) 0x30),// 30   查询液位
    QUERY_SOFTWARE_VERSION("20", (byte) 0x20),//20   查询软件版本号
    QUERY_SN("21", (byte) 0x21),//21   查询序列号
    QUERY_OPEN_24V("22", (byte) 0x22),//22   开启 24V
    QUERY_OPEN_RELAY("23", (byte) 0x23),//23   开启继电器
    QUERY_PRE_UPGRADE("24", (byte) 0x24),//24   程序预更新指令
    QUERY_UPGRADE("25", (byte) 0x25),//25   程序更新
    MODIFY_RESET("26", (byte) 0x26);//26  复位指令

    private String value;
    private byte byteCode;

    public String getValue() {
        return value;
    }

    public byte getByteCode() {
        return byteCode;
    }

    ProbeCmdCode(String value, byte byteCode) {
        this.value = value;
        this.byteCode = byteCode;
    }
}
