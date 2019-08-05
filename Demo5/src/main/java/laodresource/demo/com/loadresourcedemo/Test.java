package laodresource.demo.com.loadresourcedemo;

import java.util.List;

/**
 * @author lzl
 * @ describe
 * @ time 2019/7/16 15:12
 */
public class Test {

    private List<NewUserInfoBean> newUserInfo;

    public List<NewUserInfoBean> getNewUserInfo() {
        return newUserInfo;
    }

    public void setNewUserInfo(List<NewUserInfoBean> newUserInfo) {
        this.newUserInfo = newUserInfo;
    }

    public static class NewUserInfoBean {
        /**
         * date : 2018-01-01
         * dailyValue : [{"name":"xxx","value":0}]
         * hourValue :
         * value : 0
         */

        private String date;
        private String hourValue;
        private int value;
        private List<DailyValueBean> dailyValue;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHourValue() {
            return hourValue;
        }

        public void setHourValue(String hourValue) {
            this.hourValue = hourValue;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public List<DailyValueBean> getDailyValue() {
            return dailyValue;
        }

        public void setDailyValue(List<DailyValueBean> dailyValue) {
            this.dailyValue = dailyValue;
        }

        public static class DailyValueBean {
            /**
             * name : xxx
             * value : 0
             */

            private String name;
            private int value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
