package jp.tomorrowkey.android.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(self)
                        .setTitle("Hello, Android!")
                        .setMessage("This is an example app.")
                        .setPositiveButton("OK", null)
                        .create();
                dialog.setOwnerActivity(self);
                dialog.show();
            }
        });
    }

}
