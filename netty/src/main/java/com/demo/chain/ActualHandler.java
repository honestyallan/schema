package com.demo.chain;

public class ActualHandler extends Handler{

  @Override
  public void execute(Request request) {
    request.doSomething();
  }
}
