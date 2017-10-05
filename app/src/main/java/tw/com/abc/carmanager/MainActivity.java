package tw.com.abc.carmanager;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner spnCarNum;
    private String strCarNum;
    private TextView tvResult;
    private EditText edDate,edUser,edStartKm,edEndKm,edGasMoney,edMemo;
    private ArrayAdapter<CharSequence> arrCarNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 選取日期
        edDate =(EditText) findViewById(R.id.eddate);
        edUser =(EditText) findViewById(R.id.eduser);
        edStartKm=(EditText) findViewById(R.id.edstartkm);
        edEndKm=(EditText) findViewById(R.id.edendkm);
        edGasMoney=(EditText) findViewById(R.id.edgasmoney);
        edMemo=(EditText) findViewById(R.id.edmemo);
        tvResult= (TextView) findViewById(R.id.tvresult);



        // 下拉選項初始化
        spinnerInit();

    }

    public void btnOk(View view) {
        if ("".equals(edUser.getText().toString().trim())) {
            tvResult.setText(getString(R.string.lb_user) + "未填寫");

        } else {

            tvResult.setText(getString(R.string.lb_user) + ":" + edUser.getText() + "\n" +
                    getString(R.string.lb_carnum) + ":" + strCarNum + "\n" +
                    getString(R.string.lb_date) + ":" + edDate.getText() + "\n" +
                    getString(R.string.lb_startkm) + ":" + edStartKm.getText() + "\n" +
                    getString(R.string.lb_endkm) + ":" + edEndKm.getText() + "\n" +
                    getString(R.string.lb_gasmoney) + ":" + edGasMoney.getText() + "\n" +
                    getString(R.string.lb_memo) + ":" + edMemo.getText()
            );
        }
    }
//清除畫面欄位
    public void btnClean(View view){

        edDate.setText("");
        edUser.setText("");
        edStartKm.setText("");
        edEndKm.setText("");
        edGasMoney.setText("");
        edMemo.setText("");
        spnCarNum.setSelection(arrCarNum.getPosition("--請選擇--"));
        tvResult.setText("");
    }


    private AdapterView.OnItemSelectedListener spn =new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strCarNum=parent.getSelectedItem().toString();
            Log.i("geoff",strCarNum);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public void spinnerInit(){
        arrCarNum = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.carNumList,
                android.R.layout.simple_spinner_dropdown_item);
        spnCarNum =(Spinner)findViewById(R.id.spncarnum);

        spnCarNum.setAdapter(arrCarNum);
        spnCarNum.setOnItemSelectedListener(spn);
    }

    public void  btnDateOnClick(View view){
        //mTxtResult.setText("");
        // Calendar 是用Java
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDlg = new DatePickerDialog(MainActivity.this,
                aa,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        //DatePickerDialog 的內容設定
        datePickerDlg.setTitle("選擇日期");
        datePickerDlg.setMessage("請選擇適合您的日期");
        datePickerDlg.setIcon(android.R.drawable.ic_dialog_info);
        // 顯示Diaglog
        datePickerDlg.show();
    }
    private DatePickerDialog.OnDateSetListener aa =new DatePickerDialog.OnDateSetListener(){
        //
//        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edDate.setText(Integer.toString(year) + "/" +
                        Integer.toString(month+1) + "/" +
                    Integer.toString(dayOfMonth) );
        }

    };
}
