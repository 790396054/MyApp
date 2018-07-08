package com.github.gmm.designsamaple.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.base.BaseActivity;
import com.wuxiaolong.androidutils.library.LogUtil;

/**
 * @author gmm
 * @date 2018/7/7 21
 * @email miaomiaogong@92gmail.com
 */
public class LoginActivity extends BaseActivity {
    private AutoCompleteTextView emailText;
    private EditText passwordText;
    private TextInputLayout emailTextInput, passwordTextInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initToolBar("登录");
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        emailTextInput = findViewById(R.id.emailTextInput);
        passwordTextInput = findViewById(R.id.pwdTextInput);
        passwordText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == R.id.login || actionId == EditorInfo.IME_ACTION_DONE) {
                attemptLogin();
                return true;
            }
            return false;
        });
        Button login = findViewById(R.id.login);
        login.setOnClickListener(v->{
            attemptLogin();
        });
    }

    private void attemptLogin() {
        LogUtil.d("attemptLogin");
        // Reset errors
        emailTextInput.setErrorEnabled(false);
        passwordTextInput.setErrorEnabled(false);

        String email = emailText.getText().toString();
        String pwd = passwordText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailTextInput.setError("请输入邮箱");
            return;
        } else if (!isEmailValid(email)) {
            emailTextInput.setError("请输入邮箱的邮箱");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            passwordTextInput.setError("取那个输入密码");
            return;
        } else if (!isPasswordValid(pwd)) {
            passwordTextInput.setError("密码长度不够 ");
            return;
        }
        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
