package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class BalanceBean {

    /**
     * status : 200
     * body : [{"created":"2018-05-31T03:43:33.188Z","countn":"-￥3636","content":"提现服务"},{"created":"2018-05-31T03:38:35.205Z","countn":"+￥1212","content":"充值服务"},{"created":"2018-05-29T06:19:46.197Z","countn":"-￥5555","content":"提现服务"},{"created":"2018-05-29T03:17:49.477Z","countn":"+￥10000","content":"充值服务"},{"created":"2018-05-29T03:16:35.924Z","countn":"-￥10000","content":"充值服务"},{"created":"2018-05-21T09:12:37.011Z","countn":"-￥2000","content":"支付扣款2000元"},{"created":"2018-05-21T09:01:39.038Z","countn":"-￥2000","content":"支付扣款2000元"},{"created":"2018-05-21T08:20:03.093Z","countn":"-￥2000","content":"支付扣款2000元"},{"created":"2018-04-20T07:22:18.827Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:41:48.424Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:38:49.493Z","countn":"+￥6000","content":"充值服务"},{"created":"2018-04-16T10:38:41.583Z","countn":"+￥300","content":"充值服务"},{"created":"2018-04-16T10:38:34.369Z","countn":"+￥1002","content":"充值服务"},{"created":"2018-04-16T10:15:36.585Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:15:35.991Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:15:22.755Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:15:14.847Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:11:11.809Z","countn":"+￥5000","content":"充值服务"},{"created":"2018-04-16T10:11:03.392Z","countn":"+￥5000","content":"充值服务"}]
     */

    private int status;
    private List<BodyBean> body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * created : 2018-05-31T03:43:33.188Z
         * countn : -￥3636
         * content : 提现服务
         */

        private String created;
        private String countn;
        private String content;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getCountn() {
            return countn;
        }

        public void setCountn(String countn) {
            this.countn = countn;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
