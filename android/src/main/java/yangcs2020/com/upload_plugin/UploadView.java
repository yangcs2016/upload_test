package yangcs2020.com.upload_plugin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.EventSink;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.platform.PlatformView;

public class UploadView implements PlatformView, MethodChannel.MethodCallHandler, View.OnClickListener {
    private static final String TAG = "UploadView";
    private Registrar mRegistrar = null;
    private MethodChannel channel = null;
    private EventSink eventSink =null;
    private final TextView textView;
    public UploadView(Context context, Registrar registrar, int viewId) {
        //初始化methodchannel，并设置为处理方法类
        channel = new MethodChannel(registrar.messenger(),"upload_"+viewId);
        channel.setMethodCallHandler(this);
//        EventChannel eventChannel = new EventChannel(registrar.messenger(),"uploadEvent_" + viewId);
//        eventChannel.setStreamHandler(streamHandler);
        textView = new TextView(registrar.context());
        //textView.setOnClickListener(this);
    }

    private  EventChannel.StreamHandler streamHandler = new EventChannel.StreamHandler() {
        @Override
        public void onListen(Object o, EventChannel.EventSink sink) {
            eventSink = sink;
        }

        @Override
        public void onCancel(Object o) {
            eventSink = null;
        }
    };

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "setText":
                setText(call, result);
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public View getView() {
        return textView;
    }

    @Override
    public void dispose() {

    }
    private void setText(MethodCall methodCall, Result result) {
        String text = (String) methodCall.arguments;
        textView.setText(text);
        result.success(null);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: "+v.toString());
    }
}
