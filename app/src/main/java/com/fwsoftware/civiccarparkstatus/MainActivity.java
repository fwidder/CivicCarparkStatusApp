package com.fwsoftware.civiccarparkstatus;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private WebView table;
    private ToggleButton simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPermissions();

        table  = findViewById(R.id.Table);
        simple = findViewById(R.id.Simple);

        table.loadUrl(Config.getCarParkUrl(simple.isChecked()));

        simple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.table.loadUrl(Config.getCarParkUrl(simple.isChecked()));
            }
        });
        handler.postDelayed(WebViewReload, 5000);
    }

    private RequestPermissionHandler mRequestPermissionHandler;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(mRequestPermissionHandler==null)
            mRequestPermissionHandler=new RequestPermissionHandler();
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }

    private void loadPermissions() {
        if(mRequestPermissionHandler==null)
            mRequestPermissionHandler=new RequestPermissionHandler();
        mRequestPermissionHandler.requestPermission(this, Config.PERMISSIONS, 12345, new RequestPermissionHandler.RequestPermissionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "request permission failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    Handler handler = new Handler();
    private Runnable WebViewReload = new Runnable() {
        @Override
        public void run() {
            MainActivity.this.table.loadUrl(Config.getCarParkUrl(simple.isChecked()));
            handler.postDelayed(this, 10000);
            Log.d("Webview","Reloaded");
        }
    };
}
