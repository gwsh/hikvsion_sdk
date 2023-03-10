package com.csyfxx.alarmdeo;

import com.csyfxx.hikivision.api.HikVision;
import com.csyfxx.hikivision.original.FMSGCallBack_V31;
import com.csyfxx.hikivision.original.YFFMSGCallBack;
import com.csyfxx.hikivision.utils.SdkActionResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class HikivisionSdkApplicationTests {

    @Test
    void contextLoads() {
        // 1.初始化
        HikVision.NET_DVR_Init();

        // 2.设置回调函数
        SdkActionResult callBack_v31 = HikVision.NET_DVR_SetDVRMessageCallBack_V31(new FMSGCallBack_V31());

        // 3. 注册登录
        // 需提供设备ip, 设备端口、设备登录账号、设备登录密码
        SdkActionResult sdkActionResult = HikVision.NET_DVR_LOGIN_v40("192.168.0.118", (short) 8000, "admin", "123456");

        int userId = sdkActionResult.getRetValue();

        // 4. 报警布防
        SdkActionResult setupAlarmChan_v41 = HikVision.NET_DVR_SetupAlarmChan_V41("1", userId);


        // 报警监听
        SdkActionResult listen_v30 = HikVision.NET_DVR_StartListen_V30("192.168.0.118", (short) 8000, new YFFMSGCallBack());


        while (true) {
            //这里加入控制台输入控制，是为了保持连接状态，当输入Y表示布防结束
            System.out.print("请选择是否撤出布防(Y/N)：");
            Scanner input = new Scanner(System.in);
            String str = input.next();
            if (str.equals("Y")) {
                break;
            }
        }

        // 停止 报警监听
        HikVision.NET_DVR_StopListen_V30();

        // 停止 报警布防
        HikVision.NET_DVR_CloseAlarmChan("1");
        // 注销用户
        HikVision.NET_DVR_Logout(userId);

        // 释放SDK资源
        HikVision.NET_DVR_Cleanup();
    }


}
