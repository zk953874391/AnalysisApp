package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/16.
 */
@Service
class MeipaiHandler extends BaseHandler {

    boolean result = false;

    protected boolean handle(ActivityRequest request) {
        request?.activity?.any {
            activity ->
                activity?.intentFilter?.actions?.any {
                    def action ->
                        action?.name?.any {
                            if (it == "com.meitu.mp.sdk.action.ACTION_SDK_RESP_ACTIVITY") {
                                def sdk = new SDK("美拍sdk");
                                AppInfo.getInstance().addSDK(sdk);
                                result = true;
                                return true;
                            }
                        }
                }
        }

        return result
    }
}
