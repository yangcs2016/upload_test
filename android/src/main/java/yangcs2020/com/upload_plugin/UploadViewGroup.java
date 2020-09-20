package yangcs2020.com.upload_plugin;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;

public class UploadViewGroup extends ViewGroup {
    public UploadViewGroup(Context context) {
        super(context);
        myAddView();
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        View v = getChildAt(0);
        v.layout(arg1,arg2,arg3,arg4);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    public void myAddView(){
        Button button = new Button(this.getContext());
        addView(button);
    }
}