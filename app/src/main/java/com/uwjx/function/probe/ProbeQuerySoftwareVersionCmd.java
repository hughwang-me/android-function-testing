package com.uwjx.function.probe;

public class ProbeQuerySoftwareVersionCmd extends BaseProbeCmd{

    public ProbeQuerySoftwareVersionCmd() {
        code = ProbeCmdCode.QUERY_SOFTWARE_VERSION.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getQueryCmd();
    }
}
