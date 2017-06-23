package com.lrchao.lrchaoviewssample.edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.lrchao.lrchaoviewssample.R;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/5/27 上午9:35
 */

public class TextInputLayoutActivity extends AppCompatActivity {

//

    EditText mEtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);


        mEtUserName = (EditText) findViewById(R.id.et_username);

        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                if ("123".equals(s.toString().trim())) {
//                    mTextInputLayoutUserName.setErrorEnabled(true);
//                    mTextInputLayoutUserName.setError("aaaa");
//
//                } else {
//                    mTextInputLayoutUserName.setErrorEnabled(false);
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
