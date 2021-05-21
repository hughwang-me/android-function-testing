package com.uwjx.function.probe;

public class ProbeUpgradeFileCheckVCmd extends BaseProbeCmd{

    public ProbeUpgradeFileCheckVCmd() {
        code = ProbeCmdCode.UPGRADE_FILE_CHECK.getByteCode();
    }

    public void setCrcCheckData(byte[] crcCheckData) {
        this.crcCheckData = crcCheckData;
    }

    @Override
    public byte[] getSendCmd() {
        return getUpgradeFileCheckCmd();
    }
}
