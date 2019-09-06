![](https://i.imgur.com/uOoIKTo.gif)

## 自定义View实现带三角气泡的矩形 ##

###其中有上、下、左、右四个方向，还可以选择带不带线框。###

    <com.she.demo.custom.RectangleAndTriangleView
            android:id="@+id/rectangleAndTriangleView_left"
            android:layout_width="100dp"
            android:layout_height="100dp"
            app:fill_color="@color/bg_color_1"
            app:line_color="@color/bg_line_1"
            app:tri_direction="left"
            app:tri_height="20dp"
            app:tri_width="12dp"
            app:isHaveLine="true"
            app:tri_line_size="1dp"/>

###其中 app:tri_direction="left" 控制三角形的方向，取值有四个：left、right、top、bottom。###


app:tri_height：   控制的是三角形的高度

app:tri_width  ：  控制三角形的宽度

app:isHaveLine ：  是否含有边框线

app: tri_ line_size：   边框线的宽度




### 还可以通过代码动态的设置边线的有无 ###

    rectangleAndTriangleView.setIsHaveLine(false);


###通过代码设置矩形的填充色###


    rectangleAndTriangleView.setFillColor(getResources().getColor(R.color.red));

###通过代码设置边框的颜色###


    rectangleAndTriangleView.setLineColor(getResources().getColor(R.color.black));



