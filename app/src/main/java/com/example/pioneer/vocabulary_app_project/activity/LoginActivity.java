package com.example.pioneer.vocabulary_app_project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pioneer.vocabulary_app_project.R;
import com.example.pioneer.vocabulary_app_project.launcher.LauncherActivity;
import com.example.pioneer.vocabulary_app_project.util.Constantss;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 新浪微博相关变量定义
     */
    //SsoHandler 仅当 SDK 支持 SSO 时有效
    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    //封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能，用于在回调方法中接收授权成功后返回的信息
    private Oauth2AccessToken mAccessToken;
    /**
     * activity_login相关变量定义
     */
    private TextView know;
    private ImageButton webo;
    private ImageButton qq;
    private ImageButton phone;
    private TextView pwd;
    private TextView protocol;
    /**
     * 腾讯QQ相关变量定义
     */
    //qq开放平台APPID
    private static final String APPID = "1106697318";
    //qq主操作对象
    private Tencent mTencent;
    //授权登录监听器
    private IUiListener loginListener;
    //获取用户信息监听器
    private IUiListener userInfoListener;
    //获取信息的范围参数
    private String scope;
    //qq用户信息
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findById();
        OnClickListener();
        initData();
        // 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
        mAuthInfo = new AuthInfo(this, Constantss.APP_KEY, Constantss.REDIRECT_URL, Constantss.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    //绑定事件
    public void findById() {
        know = (TextView) findViewById(R.id.login_tv_know);
        pwd = (TextView) findViewById(R.id.login_tv_pwd);
        protocol = (TextView) findViewById(R.id.login_tv_protocol);
        webo = (ImageButton) findViewById(R.id.login_ib_weibo);
        qq = (ImageButton) findViewById(R.id.login_ib_qq);
        phone = (ImageButton) findViewById(R.id.login_ib_phone);
    }

    //绑定监听器
    public void OnClickListener() {
        know.setOnClickListener(this);
        pwd.setOnClickListener(this);
        protocol.setOnClickListener(this);
        webo.setOnClickListener(this);
        qq.setOnClickListener(this);
        phone.setOnClickListener(this);
    }

    //点击操作
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tv_know:
                Intent intent_know = new Intent();
                intent_know.setClass(LoginActivity.this, LauncherActivity.class);
                startActivity(intent_know);
                finish();
                break;
            case R.id.login_ib_qq:
                login();
                userInfo = new UserInfo(LoginActivity.this, mTencent.getQQToken());
                userInfo.getUserInfo(userInfoListener);
                break;
            case R.id.login_ib_weibo:
                mSsoHandler.authorize(new AuthListener());
                break;
            case R.id.login_tv_protocol:
                Intent intent_protocol = new Intent();
                intent_protocol.setClass(LoginActivity.this, ProtocolActivity.class);
                startActivity(intent_protocol);
                finish();
            case R.id.login_ib_phone:
                Intent intent_phone = new Intent();
                intent_phone.setClass(LoginActivity.this, PhoneRegisterActivity.class);
                startActivity(intent_phone);
                finish();
                break;
            case R.id.login_tv_pwd:
                Intent intent_pwd = new Intent();
                intent_pwd.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_pwd);
                finish();
                break;
            default:
                break;
        }

    }

    /**
     * 腾讯QQ自动登录初始化
     */
    private void initData() {
        //初始化qq主操作对象
        mTencent = Tencent.createInstance(APPID, LoginActivity.this);
        //申请所有权限
        scope = "all";
        loginListener = new IUiListener() {
            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onComplete(Object value) {
                // TODO Auto-generated method stub

                System.out.println("有数据返回..");
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    int ret = jo.getInt("ret");

                    System.out.println("json=" + String.valueOf(jo));

                    if (ret == 0) {
                        Toast.makeText(LoginActivity.this, "登录成功",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onComplete(Object arg0) {
                // TODO Auto-generated method stub
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

                    Toast.makeText(LoginActivity.this, "你好，" + nickName,
                            Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    // TODO: handle exception
                }


            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };
    }

    //腾讯QQ自动登录
    private void login() {
        //如果session无效，就开始登录
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(LoginActivity.this, scope, loginListener);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //腾讯QQ相关操作
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        //新浪微博相关操作
        super.onActivityResult(requestCode, resultCode, data);
        //SSO 授权回调
        //重要：发起sso登录的activity必须重写onActivtyResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 微博认证授权回调类
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，该回调才会被执行
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中
     */
    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle bundle) {
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {//授权成功
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent_weibo = new Intent();
                intent_weibo.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent_weibo);
                finish();
                //显示Access_Token
                //tvAccessToken.setText("Access_token:\n" + mAccessToken.getToken());
                //获取用户具体信息
                //getUserInfo();
            } else {
                /**
                 *  以下几种情况，您会收到 Code：
                 * 1. 当您未在平台上注册应用程序的包名与签名时
                 * 2. 当您注册的应用程序包名与签名不正确时
                 * 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时
                 */
                String code = bundle.getString("code");//直接从bundle里边获取
                if (!TextUtils.isEmpty(code)) {
                    Toast.makeText(LoginActivity.this, "签名不正确", Toast.LENGTH_SHORT).show();
                }
            }
//            String phoneNum = mAccessToken.getPhoneNum();//通过手机短信授权登录时可以拿到，此demo未实现此种授权方式
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LoginActivity.this, "授权异常", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }

}
