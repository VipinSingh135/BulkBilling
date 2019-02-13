package com.acuratechglobal.bulkbilling.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import timber.log.Timber;

public class UiUtils {

  private static final String EMPTY_STRING = "";

  private UiUtils() {
    //No-OP
  }

  public static int getColor(@NonNull Context context, @ColorRes int resource) {
    return ContextCompat.getColor(context, resource);
  }

  public static void handleThrowable(Throwable throwable) {
    Timber.e(throwable, throwable.toString());
  }

  public static void showSnackbar(View view, String message, int length) {
    Snackbar.make(view, message, length).setAction("Action", null).show();
  }

  public static void playFailureAnimation(View view) {
    YoYo.with(Techniques.Pulse).duration(200).playOn(view);
  }

  public static int pxToDp(int px) {
    return (int) (px / Resources.getSystem().getDisplayMetrics().density);
  }

  public static int dpToPx(int dp) {
    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
  }

  @SuppressWarnings("ConstantConditions")
  public static String getInputText(Object input) {
    if (null == input) {
      return EMPTY_STRING;
    }

    if (input instanceof TextInputLayout) {
      TextInputLayout inputLayout = (TextInputLayout) input;
      return inputLayout.getEditText().getText().toString();
    } else if (input instanceof TextView) {
      TextView textView = (TextView) input;
      return textView.getText().toString();
    }

    return EMPTY_STRING;
  }

  public static Drawable getDrawable(Context context, int drawableId) {
    return ContextCompat.getDrawable(context, drawableId);
  }

  public static void hideKeyboard(Activity activity) {
    View focusedView = activity.getWindow().getCurrentFocus();
    if (focusedView != null && focusedView.isFocused()) {
      InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  public static void setupBackToolbar(AppCompatActivity activity, Toolbar toolbar) {
    activity.setSupportActionBar(toolbar);

    if (null != activity.getSupportActionBar()) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
  }

  public static void setupSimpleToolbar(AppCompatActivity activity, Toolbar toolbar) {
    activity.setSupportActionBar(toolbar);

    if (null != activity.getSupportActionBar()) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
  }

  // slide the view from below itself to the current position
  public static void slideUp(View view){
    view.setVisibility(View.VISIBLE);
    TranslateAnimation animate = new TranslateAnimation(
            0,                 // fromXDelta
            0,                 // toXDelta
            view.getHeight(),  // fromYDelta
            0);                // toYDelta
    animate.setDuration(300);
    animate.setFillAfter(true);
    view.startAnimation(animate);

  }

  // slide the view from its current position to below itself
  public static Animation slideDown(View view){
    TranslateAnimation animate = new TranslateAnimation(
            0,                 // fromXDelta
            0,                 // toXDelta
            0,                 // fromYDelta
            view.getHeight()); // toYDelta
    animate.setDuration(300);
    animate.setFillAfter(true);
    view.startAnimation(animate);
    return animate;
  }

}