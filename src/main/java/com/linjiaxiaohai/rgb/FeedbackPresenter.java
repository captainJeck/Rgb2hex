package com.linjiaxiaohai.rgb;

import android.content.Context;
import android.util.Log;

import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理反馈
 * Created by Meng on 16/5/8.
 */
public class FeedbackPresenter {

    private IFeedbackView view;
    private FeedbackAgent agent;
    private Conversation conversation;
    private Context context;

    public FeedbackPresenter(IFeedbackView view) {
        this.view = view;
        this.context = (Context) view;
        agent = new FeedbackAgent(context);
        conversation = agent.getDefaultConversation();
    }

    public void feedback(String feedback, String contacts) {
        view.loading(true);
        agent.setUserInfo(initUserInfo(contacts));
        conversation.addUserReply(feedback);
        conversation.sync(new SyncListener() {
            @Override
            public void onReceiveDevReply(List<Reply> list) {
                Log.d("FeedbackActivity", "list:" + list);
                view.loading(false);
            }

            @Override
            public void onSendUserReply(List<Reply> list) {
                Log.d("FeedbackActivity", "list:" + list);
                view.loading(false);
                view.onSendUserReply(list);
            }
        });
    }

    private UserInfo initUserInfo(String contacts) {
        Map<String, String> map = new HashMap<>();
        map.put("contacts", contacts);
        UserInfo userInfo = new UserInfo();
        userInfo.setContact(map);
        return userInfo;
    }
}
