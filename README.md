# MultipleGlidView
这是一个创建网格的控件，并提供在指定网格内绘画的功能，如下图，就是通过4个该控件绘制而成的

![github](http://p8bciigmy.bkt.clouddn.com/device-2018-08-18-144508.png)

## 使用

## 示例

指定格子大小，宽度高度自适应

    <com.simple.multipleglid.MultipleGlidView
            android:id="@+id/top"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:column_count="10"
            app:no_bottom_line="true"
            app:node_height="20dp"
            app:node_width="20dp"
            app:row_count="10" />
            
或者指定宽度大小，高度自使用

    <com.simple.multipleglid.MultipleGlidView
            android:id="@+id/top"
            android:layout_width="611px"
            android:layout_height="wrap_content"
            app:column_count="10"
            app:no_bottom_line="true"
            app:node_is_square="true"
            app:node_width="20dp"
            app:row_count="12" />
            
属性介绍：
    
    设置行数、列数
    <attr name="row_count" format="integer"/>
    <attr name="column_count" format="integer"/>
    
    设置格子的宽和高
    <attr name="node_width" format="dimension"/>
    <attr name="node_height" format="dimension"/>
    
    设置格子是否是正方形，如果设为true，格子的宽和高只需要设置一个
    <attr name="node_is_square" format="boolean"/>
    
    边框线的颜色、粗细
    <attr name="line_color" format="color"/>
    <attr name="line_weight" format="dimension"/>
    
    分别设置有没有上右下左边框线
    <attr name="no_left_line" format="boolean"/>
    <attr name="no_right_line" format="boolean"/>
    <attr name="no_top_line" format="boolean"/>
    <attr name="no_bottom_line" format="boolean"/>
    
在格子中绘画

    public interface NodeImp {

        void draw(Canvas canvas, Paint paint, float left, float top, float right, float bottom);

        int getX();

        int getY();
    }

实现该接口,可以指定一个格子的绘制。

X为水平从左到右的格子数，从0开始

Y为竖直从上到下的格子数，从0开始

可以通过canvas画需要的内容，且提供绘画范围，demo中有画中心圆、字、一些图案的示例。

最后通过将数据List传递给控件，控件会遍历每个List进行绘制。

        List<NodeImp> list = new ArrayList<>();
        list.add(new FontNode(5, 5, "龙", Color.DKGRAY, Color.BLUE));
        view.setNodeList(list);

## 注意事项

如果需要三个及以上网格组合搭配起来使用，而且格子大小不统一，需要注意格子的边框线对齐问题。

比如大格子是20px，下面想要均分为两个格子，那么小格子=(20-边框线)/2;这个时候是没有办法整除的，最后多个网格搭配起来会使得边框线相差1px。

可以通过代码计算控制格子大小，并且在布局上不连续地依赖布局来解决，即避免B依赖A布局，C依赖B布局，D依赖C布局。

    Utils.cutNodeWidth(context, nodeWidth, cutPart, grid.getLineWeight());

# License

        Copyright [2018] [yanghaozhang]

           Licensed under the Apache License, Version 2.0 (the "License");
           you may not use this file except in compliance with the License.
           You may obtain a copy of the License at

               http://www.apache.org/licenses/LICENSE-2.0

           Unless required by applicable law or agreed to in writing, software
           distributed under the License is distributed on an "AS IS" BASIS,
           WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
           See the License for the specific language governing permissions and
           limitations under the License.






