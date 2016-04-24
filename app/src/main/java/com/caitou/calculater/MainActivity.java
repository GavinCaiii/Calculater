package com.caitou.calculater;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private EditText display;
    private Class<EditText> cls;
    private Method method;

    private String edStr;
//    private StringBuffer sb;
    private String str;
    private double result;
    private double cacheCount;
    private char operate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.ed_display);

//        init();

        disableShowSoftInput(display);
    }

    /**
     *
     * @author Guangzhao Cai
     * @createTime
     * @lastModify
     * @param
     * @return
     */
//    public void init(){
//        sb = new StringBuffer();
//    }

    /**
     * 禁止EditText弹出输入法，但光标仍能显示
     * @author Guangzhao Cai
     * @createTime 2016/4/22
     * @lastModify 2016/4/22
     * @param
     * @return
     */
    public void disableShowSoftInput(EditText editText){
        if (Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        }else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            cls = EditText.class;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_0:
                display.append("0");
                break;
            case R.id.bt_1:
                display.append("1");
                break;
            case R.id.bt_2:
                display.append("2");
                break;
            case R.id.bt_3:
                display.append("3");
                break;
            case R.id.bt_4:
                display.append("4");
                break;
            case R.id.bt_5:
                display.append("5");
                break;
            case R.id.bt_6:
                display.append("6");
                break;
            case R.id.bt_7:
                display.append("7");
                break;
            case R.id.bt_8:
                display.append("8");
                break;
            case R.id.bt_9:
                display.append("9");
                break;
            case R.id.bt_point:
                display.append(".");
                break;
            case R.id.bt_equal:
                display.append("\n");
                display.append("=");
                break;
            case R.id.bt_add:
                display.append("+");
                break;
            case R.id.bt_minus:
                display.append("-");
                break;
            case R.id.bt_multiply:
                display.append("×");
                break;
            case R.id.bt_divide:
                display.append("÷");
                break;
            case R.id.bt_clear:
                Toast.makeText(MainActivity.this, "清空", Toast.LENGTH_SHORT).show();
                display.setText("");
                break;
            case R.id.bt_delete:
                break;
            case R.id.bt_l_bracket:
                display.append("");
                break;
            case R.id.bt_r_bracket:
                break;
        }
    }

    public void calculate(char ch){
        if ((ch<='9'&& ch>='0')||ch=='.'){
            str += ch;
            display.setText(str);
        }
        switch (ch) {
            case '+':
                if (str.length() == 0)
                    break;
                cacheCount = Double.parseDouble(str);
                result += cacheCount;
                str = "";
                break;
            case '-':
                if (str.length() == 0)
                    break;
                cacheCount = Double.parseDouble(str);
                result -= cacheCount;
                str = "";
                break;
            case '*':
                if (str.length() == 0)
                    break;
                cacheCount = Double.parseDouble(str);
                str = "";
                break;

            case '/':
                if (str.length() == 0)
                    break;
                cacheCount = Double.parseDouble(str);
                str = "";
                break;
            case '=':
                if (str.length() == 0)
                    break;
                cacheCount = Double.parseDouble(str);
                cacheCount = 0;
                result = 0;
                break;

            case 'd':
                edStr = display.getText().toString();
                if (edStr.equals("")) {
                    return;
                }
                display.getText().delete(display.getSelectionStart() - 1, display.getSelectionStart());
                System.out.println("edstr = " + edStr);
                if (display.getSelectionStart() != display.getSelectionEnd())
                    display.setSelection(display.getText().toString().length());
                Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
