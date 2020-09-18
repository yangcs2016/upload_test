
import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';


class UploadPlugin{
  static const MethodChannel _channel = const MethodChannel('upload_plugin');

  StreamController<String> _streamController =new StreamController.broadcast();
  UploadPlugin(){
    _channel.setMethodCallHandler(_handleMethod);
  }
  Stream<String> get onSayHello => _streamController.stream;
  //根据方法确定如何处理android原生端触发的指令
  Future<void> _handleMethod(MethodCall call) async {
    switch(call.method) {
      case 'onSayHello':
        String msg = call.arguments;
        if (msg != null) {
          _streamController.add(msg);
        }
        return new Future.value("");
    }
  }

  static Future<String> setName(String name) async {
    String result = await _channel.invokeMethod('setName',name);
    return result;
  }
}
