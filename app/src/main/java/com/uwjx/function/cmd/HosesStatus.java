package com.uwjx.function.cmd;


public enum HosesStatus {

    STANDBY
    , AUTHORIZATION
    , DISPENSING
    , EMPTY;

    public String getItString() {
        String str = "";
        if (this == STANDBY) {
//            str = BaseApplication.getContext().getString(R.string.standBy);
        } else if (this == AUTHORIZATION) {
//            str = BaseApplication.getContext().getString(R.string.authorization);
        } else if (this == DISPENSING) {
//            str = BaseApplication.getContext().getString(R.string.dispensing);
        } else {
//            str = BaseApplication.getContext().getString(R.string.empty);
        }
        return str;
    }
}
