package com.demo.command;

public class CommandB implements Command{

  public void execute(CommandReceiver receiver) {
    receiver.doSomethingB();
  }
}
