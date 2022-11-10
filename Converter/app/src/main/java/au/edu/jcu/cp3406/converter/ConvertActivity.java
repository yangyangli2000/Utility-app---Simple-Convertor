package au.edu.jcu.cp3406.converter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertActivity extends AppCompatActivity {

    Spinner spinner;
    EditText InputValue;
    TextView binary, hexadecimal, decimal, octal;
    Button btConvert;
    public static String bValue = null, oValue = null, dValue = null, hValue = null; // Base values
    public String input = null; // Input value
    public Convert convert = new Convert();
    int k;
    private int fontSize; // Font size value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define views
        setContentView(R.layout.activity_main);
        InputValue = findViewById(R.id.InputValue);
        binary = findViewById(R.id.binary);
        hexadecimal = findViewById(R.id.hexadecimal);
        decimal = findViewById(R.id.decimal);
        octal = findViewById(R.id.octal);
        btConvert = findViewById(R.id.btConvert);
        spinner();
        // Return saved state when rotating screen
        if (savedInstanceState != null) {
            InputValue.setText(savedInstanceState.getString("input"));
            k = savedInstanceState.getInt("k");
            onConvert(null);
            fontSize = savedInstanceState.getInt("fontSize");
            changeFontSize();
        }
    }

    /*
    Save the state when rotating screen
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("input", InputValue.getText().toString());
        outState.putInt("k", k);
        outState.putInt("fontSize", fontSize);
    }

    /*
    Spinner setup and locating the item on the spinner then, when the button pressed start to convert
     */
    public void spinner(){
        // Set up
        spinner = findViewById(R.id.spinner1);
        String[] myItem;
        myItem = getResources().getStringArray(R.array.data);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, myItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // Locate the item and convert to base value
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos){
                    case 0: // 2
                        k = 2;
                        btConvert.setOnClickListener(v -> onConvert(v));
                        break;
                    case 1: // 8
                        k = 8;
                        btConvert.setOnClickListener(v -> onConvert(v));
                        break;
                    case 2: // 10
                        k = 10;
                        btConvert.setOnClickListener(v -> onConvert(v));
                        break;
                    case 3: // 16
                        k = 16;
                        btConvert.setOnClickListener(v -> onConvert(v));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /*
    When Convert is pressed,convert the input value to base values, then show on the screen
     */
    public void onConvert(View view) {
        input = InputValue.getText().toString();
        try
        {
            bValue = convert.convertToBinary(input,k);
            oValue = convert.convertToOctal(input,k);
            dValue =convert.convertToDecimal(input,k);
            hValue = convert.convertToHexadecimal(input,k);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, getString(R.string.invalidInput),
                    Toast.LENGTH_LONG).show();
        }
        binary.setText(bValue);
        octal.setText(oValue);
        decimal.setText(dValue);
        hexadecimal.setText(hValue);
    }

    /*
    When setting is pressed, go to setting screen
     */
    public void onSetting(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, 123);
    }

    /*
    Get the date form the setting screen
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            assert data != null;
            fontSize = data.getIntExtra("fontSize", 15);
            changeFontSize();
        }
    }

    /*
    Change the font size of the text
     */
    private void changeFontSize() {
        ((TextView) findViewById(R.id.from)).setTextSize(fontSize);
        ((EditText) findViewById(R.id.InputValue)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.to)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.thirdRowText)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.binary)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.fourthRowText)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.hexadecimal)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.fifthRowText)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.decimal)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.sixthRowText)).setTextSize(fontSize);
        ((TextView) findViewById(R.id.octal)).setTextSize(fontSize);
    }

    /*
    Clear the texts
     */
    public void onClear(View view) {
        InputValue.setText("");
        binary.setText("");
        hexadecimal.setText("");
        decimal.setText("");
        octal.setText("");
    }
}