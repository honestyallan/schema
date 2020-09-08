package com.demo.command;

/**
 * 命令接口
 */
public interface Command {

  /**
   * 执行命令，参数为命令接收人
   * @param receiver
   */
  void execute(CommandReceiver receiver);
}
