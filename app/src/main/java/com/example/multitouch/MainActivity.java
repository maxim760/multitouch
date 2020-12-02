package com.example.multitouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

  TextView tv;
  ConstraintLayout screen;
  boolean inTouch = false;
  String result = "";
  String lastAction = "";
  boolean isMove = false;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    screen = findViewById(R.id.screen);
    tv = findViewById(R.id.text);
    screen.setOnTouchListener(this);

  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    int actionMask = event.getActionMasked();
    int pointerCount = event.getPointerCount();
    isMove=false;
    switch (actionMask) {
      case MotionEvent.ACTION_DOWN: // первое касание
        System.out.println(1);
        inTouch = true;
        lastAction = "Первый палец нажат на экран";
        break;
      case MotionEvent.ACTION_POINTER_DOWN: // последующие касания
        System.out.println(2);
        lastAction = Integer.valueOf(pointerCount).toString() + " Палец нажат на экран";
        break;

      case MotionEvent.ACTION_UP: // прерывание последнего касания
        System.out.println(3);
        inTouch = false;
        lastAction = "Последний палец отпущен";
        break;
      case MotionEvent.ACTION_POINTER_UP: // прерывания касаний
        lastAction = (pointerCount) + " Палец отпущен.\nСейчас на экране " + (pointerCount - 1) + " пальцев";
        System.out.println(4);
        break;

      case MotionEvent.ACTION_MOVE: // движение
        isMove=true;
        break;
    }
    result = "Последнее действие\n" + lastAction;
    if (inTouch) {
      result += "\n\n\nКоличество пальцев на экране сейчас = " + pointerCount;
    }
    result += isMove ? "\nНовых касаний не происходит..." : "";
    tv.setText(result);
    return true;
  }
}