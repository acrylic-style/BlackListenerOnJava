package tk.rht0910.blacklistener.util;

import tk.rht0910.blacklistener.commands.*;

public class Commands {
    public Ping ping = new Ping();
    public Lookup lookup = new Lookup();
    public Leave leave = new Leave();
    public Members members = new Members();
    public Eval eval = new Eval();
    public Help help = new Help();

    public Ping getPing() { return ping; }
    public Lookup getLookup() { return lookup; }
    public Leave getLeave() { return leave; }
    public Members getMembers() { return members; }
    public Eval getEval() { return eval; }
    public Help getHelp() { return help; }
}
