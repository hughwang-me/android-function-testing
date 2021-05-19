package com.uwjx.function.pump;

public class PumpUpgradeCmd extends BasePumpCmd {

    public PumpUpgradeCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.UPGRADE.getByteCode();
    }

    public void setDateLength(byte[] dateLength) {
        dataLengthUsed = dateLength;
    }

    public void setAddressOffset(byte[] addressOffset) {
        addressOffsetUsed = addressOffset;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public byte[] getSendCmd() {
        return getUpgradeCmd();
    }
}
