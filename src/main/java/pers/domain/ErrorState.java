package pers.domain;

import java.util.HashMap;
import java.util.Map;

public enum ErrorState {
    Success(0, "成功"),
    Failure(1, "失败");



    private int state;
    private String desc;

    private ErrorState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ErrorState getByState(int state) {
        ErrorState array[] = ErrorState.values();
        int start = 0, end = array.length - 1;
        int midd;

        while (start <= end) {
            midd = (start + end) / 2;
            int dt = array[midd].state;
            if (state > dt) {
                start = midd + 1;
            } else if (state < dt) {
                end = midd - 1;
            } else {
                return array[midd];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"state\":").append(state);
        builder.append(",\"desc\":\"").append(desc);
        builder.append("\"}");
        return builder.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", state);
        map.put("desc", desc);
        return map;
    }
}
