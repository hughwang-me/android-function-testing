package com.uwjx.function.cmd;

import java.util.ArrayList;
import java.util.List;

/**
 * IDLE：空闲
 * CALL：油枪已提起但尚未授权
 * BUSY：加油中 AUTH：授权 STOP：急停
 * PEOT：达到预设值后加油完成
 * FEOT：加油完成
 */

public enum Code {

    QUERY_CURRENT_STATUS("50", (byte) 0x50),//0x50   查询加油机当前状态   Any state
    PRESET_FUEL_LITER("51", (byte) 0x51),//0x51  预设加油升数  IDLE,CALL
    REFUELLER_AUTH("52", (byte) 0x52),//0x52 加油机授权   IDLE,CALL,STOP
    QUERY("53", (byte) 0x53),//0x53  查询 Pulse Per Litre  IDLE
    MODIFY("54", (byte) 0x54),//0x54 修改 Pulse Per Litre  IDLE
    QUERY_SOLENOID_VALVE_CONFIG("55", (byte) 0x55),//0x55    查询电磁阀配置 IDLE
    MODIFY_SOLENOID_VALVE_CONFIG("56", (byte) 0x56),//0x56 	修改电磁阀配置 IDLE
    QUERY_OVERTIME_OF_NO_FLOW("57", (byte) 0x57),//0x57  查询无流量超时时间   IDLE
    MODIFY_OVERTIME_OF_NO_FLOW("58", (byte) 0x58),//0x58 修改无流量超时时间   IDLE
    QUERY_OVERTIME_OF_AUTH("59", (byte) 0x59),//0x59  查询授权超时时间    IDLE
    MODIFY_OVERTIME_OF_AUTH("5A", (byte) 0x5A),//0x5A    修改授权超时时间    IDLE
    QUERY_THE_LIMIT_OF_SINGLE_REFUELING_RISE("5B", (byte) 0x5B),//0x5B   查询单次加油升数限制 	IDLE
    MODIFY_THE_LIMIT_OF_SINGLE_REFUELING_LITER("5C", (byte) 0x5C),//0x5C 修改单次加油升数限制 	IDLE
    QUERY_LAST_REFUELING_LITER("5D", (byte) 0x5D),//0x5D 查询上次加油升数    IDLE,EOT
    STOP_REFUELING("5E", (byte) 0x5E),//0x5E 立即停止当前加油过程，关闭电机电磁阀并中断加油 BUSY,AUTH
    CLOSE_THE_DEAL("5F", (byte) 0x5F),//0x5F 关闭此次交易  EOT(FEOT,PEOT)
    QUERY_REAL_TIME_REFUELING_DATA("60", (byte) 0x60),//0x60 查询实时加油数据    IDLE,EOT
    QUERY_THE_TRANSACTION_INFOR("61", (byte) 0x61),//0x61   查询此次交易信息    IDLE
    QUERY_TOTAL_FUEL_LITERS("62", (byte) 0x62),//0x62   查询加油升数总计    Any State
    RESET_TOTAL_REFUELING("63", (byte) 0x63),//0x63  加油总数清零  BUSY
    QUERY_CURRENT_CONFIG("64", (byte) 0x64),//0x64   查询当前配置  EOT,IDLE
    QUERY_SOFTWARE_VERSION("65", (byte) 0x65),//0x65 查询软件版本号 Any State
    QUERY_SERIAL_NO("66", (byte) 0x66),//0x66    查询序列号   Any State
    VIRTUAL_OIL_GUN_LIFTING("67", (byte) 0x67),//0x67    虚拟油枪提起  AUTH
    QUERY_TANK_LEVEL("30", (byte) 0x30),//0x30   查询tank的页面高度
    QUERY_TANK1_LEVEL("31", (byte) 0x31),//0x30   查询tank的页面高度
    QUERY_TANK2_LEVEL("32", (byte) 0x32),//0x30   查询tank的页面高度
    QUERY_TANK3_LEVEL("33", (byte) 0x33),//0x30   查询tank的页面高度
    QUERY_TANK4_LEVEL("34", (byte) 0x34),//0x30   查询tank的页面高度
    QUERY_TANK5_LEVEL("35", (byte) 0x35),//0x30   查询tank的页面高度
    QUERY_TANK6_LEVEL("36", (byte) 0x36),//0x30   查询tank的页面高度
    MODIFY_HL("68", (byte) 0x68),//0x59  修改高低档    IDLE

    PRE_UPGRADE("69", (byte) 0x69),//0x59  修改高低档    IDLE
    UPGRADE("6A", (byte) 0x6A);//0x59  修改高低档    IDLE

    private String value;
    private byte byteCode;

    public String getValue() {
        return value;
    }

    public byte getByteCode() {
        return byteCode;
    }

    Code(String value, byte byteCode) {
        this.value = value;
        this.byteCode = byteCode;
    }

    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add(QUERY_CURRENT_STATUS.value);
        list.add(PRESET_FUEL_LITER.value);
        list.add(REFUELLER_AUTH.value);
        list.add(QUERY.value);
        list.add(MODIFY.value);
        list.add(QUERY_SOLENOID_VALVE_CONFIG.value);
        list.add(MODIFY_SOLENOID_VALVE_CONFIG.value);
        list.add(QUERY_OVERTIME_OF_NO_FLOW.value);
        list.add(MODIFY_OVERTIME_OF_NO_FLOW.value);
        list.add(QUERY_OVERTIME_OF_AUTH.value);
        list.add(MODIFY_OVERTIME_OF_AUTH.value);
        list.add(QUERY_THE_LIMIT_OF_SINGLE_REFUELING_RISE.value);
        list.add(MODIFY_THE_LIMIT_OF_SINGLE_REFUELING_LITER.value);
        list.add(QUERY_LAST_REFUELING_LITER.value);
        list.add(STOP_REFUELING.value);
        list.add(CLOSE_THE_DEAL.value);
        list.add(QUERY_REAL_TIME_REFUELING_DATA.value);
        list.add(QUERY_THE_TRANSACTION_INFOR.value);
        list.add(QUERY_TOTAL_FUEL_LITERS.value);
        list.add(RESET_TOTAL_REFUELING.value);
        list.add(QUERY_CURRENT_CONFIG.value);
        list.add(QUERY_SOFTWARE_VERSION.value);
        list.add(QUERY_SERIAL_NO.value);
        list.add(VIRTUAL_OIL_GUN_LIFTING.value);
        list.add(QUERY_TANK_LEVEL.value);
        list.add(QUERY_TANK_LEVEL.value);
        list.add(QUERY_TANK1_LEVEL.value);
        list.add(QUERY_TANK2_LEVEL.value);
        list.add(QUERY_TANK3_LEVEL.value);
        list.add(QUERY_TANK4_LEVEL.value);
        list.add(QUERY_TANK5_LEVEL.value);
        list.add(QUERY_TANK6_LEVEL.value);
        list.add(MODIFY_HL.value);
        return list;
    }

    public static Code getCode(String code) {
        Code c = null;
        if (code.equals(QUERY_CURRENT_STATUS.value)) {
            c = QUERY_CURRENT_STATUS;
        } else if (code.equals(PRESET_FUEL_LITER.value)) {
            c = PRESET_FUEL_LITER;
        } else if (code.equals(REFUELLER_AUTH.value)) {
            c = REFUELLER_AUTH;
        } else if (code.equals(QUERY.value)) {
            c = QUERY;
        } else if (code.equals(MODIFY.value)) {
            c = MODIFY;
        } else if (code.equals(QUERY_SOLENOID_VALVE_CONFIG.value)) {
            c = QUERY_SOLENOID_VALVE_CONFIG;
        } else if (code.equals(MODIFY_SOLENOID_VALVE_CONFIG.value)) {
            c = MODIFY_SOLENOID_VALVE_CONFIG;
        } else if (code.equals(QUERY_OVERTIME_OF_NO_FLOW.value)) {
            c = QUERY_OVERTIME_OF_NO_FLOW;
        } else if (code.equals(MODIFY_OVERTIME_OF_NO_FLOW.value)) {
            c = MODIFY_OVERTIME_OF_NO_FLOW;
        } else if (code.equals(QUERY_OVERTIME_OF_AUTH.value)) {
            c = QUERY_OVERTIME_OF_AUTH;
        } else if (code.equals(MODIFY_OVERTIME_OF_AUTH.value)) {
            c = MODIFY_OVERTIME_OF_AUTH;
        } else if (code.equals(QUERY_THE_LIMIT_OF_SINGLE_REFUELING_RISE.value)) {
            c = QUERY_THE_LIMIT_OF_SINGLE_REFUELING_RISE;
        } else if (code.equals(MODIFY_THE_LIMIT_OF_SINGLE_REFUELING_LITER.value)) {
            c = MODIFY_THE_LIMIT_OF_SINGLE_REFUELING_LITER;
        } else if (code.equals(QUERY_LAST_REFUELING_LITER.value)) {
            c = QUERY_LAST_REFUELING_LITER;
        } else if (code.equals( STOP_REFUELING.value)) {
            c = STOP_REFUELING;
        } else if (code.equals(CLOSE_THE_DEAL.value)) {
            c = CLOSE_THE_DEAL;
        } else if (code.equals(QUERY_REAL_TIME_REFUELING_DATA.value)) {
            c = QUERY_REAL_TIME_REFUELING_DATA;
        } else if (code.equals(QUERY_THE_TRANSACTION_INFOR.value)) {
            c = QUERY_THE_TRANSACTION_INFOR;
        } else if (code.equals(QUERY_TOTAL_FUEL_LITERS.value)) {
            c = QUERY_TOTAL_FUEL_LITERS;
        } else if (code.equals( RESET_TOTAL_REFUELING.value)) {
            c = RESET_TOTAL_REFUELING;
        } else if (code.equals( QUERY_CURRENT_CONFIG.value)) {
            c = QUERY_CURRENT_CONFIG;
        } else if (code.equals( QUERY_SOFTWARE_VERSION.value)) {
            c = QUERY_SOFTWARE_VERSION;
        } else if (code.equals( QUERY_SERIAL_NO.value)) {
            c = QUERY_SERIAL_NO;
        } else if (code.equals( VIRTUAL_OIL_GUN_LIFTING.value)) {
            c = VIRTUAL_OIL_GUN_LIFTING;
        } else if (code.equals( QUERY_TANK_LEVEL.value)) {
            c = QUERY_TANK_LEVEL;
        } else if (code.equals( QUERY_TANK1_LEVEL.value)) {
            c = QUERY_TANK1_LEVEL;
        } else if (code.equals( QUERY_TANK2_LEVEL.value)) {
            c = QUERY_TANK2_LEVEL;
        } else if (code.equals( QUERY_TANK3_LEVEL.value)) {
            c = QUERY_TANK3_LEVEL;
        } else if (code.equals( QUERY_TANK4_LEVEL.value)) {
            c = QUERY_TANK4_LEVEL;
        } else if (code.equals( QUERY_TANK5_LEVEL.value)) {
            c = QUERY_TANK5_LEVEL;
        } else if (code.equals( QUERY_TANK6_LEVEL.value)) {
            c = QUERY_TANK6_LEVEL;
        } else if (code.equals( MODIFY_HL.value)) {
            c = MODIFY_HL;
        }
        return c;
    }
}

