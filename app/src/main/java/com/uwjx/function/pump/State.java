package com.uwjx.function.pump;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:加油状态阐述枚举
 */
public enum State {

    ERROR("ERROR","30"),
    IDLE("IDLE","31"), //IDLE/OFF
    CALL("CALL","32"), //Nozzle ON
    AUTH("AUTH","33"), //Authrized
    BUSY("BUSY","34"), //in fueling
    STOP("STOP","35"), //pending
    PEOT("PEOT","36"), //EOT at preset
    FEOT("FEOT","37"); //EOT with fill up

    private String value;
    private String code;

    State(String value, String code) {
        this.value = value;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add(ERROR.code);
        list.add(IDLE.code);
        list.add(CALL.code);
        list.add(AUTH.code);
        list.add(BUSY.code);
        list.add(STOP.code);
        list.add(PEOT.code);
        list.add(FEOT.code);
        return list;
    }

    //传入code获取对应的Status
    public static State getStatus(String code) {
        State state = null;
        if (code.equals(ERROR.code)) {
            state = ERROR;
        } else if (code.equals(IDLE.code)) {
            state = IDLE;
        } else if (code.equals(CALL.code)) {
            state = CALL;
        } else if (code.equals(AUTH.code)) {
            state = AUTH;
        } else if (code.equals(BUSY.code)) {
            state = BUSY;
        } else if (code.equals(STOP.code)) {
            state = STOP;
        } else if (code.equals(PEOT.code)) {
            state = PEOT;
        } else if (code.equals(FEOT.code)) {
            state = FEOT;
        }
        return state;
    }
}
