/**
 *
 */
package pers.domain;


import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ningquan
 */
public class ResponseResult<T> {

    private int state;

    private String desc;

    private T value;

    public ResponseResult() {
        this.state = 0;
        this.desc = "success";
    }
    
    public ResponseResult(T value) {
    	this();
    	this.value = value;
    }

    public ResponseResult(ErrorState errorState) {
        this.state = errorState.getState();
        this.desc = errorState.getDesc();
    }

    public ResponseResult(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public ResponseResult(int state, String desc, T value) {
        this.state = state;
        this.desc = desc;
        this.value = value;
    }

    public static <T> ResponseResult<T> replySuccess() {
        return replyState(ErrorState.Success);
    }

    public static <T> ResponseResult<T> replySuccess(T value) {
        return replyState(ErrorState.Success, null, value);
    }

    public static <T> ResponseResult<T> replySuccess(String desc, T value) {
        return replyState(ErrorState.Success, desc, value);
    }

    public static <T> ResponseResult<T> replyFail() {
        return replyState(ErrorState.Failure);
    }

    public static <T> ResponseResult<T> replyFail(String desc) {
        return replyState(ErrorState.Failure, desc, null);
    }
    
    public static <T> ResponseResult<T> replyFail(Integer state, String desc) {
        return getInstance(state, desc, null);
    }


    public static <T> ResponseResult<T> replySuccessDesc(String desc) {
        return replyState(ErrorState.Success, desc);
    }

    public static <T> ResponseResult<T> replyState(ErrorState errorState, String desc) {
        return replyState(errorState, desc, null);
    }

    public static <T> ResponseResult<T> replyState(ErrorState errorState) {
        return replyState(errorState, errorState.getDesc(), null);
    }

    public static <T> ResponseResult<T> replyState(ErrorState errorState, String desc, T value) {
        if (StringUtils.isEmpty(desc)) {
            return getInstance(errorState.getState(), desc, value);
        }
        return getInstance(errorState.getState(), errorState.getDesc(), value);
    }

    public static <T> ResponseResult<T> getInstance(Integer state, String desc, T value) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setState(state);
        responseResult.setDesc(desc);
        responseResult.setValue(value);
        return responseResult;
    }
    
    public boolean success() {
    	return (getState() == ErrorState.Success.getState());
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTotal(long total) {
        getMapValue().put("total", total);
    }

    public void setPageSize(int pageSize) {
        getMapValue().put("pageSize", pageSize);
    }

    public void setPageNum(int pageNum) {
        getMapValue().put("pageNum", pageNum);
    }

    public T getValue() {
        return value;
    }

    /**
     * setValue 和setKeyValue只能使用其一
     *
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * setValue 和setKeyValue只能使用其一
     *
     * @param key
     * @param value
     */
    public void setKeyValue(String key, Object value) {
        getMapValue().put(key, value);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMapValue() {
        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    value = (T) new HashMap<String, Object>();
                }
                return (Map<String, Object>) value;
            }
        }
        return (Map<String, Object>) value;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", desc='" + desc + '\'' +
                ", value=" + value +
                '}';
    }
}
