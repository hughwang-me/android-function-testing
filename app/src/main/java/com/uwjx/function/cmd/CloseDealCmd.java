package com.uwjx.function.cmd;

/**
   * 关闭此次交易 (EOT(FEOT,PEOT))
   */
public class CloseDealCmd extends BaseSendCmd {

    public CloseDealCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.CLOSE_THE_DEAL.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
