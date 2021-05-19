package com.uwjx.function.pump;

public class PumpUpgradeFileCheckCmd extends BasePumpCmd {

    public PumpUpgradeFileCheckCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.UPGRADE_FILE_CHECK.getByteCode();
    }

    public void setUpgradeFileCheckCrc(byte[] upgradeFileCheck) {
        this.upgradeFileCheck = upgradeFileCheck;
    }

    @Override
    public byte[] getSendCmd() {
        return getUpgradeCmd();
    }
}
