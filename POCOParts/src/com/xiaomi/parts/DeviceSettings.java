/*
 * Copyright (C) 2018 The Asus-SDM660 Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.xiaomi.parts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SELinux;
import android.os.Handler;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.SwitchPreference;
import androidx.preference.TwoStatePreference;
import android.util.Log;

import com.xiaomi.parts.kcal.KCalSettingsActivity;
import com.xiaomi.parts.preferences.VibratorStrengthPreference;
import com.xiaomi.parts.preferences.CustomSeekBarPreference;
import com.xiaomi.parts.preferences.SecureSettingListPreference;
import com.xiaomi.parts.preferences.SecureSettingSwitchPreference;

public class DeviceSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = "POCOParts";

    public static final String CATEGORY_DISPLAY = "display";
    public static final String PREF_DEVICE_KCAL = "device_kcal";
    public static final String KEY_VIBSTRENGTH = "vib_strength";
    public static final String KEY_USB2_SWITCH = "usb2_fast_charge";

    private Preference mKcal;
    private static Context mContext;
    private VibratorStrengthPreference mVibratorStrength;
    private static TwoStatePreference mUSB2FastChargeModeSwitch;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_poco_parts, rootKey);
        mContext = this.getContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        String device = FileUtils.getStringProp("ro.build.product", "unknown");

        mVibratorStrength = (VibratorStrengthPreference) findPreference(KEY_VIBSTRENGTH);
        if (mVibratorStrength != null) {
            mVibratorStrength.setEnabled(VibratorStrengthPreference.isSupported());
        }

        PreferenceCategory displayCategory = (PreferenceCategory) findPreference(CATEGORY_DISPLAY);

        mKcal = findPreference(PREF_DEVICE_KCAL);

        mKcal.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), KCalSettingsActivity.class);
            startActivity(intent);
            return true;
        });

        // USB2 Force FastCharge Toggle
        mUSB2FastChargeModeSwitch = (TwoStatePreference) findPreference(KEY_USB2_SWITCH);
        mUSB2FastChargeModeSwitch.setEnabled(USB2FastChargeModeSwitch.isSupported());
        mUSB2FastChargeModeSwitch.setChecked(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(DeviceSettings.KEY_USB2_SWITCH, false));
        mUSB2FastChargeModeSwitch.setOnPreferenceChangeListener(new USB2FastChargeModeSwitch());
	}

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        final String key = preference.getKey();
        return true;
    }

    private boolean isAppNotInstalled(String uri) {
        PackageManager packageManager = getContext().getPackageManager();
        try {
            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return true;
        }
    }
}
