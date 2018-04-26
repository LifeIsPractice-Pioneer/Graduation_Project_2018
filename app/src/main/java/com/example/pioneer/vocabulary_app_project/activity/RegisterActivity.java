package com.example.pioneer.vocabulary_app_project.activity;

import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pioneer.vocabulary_app_project.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button register;
    private EditText etaccount;
    private EditText etpassword;
    private ProgressDialog loginProgress;
    public static final int MSG_LOGIN_RESULT = 0;
    private String MSG_LOGIN_TYPE = "0";
    public String serverUrl = "http://47.95.235.114:8080/fly2/user/register";

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOGIN_RESULT:
                    loginProgress.dismiss();
                    JSONObject json = (JSONObject) msg.obj;
                    handleLoginResult(json);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register);
        init();

    }

    private void init() {
        register = (Button) findViewById(R.id.registerButton);
        etaccount = (EditText) findViewById(R.id.accountEdittext);
        etpassword = (EditText) findViewById(R.id.pwdEdittext);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.accountEdittext:
                String userName =
                        etaccount.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.pwdEdittext:
                String passW = etpassword.getText().toString();
                if (TextUtils.isEmpty(passW)) {
                    etpassword.setError("密码不能为空");
                    return;
                }
                break;
            case R.id.registerButton:
                handleLogin();
                break;
            default:
                break;

        }

    }
    private void handleLogin() {
        String username = etaccount.getText().toString();
        String password = etpassword.getText().toString();
        String type =  MSG_LOGIN_TYPE;
        login(username, password,type);
    }

    private void login(final String username, final String password,final String type) {
        loginProgress = new ProgressDialog(this);
        loginProgress.setCancelable(false);
        loginProgress.setCanceledOnTouchOutside(false);
        loginProgress.show(this, null, "注册中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(serverUrl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("account", username));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("type", type));

                HttpResponse httpResponse = null;
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    httpResponse = client.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String entityString = EntityUtils.toString(entity);
                        String jsonString = entityString.substring(entityString.indexOf("{"));
                        JSONObject json = new JSONObject(jsonString);
                        sendMessage(MSG_LOGIN_RESULT, json);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void handleLoginResult(JSONObject json) {
        /*
         * login_result:
         * -1：登陆失败，未知错误！
         * 0: 登陆成功！
         * 1：登陆失败，用户名或密码错误！
         * 2：登陆失败，用户名不存在！
         * */
        boolean resultCode =  false;
        try {
            resultCode = json.getBoolean("success");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (resultCode == true) {
            onLoginSuccess(json);

        }
    }

    private void onLoginSuccess(JSONObject json) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void sendMessage(int what, Object obj) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        mHandler.sendMessage(msg);
    }
}
