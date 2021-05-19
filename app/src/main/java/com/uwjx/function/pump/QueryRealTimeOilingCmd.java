package com.uwjx.function.pump;

/**
   * 查询实时加油数据 (IDLE,EOT)
   */
public class QueryRealTimeOilingCmd extends BasePumpCmd {

    public QueryRealTimeOilingCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.QUERY_REAL_TIME_REFUELING_DATA.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
