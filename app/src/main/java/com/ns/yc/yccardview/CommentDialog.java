package com.ns.yc.yccardview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author: zzh
 * data : 2020/11/10
 * description：评论弹框
 */
public class CommentDialog extends Dialog implements View.OnClickListener {

    private EditText etContent;
    private TextView tvPublishComment;
    private Context context;
    private OnCommitListener listener;

    public CommentDialog(Context context) {
        this(context, R.style.organaffairs_inputDialog);
        this.context = context;
    }

    public CommentDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        setContentView(R.layout.organaffairs_news_comment_dialog);
        initView();
        initListener();
    }

    // 初始化view
    private void initView(){
        setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.0f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        etContent = findViewById(R.id.et_content);
        etContent.requestFocus();
        tvPublishComment = findViewById(R.id.tv_publish_comment);
    }

    // 初始化监听
    private void initListener() {
        //设置显示对话框时的返回键的监听
        this.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    CommentDialog.this.cancel();
                return false;
            }
        });
        //设置EditText内容改变的监听
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    tvPublishComment.setBackground(context.getResources().getDrawable(R.drawable.organaffairs_publish_bt_bg_normal));
                    tvPublishComment.setClickable(true);
                } else {
                    tvPublishComment.setBackground(context.getResources().getDrawable(R.drawable.organaffairs_publish_bt_bg_abnormal));
                    tvPublishComment.setClickable(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        tvPublishComment.setOnClickListener(this);// 发表
    }

    public void setOnCommitListener(OnCommitListener listener) {
        this.listener = listener;
    }

    public interface OnCommitListener {

        void onCommit(EditText et, View v);//发表

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_publish_comment){
            if (null != listener) {
                listener.onCommit(etContent, v);
            }
        }
    }

}
