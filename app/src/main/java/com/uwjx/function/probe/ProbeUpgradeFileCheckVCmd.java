package com.uwjx.function.probe;

public class ProbeUpgradeFileCheckVCmd extends BaseProbeCmd{

    private byte[] crcCheckData;

    public ProbeUpgradeFileCheckVCmd() {
        code = ProbeCmdCode.UPGRADE_FILE_CHECK.getByteCode();
        usedCrcCheckData = crcCheckData;
    }

    public void setCrcCheckData(byte[] crcCheckData) {
        this.crcCheckData = crcCheckData;
    }

    @Override
    public byte[] getSendCmd() {
        return getUpgradeFileCheckCmd();
    }
}
