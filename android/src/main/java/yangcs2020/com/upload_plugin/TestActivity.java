package yangcs2020.com.upload_plugin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.plugin.common.EventChannel.EventSink;

public class TestActivity extends FlutterActivity {
    private static String TAG = "TestActivity";
    private  EventChannel eventChannel;
    private  EventSink eventSink;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        eventChannel =new EventChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),"upload_event");
        eventChannel.setStreamHandler(streamHandler);
    }

    private  StreamHandler streamHandler = new StreamHandler() {
        @Override
        public void onListen(Object arguments, EventSink events) {
            eventSink = events;
            Log.d(TAG, "onListen: ");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    eventSink.success("Android");
                }
            }, 5000);
        }

        @Override
        public void onCancel(Object arguments) {
            Log.d(TAG, "onCancel: Android");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}