package com.uwjx.function.pump;

public class PumpPreUpgradeCmd extends BasePumpCmd {

    public PumpPreUpgradeCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.PRE_UPGRADE.getByteCode();
    }

    public void setDataLength(byte[] dataLength) {
        this.dataLengthUsed = dataLength;
    }

    @Override
    public byte[] getSendCmd() {
        return getPreUpgradeCmd();
    }
}
