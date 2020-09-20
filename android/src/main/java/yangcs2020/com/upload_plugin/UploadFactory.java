package yangcs2020.com.upload_plugin;

import android.content.Context;

import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class UploadFactory extends PlatformViewFactory {
    private final Registrar mRegister;
    public UploadFactory(Registrar registrar) {
        //StandardMessageCodec.INSTANCE:上下层编码格式设置
        super(StandardMessageCodec.INSTANCE);
        this.mRegister = registrar;
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        return new UploadView(context,mRegister,viewId);
    }
}
