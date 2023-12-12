package androidx.kylin.camera.model

import androidx.camera.core.AspectRatio

/**
 * 相机参数
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
data class CameraOptions(
    /** 相机镜头，默认为后置镜头。 */
    val facing: CameraLensFacing = CameraLensFacing.BACK,
    /** 相机比例，默认16:9 */
    val aspectRatio: Int = AspectRatio.RATIO_16_9,

    /** 照片是否写入Exif信息，默认不写入。 */
    val writeExifInfo: Boolean = false,
)