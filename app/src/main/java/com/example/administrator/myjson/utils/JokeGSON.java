package com.example.administrator.myjson.utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class JokeGSON {


    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"烧烤摊上,一人小贩和烧烤师傅商量上他的货,只听烧烤师傅大着嗓门喊:\u201c我正在考虑,等考虑好了就上!\u201d一边的顾客听到了,大声到:\u201c老板,这里还有烤驴?我们也来一份!\u201d","hashId":"d30f7dd825133e5c5fd0e2b2a26c1183","unixtime":1470294832,"updatetime":"2016-08-04 15:13:52"},{"content":"老师说:有些民族吃饭是用手抓的。比如蒙古族。小明:那他们吃火锅吗?老师:你出去!","hashId":"f04ddc8fce874a3ab3258469290d1952","unixtime":1470294832,"updatetime":"2016-08-04 15:13:52"},{"content":"在星巴克,我大声问服务员:\u201c服务员,能换首有品位的歌么?什么小苹果的歌都太聒噪了,我想安安静静地写稿子。\u201d于是服务员走过来,帮我把耳机拿下来了。","hashId":"4c30d41a65e463782e41a16925d10f7a","unixtime":1470291230,"updatetime":"2016-08-04 14:13:50"},{"content":"\u201c朝廷下了公文,明日午时三刻在菜市口开刀问斩。我给你带来了酒菜,来来来,今晚这是你最后一顿饭,吃完了好上路。\u201d听完,我脑子嗡的一声,眼泪紧接着就掉了下来:\u201c明天早晨不管饭啊!\u201d","hashId":"c1843a7fb9d1a202425af92f72752bd6","unixtime":1470291230,"updatetime":"2016-08-04 14:13:50"},{"content":"饭店里，一顾客等得不耐烦了，便走去问跑堂的：\u201c我还得等多久啊？\u201d　　跑堂的和颜悦色地答道：\u201c要等另一位顾客点了和你同样的菜。\u201d　　\u201c为什么？\u201d顾客叫了起来 \u201c因为你只要了半只鸡。\u201d","hashId":"a03708f99270ae6faed4db2900b765d0","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"},{"content":"\u201c经理，你们这儿的厕所苍蝇太多，简直进不去！\u201d　　\u201c下次您别一大早上厕所。\u201d　　\u201c那该什么时候去？\u201d　　\u201c最好是中午十二时，那时候苍蝇都到餐厅里去了。\u201d","hashId":"bb90a22847914bba39ee14835bb220c4","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"},{"content":"顾客：服务员，我要的焖全鸡，怎么少了个鸡大腿？　　服务员：人还有残疾人呢，难道鸡就没有残疾的？","hashId":"1b4384e22277bb598515486a949a1e9d","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"},{"content":"我脸上长了青春美丽疙瘩豆。　　某日坐公交车，手拉拉环，随车晃动。一小P孩。拉着我的衣角：\u201c哥哥，哥哥，你八宝粥洒在脸上了。\u201d","hashId":"1dc3be13437106beb34d2c9cfd083c31","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"},{"content":"两个歹徒埋伏着，打算暗算某人，但老是不见那人的踪影。其中一个着急他说：\u201c怎么搞的？还不见他来，但愿他不要发生意外！\u201d","hashId":"f2dace27ba08967bc551b029d2c639e1","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"},{"content":"历史老师问：\u201c三国演义中董卓骑的是什么马？\u201d　　讲台下无人作答。　　老师又提示道：\u201c再想想，这马吕布也骑过。\u201d　　这时有人回答：\u201c貂禅。\u201d　　老师勃然大怒道：\u201c混蛋！我问的是白天的！\u201d","hashId":"1cf70661ac104de4084e31c5af5d2b26","unixtime":1470276832,"updatetime":"2016-08-04 10:13:52"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 烧烤摊上,一人小贩和烧烤师傅商量上他的货,只听烧烤师傅大着嗓门喊:“我正在考虑,等考虑好了就上!”一边的顾客听到了,大声到:“老板,这里还有烤驴?我们也来一份!”
         * hashId : d30f7dd825133e5c5fd0e2b2a26c1183
         * unixtime : 1470294832
         * updatetime : 2016-08-04 15:13:52
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
