package com.uwjx.function.cmd;


/**
   * 查询加油机当前状态 (Any state)
   */
public class QueryCurrentStatusCmd extends BaseSendCmd {

    public QueryCurrentStatusCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.QUERY_CURRENT_STATUS.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
