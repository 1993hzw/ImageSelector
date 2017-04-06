package cn.hzw.imageclipperdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import cn.forward.androids.views.ScrollPickerView;
import cn.forward.androids.views.StringScrollPicker;
import cn.hzw.imageselector.ImageSelectorActivity;
import cn.hzw.imageselector.ImageSelectorAdapter;

public class MainActivity extends Activity implements View.OnClickListener {

    private GridView mGridView;
    private ArrayList<String> mSelectedList = new ArrayList<>();
    private ImageSelectorAdapter mAdapter;

    private StringScrollPicker mPicker;

    private ToggleButton mSelectMode;

    private int mMaxCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_open).setOnClickListener(this);

        mGridView = (GridView) findViewById(R.id.grid_view);
        mAdapter = new ImageSelectorAdapter(this, mSelectedList);
        mGridView.setAdapter(mAdapter);

        mPicker = (StringScrollPicker) findViewById(R.id.select_count);
        mPicker.setData(new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        mPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                mMaxCount = position + 1;
            }
        });
        mPicker.setSelectedPosition(mMaxCount - 1);

        mSelectMode = (ToggleButton) findViewById(R.id.select_mode);
        mSelectMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPicker.setVisibility(View.VISIBLE);
                } else {
                    mPicker.setVisibility(View.GONE);
                }
            }
        });
        mSelectMode.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_open) {
            ImageSelectorActivity.startActivityForResult(100, this, null, mSelectMode.isChecked(), mMaxCount);
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
                mSelectedList = data.getExtras().getStringArrayList(ImageSelectorActivity.KEY_PATH_LIST);
                mAdapter.refreshPathList(mSelectedList);
//                Toast.makeText(getApplicationContext(), "" + mSelectedList, Toast.LENGTH_LONG).show();
            }
        }
    }
}
