package com.uwjx.function.pump;


public enum Hoses {

    HOSE1("40", 1, (byte) 0x40, "hose1")
    ,HOSE2("41", 2, (byte) 0x41, "hose2");

    private String address;
    private int id;
    private byte code;
    private String hoseNo;

    Hoses(String address, int id, byte code, String hoseNo) {
        this.address = address;
        this.id = id;
        this.code = code;
        this.hoseNo = hoseNo;
    }

    public String getAddress() {
        return this.address;
    }

    public int getId() {
        return this.id;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getHoseNo() {
        return hoseNo;
    }

    public void setHoseNo(String hoseNo) {
        this.hoseNo = hoseNo;
    }

    //获取当前对象的名称
    public String getHoseName() {
        String name = "";
        if (this == HOSE1) {
//            name = BaseApplication.getContext().getString(R.string.hose1);
        } else {
//            name = BaseApplication.getContext().getString(R.string.hose2);
        }
        return name;
    }

    //通过id获取Hoses对象
    public static Hoses getHose(int which) {
        Hoses hoses;
        if (which == 1) {
            hoses = HOSE1;
        } else {
            hoses =  HOSE2;
        }
        return hoses;
    }

    //通过address获取Hoses的id
    public static int getId(String address) {
        if (address.equals(HOSE1.address)) {
            return 1;
        } else {
            return 2;
        }
    }

    //通过address获取Hoses
    public static Hoses getHoseByAddr(String address) {
        if (address.equals(HOSE1.address)) {
            return HOSE1;
        } else {
            return HOSE2;
        }
    }
}
