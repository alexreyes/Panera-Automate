package com.amrapps.paneraautomate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Boolean toastCheck = true;
    String name = "Not";
    String lastName = "Available";
    WebView webview;
    String password = "asdf123";
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.amrapps.foodapp", MODE_PRIVATE);

        name = prefs.getString("name", "");
        lastName = prefs.getString("lastName", "");
        password = prefs.getString("password", "");

        // Check for null values and set default if empty
        if (name == "") {
            name = "Johnny";
        }

        if (lastName == "") {
            lastName = "Appleseed";
        }

        if (password == "") {
            password = "Asdf123";
        }

        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();

        Random random = new Random();
        int emailNum = random.nextInt(550) + 1;
        final String email = name.charAt(0) + lastName + emailNum + "@gmail.com";

        webview.loadUrl("https://www.panerabread.com/en-us/mypanera/registration-page.html");

        webSettings.setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);

        // Fill out form
        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                WebView myWebView = (WebView) findViewById(R.id.webview);

                //hide loading image
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                //show WebView
                findViewById(R.id.webview).setVisibility(View.VISIBLE);

                myWebView.loadUrl("javascript:document.getElementById('join_first_name').value='" + name + "';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('join_last_name').value='" + lastName + "';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('join_email').value='" + email + "';void(0);");
                myWebView.loadUrl("javascript:document.getElementById('join_confirm_email').value='" + email + "';void(0);");
                myWebView.loadUrl("javascript:document.getElementById('join_password').value='" + password + "';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('join_confirm_password').value='" + password + "';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('phone_number_a').value='231';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('phone_number_b').value='123';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('phone_number_c').value='2310';void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('join_i_agree').click();void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('join_card_not_available').click();void(0); ");
                myWebView.loadUrl("javascript:document.getElementById('#join-now-primary'); ");

                myWebView.pageDown(true);
                // Make sure a toast is only shown once.
                while (toastCheck) {
                    Toast.makeText(MainActivity.this, "Click the \"join\" button.", Toast.LENGTH_SHORT).show();
                    toastCheck = false;
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("https://delivery1.panerabread.com/#!/orderProcess/");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstRun", true)) {
            Intent myIntent = new Intent(this, firstRun.class);
            startActivity(myIntent);

            prefs.edit().putBoolean("firstRun", false).commit();

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, settings.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}