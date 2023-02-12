package com.csyfxx.hikivision.original;


import com.sun.jna.Pointer;

public class YFFMSGCallBack implements HCNetSDK.FMSGCallBack {
    //报警信息回调函数
    @Override
    public void invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        FMSGCallBack_V31.alarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
    }
}

