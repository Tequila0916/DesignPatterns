package org.whisky.command_pattern;

import java.util.ArrayList;

/**
 * @ClassName Waiter
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2321:06
 * @Version 1.0
 */
public class Waiter {
    private ArrayList<Command> commands;

    public Waiter() {
        commands = new ArrayList<>();
    }

    public Waiter(ArrayList<Command> commands) {
        this.commands = commands;
    }
    public void setCommands(Command command) {
        commands.add(command);
    }
    public void orderUp(){
        System.out.println("服务员: 叮咚,有新的订单,请厨师开始制作......");
        for (Command cmd : commands) {
            if(cmd != null){
                cmd.execute();
            }
        }
    }
}
