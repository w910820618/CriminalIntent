package com.wuzhouyang.criminalintent.fragment;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuzhouyang.criminalintent.R;

import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment implements DatePicker.OnDateChangedListener, View.OnClickListener {

    private static Date curDate;
    Calendar currentDate;
    Calendar newDate;

    DatePicker datePicker;
    TextView backButton;
    TextView ensureButton;
    View splitLineV;

    public static DatePickerFragment getInstance(Date date) {
        curDate = date;
        return new DatePickerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(0.8f);
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = R.style.ZZBDatePickerDialogLStyle;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            style = R.style.ZZBDatePickerDialogLStyle;
        } else {
            style = getTheme();
        }
        return new Dialog(getActivity(), style);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datePicker = view.findViewById(R.id.datePickerView);
        backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(this);
        ensureButton = view.findViewById(R.id.ensure);
        ensureButton.setOnClickListener(this);
        splitLineV = view.findViewById(R.id.splitLineV);
        //bug4:LOLLIPOP和Marshmallow上，使用spinner模式，然后隐藏滚轮，显示日历(spinner模式下的日历没有头部)，日历最底部一排日期被截去部分。所以只能使用calender模式，然后手动隐藏header（系统没有提供隐藏header的api）。
        //如果只要日历部分，隐藏header
        ViewGroup mContainer = (ViewGroup) datePicker.getChildAt(0);
        View header = mContainer.getChildAt(0);
        header.setVisibility(View.GONE);
        //Marshmallow上底部留白太多，减小间距
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) datePicker.getLayoutParams();
        layoutParams.bottomMargin = 10;
        datePicker.setLayoutParams(layoutParams);
        initDatePicker();
    }

    private void initDatePicker() {
        if (datePicker == null) {
            return;
        }
        if (currentDate == null) {
            currentDate = Calendar.getInstance();
            currentDate.setTime(curDate);
        }
        datePicker.init(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                sendDateToParent();
                break;
            case R.id.ensure:
                returnSelectedDateUnderLOLLIPOP();
                break;
            default:
                break;
        }
    }

    private void returnSelectedDateUnderLOLLIPOP() {
        //bug3:5.0上超过可选区间的日期依然能选中,所以要手动校验.5.1上已解决，但是为了与5.0保持一致，也采用确定菜单返回日期
        if (onSelectedDateListener != null) {
            onSelectedDateListener.onSelectedDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onSelectedDateListener = null;
    }

    public interface OnSelectedDateListener {
        void onSelectedDate(int year, int monthOfYear, int dayOfMonth);
    }

    OnSelectedDateListener onSelectedDateListener;

    public void setOnSelectedDateListener(OnSelectedDateListener onSelectedDateListener) {
        this.onSelectedDateListener = onSelectedDateListener;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (onSelectedDateListener != null) {
            onSelectedDateListener.onSelectedDate(year, monthOfYear, dayOfMonth);
        }
        newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        Log.d("DatePickerFragment", "日期发生改变");
    }

    private void sendDateToParent() {
        if (newDate != null) {
            Bundle result = new Bundle();
            result.putSerializable("bundleKey", newDate);
            getParentFragmentManager().setFragmentResult("requestKey", result);
            dismiss();
        }
    }

}