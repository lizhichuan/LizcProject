package com.lizc.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lizc.app.activity.JBDocumentActivity;
import com.lizc.app.activity.NewContentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_new_content)
    TextView tvNewContent;
    @BindView(R.id.tv_document)
    TextView tvDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_new_content, R.id.tv_document})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_content:
                startActivity(new Intent(MainActivity.this, NewContentActivity.class));
                break;
            case R.id.tv_document:
                startActivity(new Intent(MainActivity.this, JBDocumentActivity.class));
                break;
        }
    }
}
