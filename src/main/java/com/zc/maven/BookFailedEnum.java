package com.zc.maven;

/**
 * Created by xuxiaoying on 2018/3/14.
 */
public enum BookFailedEnum {

        BOOK_NO_ROOM(2001,"预订无房"),
        VERIFY_NO_ROOM(2002,"验价无房"),
        VERIFY_FAILED(2003,"验价失败"),
        BOOK_NO_RETURN(2004,"预订无返回"),
        VERIFY_NO_RETURN(2005,"验价无返回"),

        ;





        private int id;
        private String desc;

        BookFailedEnum(int id, String desc){
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
}
