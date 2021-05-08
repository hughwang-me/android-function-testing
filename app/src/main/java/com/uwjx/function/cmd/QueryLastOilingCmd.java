package com.uwjx.function.cmd;

/**
   * 查询上次加油升数 (IDLE,EOT)
   */
public class QueryLastOilingCmd extends BasePumpCmd {

    public QueryLastOilingCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.QUERY_LAST_REFUELING_LITER.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
