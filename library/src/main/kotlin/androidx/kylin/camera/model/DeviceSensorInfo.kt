package androidx.kylin.camera.model

/**
 * 设备传感器信息
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
data class DeviceSensorInfo(

    /**
     * [0,360]方位角<br>
     * 0 = 北，90 = 东，180 = 南，270 = 西
     */
    val azimuth: Int,

    /**
     * 俯仰角[-180,180] <br>
     * 手机朝上时，取值变化为[0,90,180] <br>
     * 手机朝下时，取值变化为[0,-90,180]
     */
    val pitch: Int,

    /**
     * 侧倾角[-90,90] <br>
     * 手机朝上时，从左到右取值变化为[0,90,0]，从右到左取值变化为[0,-90,0] <br>
     * 手机朝下时，从左到右取值变化为[0,-90,0]，从右到左取值变化为[0,90,0]
     */
    val roll: Int

)