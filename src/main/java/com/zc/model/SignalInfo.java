package com.zc.model;

import java.util.List;

/**
 * @author miracle
 * @data 2017/12/18
 */

public class SignalInfo extends SignalBaseInfo {
    private List<Long> poiIdList;

    public List<Long> getPoiIdList() {
        return poiIdList;
    }

    public void setPoiIdList(List<Long> poiIdList) {
        this.poiIdList = poiIdList;
    }

    @Override
    public String toString() {
        return "SignalInfo{" +
                "poiIdList=" + poiIdList +
                '}';
    }
}
