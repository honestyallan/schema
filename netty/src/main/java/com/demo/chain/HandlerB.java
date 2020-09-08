package com.demo.chain;

public class HandlerB extends Handler {

  @Override
  public void execute(Request request) {
    System.out.println("请求处理者B处理请求");
    next.execute(request);
  }
}
