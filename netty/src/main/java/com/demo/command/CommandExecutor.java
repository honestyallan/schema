package com.demo.command;

public class CommandExecutor {

  public void execute(Command command){
    command.execute(new CommandReceiverImpl());
  }
}
