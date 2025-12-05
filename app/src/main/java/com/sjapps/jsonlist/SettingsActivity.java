package com.sjapps.jsonlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sj14apps.jsonlist.core.AppState;
import com.sjapps.jsonlist.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    ArrayAdapter<CharSequence> Themes;
    AppState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
        setLayoutBounds();

        LoadData();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
        binding.mainSV.startAnimation(animation);

        binding.themeSpinner.setSelection(state.getTheme());

        binding.themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state.setTheme(position);
                switch (position) {
                    case 0:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case 1:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case 2:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }

                SaveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.CheckForUpdateSwitch.setChecked(state.isAutoCheckForUpdate());
        binding.MIMESwitch.setChecked(state.isMIMEFilterDisabled());
        binding.sHighlightingSwitch.setChecked(state.isSyntaxHighlighting());

        binding.CheckForUpdateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            state.setAutoCheckForUpdate(isChecked);
            SaveData();
        });

        binding.MIMESwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            state.setMIMEFilterDisabled(isChecked);
            SaveData();
        });

        binding.sHighlightingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            state.setSyntaxHighlighting(isChecked);
            SaveData();
        });

    }

    private void setLayoutBounds() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootView, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets insetsN = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

            layoutParams.leftMargin = insets.left + insetsN.left;
            layoutParams.topMargin = insets.top;
            layoutParams.rightMargin = insets.right + insetsN.right;
            layoutParams.bottomMargin = insets.bottom;
            v.setLayoutParams(layoutParams);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void LoadData() {
        state = FileSystem.loadStateData(this);
    }

    private void SaveData() {
        FileSystem.SaveState(this, state);
    }

    private void initialize() {

        Themes = ArrayAdapter.createFromResource(this, R.array.Themes, android.R.layout.simple_spinner_item);
        Themes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.themeSpinner.setAdapter(Themes);

    }

    public void goBack(View view) {
        finish();
    }

}