package com.demo.chain;

public class HandlerA extends Handler{

  @Override
  public void execute(Request request) {
    System.out.println("请求处理者A处理请求");
    next.execute(request);
  }
}
