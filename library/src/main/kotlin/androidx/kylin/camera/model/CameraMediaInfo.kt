package androidx.kylin.camera.model

import android.location.Location
import java.io.File

/**
 * 相机媒体信息，照片元数据
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
data class CameraMediaInfo(
    /** 文件名 */
    val filename: String,
    /** 文件保存路径 */
    val file: File,
    /** 文件大小 */
    val fileSize: Long,
    /** 照片宽度 */
    val width: Int,
    /** 照片高度 */
    val height: Int,
    /** 拍照地理位置信息 */
    val location: Location? = null,
    /** 设备传感器信息 */
    val sensorInfo: DeviceSensorInfo? = null
)