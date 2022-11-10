package au.edu.jcu.cp3406.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /*
    When Done button pressed it pull out the fontSize pass back to ConvertActivity
     */
    public void doneClicked(View view) {
        EditText editText = findViewById(R.id.fontSize);
        String fontSize = editText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("fontSize", fontSize);
        setResult(RESULT_OK, intent);
        finish(); // programmatically close this activity
    }
}