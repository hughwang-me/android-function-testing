package com.uwjx.function.probe;

public class ProbeQuerySnCmd extends BaseProbeCmd{

    public ProbeQuerySnCmd() {
        code = ProbeCmdCode.QUERY_SN.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getQueryCmd();
    }
}
