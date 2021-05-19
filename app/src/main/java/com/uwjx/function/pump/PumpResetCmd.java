package com.uwjx.function.pump;

/**
   * 关闭此次交易 (EOT(FEOT,PEOT))
   */
public class PumpResetCmd extends BasePumpCmd {

    public PumpResetCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.RESET.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
