package com.zhn.pro.demo.wx.session.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * @author ZhouHN
 * @desc 微信对话消息，第三方服务器回复消息的消息实体
 * @date 16:47 2019/10/25 0025
 */
@XStreamAlias("xml")
public class OutputMessage extends Message {

    /* --------回复文本消息  MsgType : text ------------------------- */
    private String Content;         // 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

    /* --------回复图片消息  MsgType : image ------------------------- */
    private OutMsgImage Image;

    /* --------回复语言消息  MsgType : voice ------------------------- */
    private OutMsgVoice Voice;

    /* --------回复视频消息  MsgType : video ------------------------- */
    private OutMsgVideo Video;

    /* --------回复音乐消息  MsgType : music ------------------------- */
    private OutMsgMusic Music;

    /* --------回复图文消息  MsgType : news ------------------------- */
    private List<OutMsgArticle> Articles;       // 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
    // 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；
    // 其余场景最多可回复8条图文消息
    private Integer ArticleCount;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public OutMsgImage getImage() {
        return Image;
    }

    public void setImage(OutMsgImage image) {
        Image = image;
    }

    public OutMsgVoice getVoice() {
        return Voice;
    }

    public void setVoice(OutMsgVoice voice) {
        Voice = voice;
    }

    public OutMsgVideo getVideo() {
        return Video;
    }

    public void setVideo(OutMsgVideo video) {
        Video = video;
    }

    public OutMsgMusic getMusic() {
        return Music;
    }

    public void setMusic(OutMsgMusic music) {
        Music = music;
    }

    public List<OutMsgArticle> getArticles() {
        return Articles;
    }

    public void setArticles(List<OutMsgArticle> articles) {
        Articles = articles;
    }

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    @Override
    public String toString() {
        return "OutputMessage{" +
                "Content='" + Content + '\'' +
                ", Image=" + Image +
                ", Voice=" + Voice +
                ", Video=" + Video +
                ", Music=" + Music +
                ", Articles=" + Articles +
                ", ArticleCount=" + ArticleCount +
                "} " + super.toString();
    }
}
