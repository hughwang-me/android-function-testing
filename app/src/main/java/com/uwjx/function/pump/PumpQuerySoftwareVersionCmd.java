package com.uwjx.function.pump;

/**
   */
public class PumpQuerySoftwareVersionCmd extends BasePumpCmd {

    public PumpQuerySoftwareVersionCmd(Hoses hoses) {
        addr = hoses.getCode();
        code = Code.QUERY_SOFTWARE_VERSION.getByteCode();
    }

    @Override
    public byte[] getSendCmd() {
        return getThisCmd();
    }
}
