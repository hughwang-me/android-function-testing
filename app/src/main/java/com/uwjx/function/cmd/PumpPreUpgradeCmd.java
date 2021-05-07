package com.uwjx.function.cmd;

public class PumpPreUpgradeCmd extends BaseSendCmd {

    private byte[] dataLength = new byte[2];

    public PumpPreUpgradeCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.PRE_UPGRADE.getByteCode();
        dataLengthUsed = this.dataLength;
    }

    public void setDataLength(byte[] dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
