package com.uwjx.function.probe;

public class ProbeQueryLiquidLevelCmd extends BaseProbeCmd{

    public byte   index_all =   (byte)0x30;//探针 所有
    public byte   index_1 =   (byte)0x31;//探针 1
    public byte   index_2 =   (byte)0x32;//探针 2
    public byte   index_3 =   (byte)0x33;//探针 3
    public byte   index_4 =   (byte)0x34;//探针 4

    public ProbeQueryLiquidLevelCmd(int index) {
        code = ProbeCmdCode.QUERY_LIQUID_LEVEL.getByteCode();
        switch (index){
            case 0:
                data = index_all;
                break;
            case 1:
                data = index_1;
                break;
            case 2:
                data = index_2;
                break;
            case 3:
                data = index_3;
                break;
            case 4:
                data = index_4;
                break;
        }
    }

    @Override
    public byte[] getSendCmd() {
        return getCmd();
    }
}
