package com.demo.chain;

import java.util.ArrayList;

public class Client {

  public static void main(String[] args) {
    ArrayList<Handler> handlers = new ArrayList<Handler>();
    handlers.add(new HandlerA());
    handlers.add(new HandlerB());
    handlers.add(new ActualHandler());
    Handler first = setNext(handlers);
    first.execute(new Request());
  }

  private static Handler setNext(ArrayList<Handler> handlers) {
    for (int i = 0; i < handlers.size()-1; i++) {
      Handler handler = handlers.get(i);
      Handler next = handlers.get(i + 1);
      handler.setNext(next);
    }
    return handlers.get(0);
  }
}
