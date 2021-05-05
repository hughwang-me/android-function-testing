package com.uwjx.function.probe;

public class ProbeOpenRelayCmd extends BaseProbeCmd{

    public byte   open =   (byte)0x31;//开启
    public byte   close =   (byte)0x30;//关闭
    public ProbeOpenRelayCmd(int status) {
        code = ProbeCmdCode.OPEN_RELAY.getByteCode();
        switch (status){
            case 0:
                data = close;
                break;
            case 1:
                data = open;
                break;
        }
    }

    @Override
    public byte[] getSendCmd() {
        return getCmd();
    }
}
