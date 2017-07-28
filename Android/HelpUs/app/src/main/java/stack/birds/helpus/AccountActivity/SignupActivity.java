package stack.birds.helpus.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import stack.birds.helpus.Account.Account;
import stack.birds.helpus.MainActivity;
import stack.birds.helpus.R;

public class SignupActivity extends AppCompatActivity {

    EditText name, id, pw, re_pw;
    TextView regist_check;

    int REGIST_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.name_enter);
        id = (EditText) findViewById(R.id.ID_enter);
        pw = (EditText) findViewById(R.id.PW_enter);
        re_pw = (EditText) findViewById(R.id.rPW_enter);

        regist_check = (TextView) findViewById(R.id.regist_check);
        regist_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 비밀번호 check가 다르면 다시 입력
                if (!pw.getText().toString().equals(re_pw.getText().toString())) {
                    Snackbar.make(getWindow().getDecorView().getRootView()
                            , "비밀번호를 다시 확인해 주세요", Snackbar.LENGTH_LONG).show();

                } else {
                    HashMap<String, String> regist_param = new HashMap<String, String>();
                    regist_param.put("name",  name.getText().toString());
                    regist_param.put("id", id.getText().toString());
                    regist_param.put("pw", pw.getText().toString());

                    Account account = new Account(getApplicationContext());
                    int result = account.requestToServer(REGIST_FLAG, regist_param);
                    // 만약 회원가입 성공시 로그인창으로 이동
                    if(result == 1) {
                        // 회원가입성공시 아이디와 비밀번호를 자동로그인시킴
                        account.registAutoLogin(id.getText().toString(), pw.getText().toString());

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (result == 0){
                        // 해당아이디가 이미 서버에 있는 경우
                        Snackbar.make(getWindow().getDecorView().getRootView()
                                , "해당아이디가 있습니다. 다른 아이디를 입력해주세요.", Snackbar.LENGTH_LONG).show();
                    } else if (result == -1) {
                        Snackbar.make(getWindow().getDecorView().getRootView()
                                , "회원가입에 실패하였습니다. 네트워크를 확인해주세요.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
