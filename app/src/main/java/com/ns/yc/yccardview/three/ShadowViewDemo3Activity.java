package com.ns.yc.yccardview.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ns.yc.yccardview.CommentDialog;
import com.ns.yc.yccardview.R;

public class ShadowViewDemo3Activity extends AppCompatActivity {
    TextView tv_show;
    private CommentDialog commentDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_view_demo3);

        tv_show = findViewById(R.id.tv_show);
        commentDialog = new CommentDialog(this);
        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentDialog == null){
                    commentDialog = new CommentDialog(ShadowViewDemo3Activity.this);
                } else {
                    commentDialog.show();
                }
            }
        });
    }

}
