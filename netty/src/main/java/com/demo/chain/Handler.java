package com.demo.chain;

/**
 * 请求处理者接口
 */
public abstract class Handler {

  protected  Handler next;

  public void setNext(Handler next){
    this.next = next;
  }

  public abstract void execute(Request request);
}
