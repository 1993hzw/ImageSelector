package cn.hzw.imageclipperdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cn.hzw.imageselector.ImageSelectorActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_open).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_open) {
            ImageSelectorActivity.startActivityForResult(100, this, null, true, 3);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> list = data.getExtras().getStringArrayList(ImageSelectorActivity.KEY_PATH_LIST);
                Toast.makeText(getApplicationContext(), "" + list, Toast.LENGTH_LONG).show();
            }
        }
    }
}
