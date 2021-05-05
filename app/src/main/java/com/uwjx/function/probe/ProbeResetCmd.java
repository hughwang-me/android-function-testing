package com.uwjx.function.probe;

public class ProbeResetCmd extends BaseProbeCmd{

    public ProbeResetCmd() {
        code = ProbeCmdCode.RESET.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getCmd();
    }
}
