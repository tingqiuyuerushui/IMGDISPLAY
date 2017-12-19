package com.mine.myapplication.customview;

import android.widget.ListView;

/** 
 * ScrollView中嵌入ListView,让ListView全显示出来 
 * @author reyo 
 * 
 */  
public class MyListView extends ListView {
  
    public MyListView(android.content.Context context, android.util.AttributeSet attrs){
        super(context, attrs);  
    }  
  
    /** 
     * 设置不滚动 
     */  
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        try {
        	super.onMeasure(widthMeasureSpec, expandSpec);  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  
    }  
      
}  
