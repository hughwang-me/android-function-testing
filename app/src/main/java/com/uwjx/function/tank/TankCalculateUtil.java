package com.uwjx.function.tank;

public class TankCalculateUtil {

    public static final Double G_VALUE = 9.8;

    public static final Double DEFAULT_ZERO_OFFSET_VALUE = 4.0;

    /**
     * 计算压强值
     */
    public static Double getPressure(double offsetValue, double current, ProbeType probeType) {
        if (offsetValue <= 0) {
            offsetValue = DEFAULT_ZERO_OFFSET_VALUE;
        }
        return (current - offsetValue) / (20 - offsetValue) * probeType.getValue();
    }

    /**
     * 计算液位高度
     */
    public static Double getHighLevel(double pressure, double density) {
        return ((pressure * 100) / (density * G_VALUE)) * 1000;
    }



    public static VpsflModel getHorizontalVpsflModel(HVRTankType hvrTankType ,
                                                     double highLevel,
                                                     double safeFillLevelInput){
        VpsflModel vpsflModel = new VpsflModel();
        double hd = hvrTankType.getHd();
        double hl = hvrTankType.getHl();
        double r = hd/200;
        double h = (hd - highLevel)/100;
        double l = hl / 100;
        double a = (Math.PI * Math.pow((hd/2) , 2))
                - (Math.pow((hd/2) , 2) * Math.acos( (r-h)/Math.pow((hd/2) , 2) ))
                + ((r-h) * Math.sqrt(2 * r * h - h*h));
        double v = a * l;
        double p = v/(Math.PI * Math.pow((hd/2) , 2) * l);
        double sfl = safeFillLevelInput * Math.PI * Math.pow((hd/2) , 2) * l;
        vpsflModel.setVolume(v);
        vpsflModel.setPercentage(p);
        vpsflModel.setSafeFillLevel(sfl);
        return vpsflModel;
    }

    public static VpsflModel getVerticalVpsflModel(HVRTankType hvrTankType ,
                                                     double highLevel,
                                                     double safeFillLevelInput){
        VpsflModel vpsflModel = new VpsflModel();
        double vd = hvrTankType.getVd();
        double vl = hvrTankType.getVl();
        double r = vd/200;
        double l = vl / 100;
        double p = highLevel / l;
        double v = Math.PI * Math.pow((vd/2) , 2) * highLevel / 100;
        double sfl = safeFillLevelInput * Math.PI * Math.pow((vd/2) , 2) * l;
        vpsflModel.setVolume(v);
        vpsflModel.setPercentage(p);
        vpsflModel.setSafeFillLevel(sfl);
        return vpsflModel;
    }

    public static VpsflModel getRectangularVpsflModel(HVRTankType hvrTankType ,
                                                   double highLevel,
                                                   double safeFillLevelInput){
        VpsflModel vpsflModel = new VpsflModel();
        double rw = hvrTankType.getRw();
        double rl = hvrTankType.getRl();
        double rh = hvrTankType.getRh();
        double p = highLevel / rh;
        double v = rl * rw * highLevel / Math.pow(10 ,6);
        double sfl = safeFillLevelInput * rl * rw * rh / Math.pow(10 ,6);
        vpsflModel.setVolume(v);
        vpsflModel.setPercentage(p);
        vpsflModel.setSafeFillLevel(sfl);
        return vpsflModel;
    }

    public static VpsflModel calculate(int tankType ,
                                       int preSavedTankType,
                                       HVRTankType hvrTankType,
                                       double highLevel ,
                                       double safeFillLevelInput){
        VpsflModel vpsflModel = new VpsflModel();
        switch (tankType){
            case TankType.PRE_SAVE:
                return handlePreSaved(preSavedTankType , highLevel , safeFillLevelInput);
            case TankType.DEFORMED_HORIZONTAL:
                return getHorizontalVpsflModel(hvrTankType , highLevel , safeFillLevelInput);
            case TankType.DEFORMED_VERTICAL:
                return getVerticalVpsflModel(hvrTankType , highLevel , safeFillLevelInput);
            case TankType.DEFORMED_RECTANGULAR:
                return getRectangularVpsflModel(hvrTankType , highLevel , safeFillLevelInput);
            case TankType.CUSTOM:
//                handlePreSaved(preSavedTankType , highLevel , safeFillLevelInput);
                break;
            default:

                break;
        }
        return vpsflModel;
    }

    private static VpsflModel handlePreSaved(int preSavedTankType,
                                             double highLevel ,
                                             double safeFillLevelInput){
        VpsflModel vpsflModel = new VpsflModel();
        double v = 0;
        double p = 0;
        double sfl = 0;
        switch (preSavedTankType){
            case TankType.PreSavedTankType.FT55:
                v = highLevel /PreSaveTank.FT55.getHigh() * PreSaveTank.FT55.getVolume();
                p = highLevel / PreSaveTank.FT55.getHigh();
                break;
            case TankType.PreSavedTankType.FT30:
                v = highLevel /PreSaveTank.FT30.getHigh() * PreSaveTank.FT30.getVolume();
                p = highLevel / PreSaveTank.FT30.getHigh();
                break;
            case TankType.PreSavedTankType.FT12:
                v = highLevel /PreSaveTank.FT12.getHigh() * PreSaveTank.FT12.getVolume();
                p = highLevel / PreSaveTank.FT12.getHigh();
                break;
            case TankType.PreSavedTankType.FT68:
                v = highLevel /PreSaveTank.FT68.getHigh() * PreSaveTank.FT68.getVolume();
                p = highLevel / PreSaveTank.FT68.getHigh();
                break;
            case TankType.PreSavedTankType.FT110:
                v = highLevel /PreSaveTank.FT110.getHigh() * PreSaveTank.FT110.getVolume();
                p = highLevel / PreSaveTank.FT110.getHigh();
                break;
            case TankType.PreSavedTankType.PFS13:
                v = highLevel /PreSaveTank.PFS13.getHigh() * PreSaveTank.PFS13.getVolume();
                p = highLevel / PreSaveTank.PFS13.getHigh();
                break;
            case TankType.PreSavedTankType.PFS5:
                v = highLevel /PreSaveTank.PFS5.getHigh() * PreSaveTank.PFS5.getVolume();
                p = highLevel / PreSaveTank.PFS5.getHigh();
                break;
            case TankType.PreSavedTankType.PFS17:
                v = highLevel /PreSaveTank.PFS17.getHigh() * PreSaveTank.PFS17.getVolume();
                p = highLevel / PreSaveTank.PFS17.getHigh();
                break;
            case TankType.PreSavedTankType.PFS23:
                v = highLevel /PreSaveTank.PFS23.getHigh() * PreSaveTank.PFS23.getVolume();
                p = highLevel / PreSaveTank.PFS23.getHigh();
                break;

            default:

                break;
        }
        sfl = safeFillLevelInput * v;
        vpsflModel.setPercentage(p);
        vpsflModel.setVolume(v);
        vpsflModel.setSafeFillLevel(sfl);
        return vpsflModel;
    }




}
