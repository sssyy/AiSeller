/**
 *
 */
package pers.domain;

/**
 * @author chengkaijing
 */
public class ResponseResultUtils {

    public static boolean isSuccess(ResponseResult responseResult) {
        return responseResult != null && responseResult.getState() == ErrorState.Success.getState();
    }

    public static boolean isFail(ResponseResult responseResult) {
        return responseResult == null || responseResult.getState() != ErrorState.Success.getState();
    }

    public static String getResultDesc(ResponseResult responseResult,String defaultStr) {
        if (responseResult == null){
            return defaultStr;
        }
        return responseResult.getDesc();
    }
    

}
