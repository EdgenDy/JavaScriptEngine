import js.api.JavaScriptEngine;
import js.runtime.JSObjectPtr;

public class App {
  public static void main(String[] args) throws Exception {
    JavaScriptEngine engine = new JavaScriptEngine();
    engine.setHeapSize(100);

    JSObjectPtr object = engine.newEmptyObject();
    object.set(engine.newString("property"), engine.newNumber(100));
    System.out.println(object.get(engine.newString("property")).toJSNumberPtr().value());
  }
}
