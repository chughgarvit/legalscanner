package com.mxn.soul.flowingdrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class CommonBaseActivity extends AppCompatActivity {

    String category;
    WebView myBrowser;
    TextView why;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_base);

        category = getIntent().getExtras().getString("category");
        why = (TextView) findViewById(R.id.why);
        myBrowser = (WebView)findViewById(R.id.webview);

        switch (category){
            case "why":
                myBrowser.setVisibility(View.GONE);
                break;
            case "vul":
                why.setVisibility(View.GONE);
                myBrowser.loadUrl("https://legaltemplates.net/legal-documents-forms/");
                break;
            case "list":
                why.setVisibility(View.GONE);
                myBrowser.loadUrl("https://legaltemplates.net/legal-documents-forms/");
                break;
        }

    }
}
