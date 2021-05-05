package com.uwjx.function.probe;

public class ProbePreUpgradeCmd extends BaseProbeCmd{


    public ProbePreUpgradeCmd(byte[] dataLength) {
        code = ProbeCmdCode.PRE_UPGRADE.getByteCode();
        upgradeDataLength = dataLength;
    }

    @Override
    public byte[] getSendCmd() {
        return getPreUpgradeCmd();
    }
}
