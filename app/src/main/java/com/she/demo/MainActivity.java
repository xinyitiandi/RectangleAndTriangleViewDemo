package com.she.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.she.demo.custom.RectangleAndTriangleView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private RectangleAndTriangleView rectangleAndTriangleView_left;
    private RectangleAndTriangleView rectangleAndTriangleView_right;
    private RectangleAndTriangleView rectangleAndTriangleView_top;
    private RectangleAndTriangleView rectangleAndTriangleView_bottom;
    private RectangleAndTriangleView rectangleAndTriangleView_no_out_line;
    private RectangleAndTriangleView rectangleAndTriangleView_no_triangle;
    private Button button_change_no_line;
    private int i=1;
    private int[] colorBg={R.color.bg_color_1,R.color.bg_color_2,R.color.bg_color_3,R.color.bg_color_4,R.color.bg_color_5,
            R.color.bg_color_6,R.color.bg_color_7,R.color.bg_color_8};
    private int[] colorLineBg={R.color.bg_line_1,R.color.bg_line_2,R.color.bg_line_3,R.color.bg_line_4,R.color.bg_line_5,
            R.color.bg_line_6,R.color.bg_line_7,R.color.bg_line_8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEventListener();
    }

    private void initView() {
        button = findViewById(R.id.button);
        button_change_no_line = findViewById(R.id.button_change_no_line);
        rectangleAndTriangleView_left = findViewById(R.id.rectangleAndTriangleView_left);
        rectangleAndTriangleView_right = findViewById(R.id.rectangleAndTriangleView_right);
        rectangleAndTriangleView_top = findViewById(R.id.rectangleAndTriangleView_top);
        rectangleAndTriangleView_bottom = findViewById(R.id.rectangleAndTriangleView_bottom);
        rectangleAndTriangleView_no_out_line = findViewById(R.id.rectangleAndTriangleView_no_out_line);
        rectangleAndTriangleView_no_triangle = findViewById(R.id.rectangleAndTriangleView_no_triangle);
    }

    private void initEventListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectangleAndTriangleView_left.setFillColor(getResources().getColor(colorBg[i]));
                rectangleAndTriangleView_left.setLineColor(getResources().getColor(colorLineBg[i]));
                i++;
                if (i==8){
                    i=0;
                }
            }
        });
        button_change_no_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rectangleAndTriangleView_left.isHaveLine()){
                    rectangleAndTriangleView_left.setIsHaveLine(false);
                }else{
                    rectangleAndTriangleView_left.setIsHaveLine(true);
                }
            }
        });
    }
}
