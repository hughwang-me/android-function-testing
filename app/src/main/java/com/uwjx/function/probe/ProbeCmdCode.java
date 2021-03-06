package com.uwjx.function.probe;

public enum  ProbeCmdCode {

    QUERY_LIQUID_LEVEL("30", (byte) 0x30),// 30   查询液位
    QUERY_SOFTWARE_VERSION("20", (byte) 0x20),//20   查询软件版本号
    QUERY_SN("21", (byte) 0x21),//21   查询序列号
    OPEN_24V("22", (byte) 0x22),//22   开启 24V
    OPEN_RELAY("23", (byte) 0x23),//23   开启继电器
    PRE_UPGRADE("24", (byte) 0x24),//24   程序预更新指令
    UPGRADE("25", (byte) 0x25),//25   程序更新
    RESET("26", (byte) 0x26),//26  复位指令
    UPGRADE_FILE_CHECK("27", (byte) 0x27);//27  升级文件核验

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
