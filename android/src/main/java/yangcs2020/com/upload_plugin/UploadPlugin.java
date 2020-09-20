package yangcs2020.com.upload_plugin;


import io.flutter.plugin.common.PluginRegistry.Registrar;

/** UploadPlugin */
//只做注册使用
public class UploadPlugin {
  public static void registerWith(Registrar registrar) {
    //注册平台视图，然后注册视图工厂，(参数1:flutter调用时传过来的视图类型标识符，参数2：视图创建工厂类实例)
    registrar.platformViewRegistry().registerViewFactory("upload_plugin",new UploadFactory(registrar));
  }
}
