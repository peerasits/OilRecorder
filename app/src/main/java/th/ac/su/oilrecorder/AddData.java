package th.ac.su.oilrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;


public class AddData extends AppCompatActivity implements View.OnClickListener{

    private double unitPrice = 0,price = 0,mileage = 0;
    private TextView dateText,amountText;
    private EditText unitEdit,priceEdittext,mileageText;
    private String time;
    private RadioButton[] rads = new RadioButton[7];
    private LocalDateTime now;
    private DateTimeFormatter dtf;
    private String unitStr,priceStr;

    public String findFuelType(RadioButton[] rads){

        for(RadioButton r : rads){
            if(r.isChecked()) {
                if (r.getId() == R.id.benzene_radio) {
                    return "benzene";
                } else if (r.getId() == R.id.gas95_radio) {
                    return "gasohol 95";
                } else if (r.getId() == R.id.gas91_radio) {
                    return "gasohol 91";
                } else if (r.getId() == R.id.e20_radio) {
                    return "gasohol e20";
                } else if (r.getId() == R.id.e85_radio) {
                    return "gasohol e85";
                }else if(r.getId() == R.id.b7_radio){
                    return  "b7 diesel";
                }else if(r.getId() == R.id.b10_radio){
                    return  "b10 diesel or standard diesel";
                }
            }
        }
        return "non select";
    }

    protected void onResume() {

        super.onResume();
        time = dtf.format(now);
        dateText.setText(time);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        dateText = findViewById(R.id.dateText);
        now = LocalDateTime.now(ZoneId.of("UTC+7"));
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        time = dtf.format(now);
        dateText.setText(time);



        Button summitBtn = findViewById(R.id.summit_btn);
        summitBtn.setOnClickListener(this);

        unitEdit = findViewById(R.id.unit_editext);
        priceEdittext = findViewById(R.id.price_edittext);
        mileageText = findViewById(R.id.mileage_text);
        amountText = findViewById(R.id.amount_text);

        rads[0] = findViewById(R.id.benzene_radio);
        rads[1] = findViewById(R.id.gas95_radio);
        rads[2] = findViewById(R.id.gas91_radio);
        rads[3] = findViewById(R.id.e20_radio);
        rads[4] = findViewById(R.id.e85_radio);
        rads[5] = findViewById(R.id.b7_radio);
        rads[6] = findViewById(R.id.b10_radio);



    }

    @Override
    public void onClick(View v) {
        Button b = findViewById(v.getId());
        if(b.getId() == R.id.summit_btn){
            String unitText = unitEdit.getText().toString();
            if(!(unitText==null || unitText.equals("")))
                unitPrice = Double.parseDouble(unitText);
            String priceText = priceEdittext.getText().toString();
            if(!(priceText==null || priceText.equals("")))
                price = Double.parseDouble(priceText);
            String s = mileageText.getText().toString();
            if(!(s == null || s.equals("")))
                mileage = Double.parseDouble(s);

            String resultFuel = findFuelType(rads);

            AlertDialog.Builder dialog = new AlertDialog.Builder(AddData.this);
            dialog.setTitle("Notification");
            dialog.setMessage(String.valueOf(mileage)+"\n" +
                    "Unit price : "+String.valueOf(unitPrice)+" bath\nTotal price : "+String.valueOf(price)+" bath\n"+time+"\n"+resultFuel);
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }
    }
}