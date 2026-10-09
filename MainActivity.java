package com.example.menuwebview;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String HOME_URL = "https://www.google.com";

    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvStudentInfo;
    private Button btnPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        tvStudentInfo = findViewById(R.id.tvStudentInfo);
        btnPopup = findViewById(R.id.btnPopup);

        // Student details (edit in res/values/strings.xml)
        tvStudentInfo.setText(getString(R.string.student_name) + " | " + getString(R.string.student_usn));

        setupWebView();

        // Context menu: long-press the student info bar
        registerForContextMenu(tvStudentInfo);

        // Popup menu: tap the button
        btnPopup.setOnClickListener(this::showPopupMenu);

        // Back button navigates WebView history first
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });

        webView.loadUrl(HOME_URL);
    }

    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Keeps navigation inside the app instead of opening the browser
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                progressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }
        });
    }

    // ---------- 1. OPTIONS MENU (top-right three dots) ----------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            webView.loadUrl(HOME_URL);
        } else if (id == R.id.menu_wikipedia) {
            webView.loadUrl("https://www.wikipedia.org");
        } else if (id == R.id.menu_android) {
            webView.loadUrl("https://developer.android.com");
        } else if (id == R.id.menu_refresh) {
            webView.reload();
        } else if (id == R.id.menu_about) {
            showAboutDialog();
        } else if (id == R.id.menu_exit) {
            finish();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // ---------- 2. CONTEXT MENU (long-press the student info bar) ----------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ctx_reload) {
            webView.reload();
            Toast.makeText(this, "Reloading page...", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ctx_title) {
            Toast.makeText(this, "Title: " + webView.getTitle(), Toast.LENGTH_LONG).show();
        } else if (id == R.id.ctx_clear) {
            webView.clearHistory();
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show();
        } else {
            return super.onContextItemSelected(item);
        }
        return true;
    }

    // ---------- 3. POPUP MENU (button) ----------
    private void showPopupMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.pop_back) {
                if (webView.canGoBack()) webView.goBack();
                else Toast.makeText(this, "No previous page", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.pop_forward) {
                if (webView.canGoForward()) webView.goForward();
                else Toast.makeText(this, "No next page", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.pop_url) {
                Toast.makeText(this, "URL: " + webView.getUrl(), Toast.LENGTH_LONG).show();
            }
            return true;
        });
        popup.show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("Mini Browser\nExperiment 8: Menus and WebView\n\n"
                        + getString(R.string.student_name) + "\n" + getString(R.string.student_usn))
                .setPositiveButton("OK", null)
                .show();
    }
}
