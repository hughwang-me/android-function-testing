package com.uwjx.function.pump;

/**
   * 立即停止当前加油过程，关闭电机电磁阀并中断加油 (BUSY,AUTH)
   */
public class StopOilingCmd extends BasePumpCmd {

    public StopOilingCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.STOP_REFUELING.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
