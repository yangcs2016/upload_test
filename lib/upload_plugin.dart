import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

//View创建完成回调
typedef void UploadCreatedCallback(UploadController controller);

//View上层组件
class UploadPlugin extends StatefulWidget {
  final UploadCreatedCallback onCreated;
  UploadPlugin({
    Key key,
    @required this.onCreated,
  });

  @override
  _UploadPluginState createState() => _UploadPluginState();
}

class _UploadPluginState extends State<UploadPlugin> {
  String _platformMessage = "";
  static const EventChannel eventChannel = const EventChannel('upload_event');
  Stream<String> _ontap;
  Stream<String> ontap(){
    if(_ontap==null){
      _ontap = eventChannel.receiveBroadcastStream();
    }
    return _ontap;
  }
  StreamSubscription _streamSubscription;
  @override
  void initState() {
    _enableEventReceiver();
    super.initState();
  }

  @override
  void dispose() {
    _disableEventReceiver();
    super.dispose();
  }

  void _enableEventReceiver() {
    _streamSubscription =
        eventChannel.receiveBroadcastStream().listen((dynamic event) {
      print('Received event: $event');
      setState(() {
        _platformMessage = event;
      });
    }, onError: (dynamic error) {
      print('Received error: ${error.message}');
    }, cancelOnError: true);
  }

  void _disableEventReceiver() {
    if (_streamSubscription != null) {
      _streamSubscription.cancel();
      _streamSubscription = null;
    }
  }

  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'upload_plugin',
        onPlatformViewCreated: onPlatformViewCreated,
        creationParamsCodec: const StandardMessageCodec(),
      );
    } else if (defaultTargetPlatform == TargetPlatform.iOS) {
      return UiKitView(
        viewType: 'upload_plugin',
        onPlatformViewCreated: onPlatformViewCreated,
        creationParamsCodec: const StandardMessageCodec(),
      );
    }
    return Text('$defaultTargetPlatform 不支持此插件');
  }

  //视图创建完成
  Future<void> onPlatformViewCreated(id) async {
    if (widget.onCreated == null) {
      return;
    }
    widget.onCreated(UploadController.init(id));
  }
}

class UploadController {
  MethodChannel _channel;
  UploadController.init(int id) {
    _channel = MethodChannel('upload_$id');
  }
  Future<void> setText(String text) async {
    assert(text != null);
    return _channel.invokeMethod('setText', text);
  }
}
