package android.src.main.java.yangcs2020.com.upload_plugin.aliyuncommon.baseAdapter.animation;

import android.animation.Animator;
import android.view.View;

/**
 * 基recyclervView的item础动画接口
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);
}