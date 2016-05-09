package com.linjiaxiaohai.rgb;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.fb.model.Reply;

import java.util.List;


/**
 *
 */
public class FeedbackActivity extends BaseActivity implements IFeedbackView {

    private EditText feedbackEdit;
    private EditText contactsEdit;

    private FeedbackPresenter feedbackPresenter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_feedback);

        feedbackEdit = (EditText) findViewById(R.id.feedback);
        contactsEdit = (EditText) findViewById(R.id.contacts);

        feedbackPresenter = new FeedbackPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_feedback:
                feedback();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void feedback() {
        if (validate()) {
            hideInputMethod();
            feedbackPresenter.feedback(feedbackEdit.getText().toString(), contactsEdit.getText().toString());
        } else {
            Toast.makeText(this, "请填写反馈信息", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(feedbackEdit.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public void loading(boolean isLoad) {
        if (isLoad) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("反馈中...");
            }
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onSendUserReply(List<Reply> list) {
        Toast.makeText(this, "反馈成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
