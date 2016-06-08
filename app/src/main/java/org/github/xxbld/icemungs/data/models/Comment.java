package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 评论
 */
public class Comment extends BmobObject {
    //内容
    private String commentContent;

    private Student student;
    private ITalk italk;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ITalk getItalk() {
        return italk;
    }

    public void setItalk(ITalk italk) {
        this.italk = italk;
    }
}
