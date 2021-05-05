package com.uwjx.function.cmd;

/**
   * 加油机授权 (IDLE,CALL,STOP)
   */
public class AuthCmd extends BaseSendCmd {

    public AuthCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.REFUELLER_AUTH.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
