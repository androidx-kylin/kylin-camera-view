package androidx.kylin.camera.event

import androidx.kylin.camera.model.ErrorCode

/**
 * Camera UseCase Event
 * @author rae
 * @copyright https://github.com/raedev
 */
sealed class UseCaseEvent {

    /**
     * UseCase Error Event
     */
    class UseCaseError(val message: String, val code: ErrorCode? = null) : UseCaseEvent()


    class Capture(val uri: String) : UseCaseEvent()


    class RecordVideo(val uri: String) : UseCaseEvent()

}

