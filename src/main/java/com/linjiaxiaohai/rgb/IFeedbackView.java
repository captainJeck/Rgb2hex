package com.linjiaxiaohai.rgb;

import com.umeng.fb.model.Reply;

import java.util.List;

/**
 * Created by Meng on 16/5/8.
 */
public interface IFeedbackView {

    void loading(boolean isLoad);

    void onSendUserReply(List<Reply> list);
}
