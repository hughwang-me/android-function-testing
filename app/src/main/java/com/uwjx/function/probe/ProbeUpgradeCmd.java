package com.uwjx.function.probe;

public class ProbeUpgradeCmd extends BaseProbeCmd{

    public ProbeUpgradeCmd(byte[] dateLength  , byte[] addressOffset , byte[] data) {
        code = ProbeCmdCode.UPGRADE.getByteCode();
        upgradeDataLength = dateLength;
        upgradeAddressOffset = addressOffset;
        upgradeData = data;
    }

    @Override
    public byte[] getSendCmd() {
        return getUpgradeCmd();
    }
}
