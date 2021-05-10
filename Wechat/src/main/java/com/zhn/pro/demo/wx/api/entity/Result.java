package com.zhn.pro.demo.wx.api.entity;

/**
 * @author ZhouHN
 * @desc 微信接口调用返回错误码说明，实体类
 * @date 14:37 2019/10/31 0031
 */
public class Result {

    /**
     * 错误码	说明
     * -1	系统繁忙
     * 45157	标签名非法，请注意不能和其他标签重名
     * 45158	标签名长度超过30个字节
     * 45056	创建的标签数过多，请注意不能超过100个
     * 45058	不能修改0/1/2这三个系统默认保留的标签
     * 45057	该标签下粉丝数超过10w，不允许直接删除
     * 40003	传入非法的openid
     * 45159	非法的tag_id
     * 40032	每次传入的openid列表个数不能超过50个
     * 45159	非法的标签
     * 45059	有粉丝身上的标签数已经超过限制，即超过20个
     * 49003	传入的openid不属于此AppID
     */

    private Integer errcode;
    private String errmsg;
    private Integer msgid;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", msgid=" + msgid +
                '}';
    }
}
