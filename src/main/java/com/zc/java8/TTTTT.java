package com.zc.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by zhangchao on 2017/11/9.
 */
public class TTTTT {

    public static void main(String[] args) {


        Map<Long,List<CommonGoods>> poiGoodsListMap = new HashMap<>();

        List<CommonGoods> switchGoodsList = new ArrayList<>();
        switchGoodsList.add(new CommonGoods(1L));
//        switchGoodsList.add(new CommonGoods(1L));
//        switchGoodsList.add(new CommonGoods(1L));
        switchGoodsList.add(new CommonGoods(2L));

//
//        switchGoodsList.forEach((commonGoods)->{
//            Long poiId = commonGoods.getPoiId();
//            List<CommonGoods> poiGoodsList = new ArrayList<>();
//            if(poiGoodsListMap.containsKey(poiId)){
//                poiGoodsListMap.get(poiId).add(commonGoods);
//            }else{
//                poiGoodsList.add(commonGoods);
//                poiGoodsListMap.put(poiId,poiGoodsList);
//            }
//        });
//
//        System.out.println( poiGoodsListMap );
    }


    private static class CommonGoods {

        private Long poiId;

        public CommonGoods(Long poiId) {
            this.poiId = poiId;
        }

        public Long getPoiId() {
            return poiId;
        }

        public void setPoiId(Long poiId) {
            this.poiId = poiId;
        }
    }

}
