package com.demo.command;

public class Client {

  public static void main(String[] args) {
    CommandExecutor executor = new CommandExecutor();
    CommandA commandA = new CommandA();
    executor.execute(commandA);
    CommandB commandB = new CommandB();
    executor.execute(commandB);
  }
}
