package com.demo.command;

public class CommandA implements Command {

  public void execute(CommandReceiver receiver) {
    receiver.doSomethingA();
  }
}
