package com.example.vrstyproject.bookread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vrstyproject.R;

import java.net.URLEncoder;

public class ReadBookActivity extends AppCompatActivity {
    WebView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        pdfView=findViewById(R.id.pdfView);

        String fileName=getIntent().getStringExtra("filename");
        String fileurl=getIntent().getStringExtra("fileUrl");

        ProgressDialog pg=new ProgressDialog(this);
        pg.setTitle(fileName);
        pg.setMessage("Opening........!!!");

       pdfView.setWebViewClient(new WebViewClient()
       {
           @Override
           public void onPageStarted(WebView view, String url, Bitmap favicon) {
               super.onPageStarted(view, url, favicon);
               pg.show();
           }

           @Override
           public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);
               pg.dismiss();
           }
       });

       String url="";

       try {
           url= URLEncoder.encode(fileurl,"UTF-8");
       }catch(Exception e)
       {

       }
        //pdfView.getSettings().setJavaScriptEnabled(true);
     //   pdfView.getSettings().setPl(true);
      // pdfView.loadUrl("http://docs.google.com/gview?embedded=true&url="+url);

        pdfView.getSettings().setJavaScriptEnabled(true);
        pdfView.getSettings().setLoadWithOverviewMode(true);
        pdfView.getSettings().setUseWideViewPort(true);
       pdfView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);

       // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
       // startActivity(browserIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}