import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:upload_plugin/upload_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  UploadController controller;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    UploadPlugin upload = UploadPlugin(
      onCreated: onViewCreated,
    );
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Container(
            child: upload,
            height: 50.0,
            width: 100.0,
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            controller.setText('给点力好不好？');
          },
          child: Icon(Icons.flight_takeoff),
        ),
      ),
    );
  }

  void onViewCreated(UploadController controller) {
    this.controller = controller;
  }
}
