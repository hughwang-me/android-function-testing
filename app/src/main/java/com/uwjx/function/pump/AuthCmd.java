package com.uwjx.function.pump;

/**
   * 加油机授权 (IDLE,CALL,STOP)
   */
public class AuthCmd extends BasePumpCmd {

    public AuthCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.REFUELLER_AUTH.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
