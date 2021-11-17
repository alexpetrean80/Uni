package view;

import controller.Controller;
import model.ProgramState;
import model.adt.TLDict;
import model.adt.TLList;
import model.adt.TLStack;
import model.statement.Statement;
import repository.Repository;

public class Interpreter {
    private static ProgramState getProgramState(Statement stmt) {
        return new ProgramState(new TLStack<>(), new TLDict<>(), new TLList<>(),
                stmt, new TLDict<>());
    }

    public static void main(String[] argv) {
        var example1 = Examples.getExample1();
        var programStateExample1 = getProgramState(example1);
        var repository1 = new Repository("log1.txt");
        var controller1 = new Controller(repository1);
        controller1.addProgramState(programStateExample1);

        var example2 = Examples.getExample2();
        var programStateExample2 = getProgramState(example2);
        var repository2 = new Repository("log2.txt");
        var controller2 = new Controller(repository2);
        controller2.addProgramState(programStateExample2);

        var example3 = Examples.getExample3();
        var programStateExample3 = getProgramState(example3);
        var repository3 = new Repository("log3.txt");
        var controller3 = new Controller(repository3);
        controller3.addProgramState(programStateExample3);

        var example4 = Examples.getExample4();
        var programStateExample4 = getProgramState(example4);
        var repository4 = new Repository("log4.txt");
        var controller4 = new Controller(repository4);
        controller4.addProgramState(programStateExample4);

        var example5 = Examples.getExample5();
        var programStateExample5 = getProgramState(example5);
        var repository5 = new Repository("log5.txt");
        var controller5 = new Controller(repository5);
        controller5.addProgramState(programStateExample5);

        var menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "int v; v=2;Print(v)", controller1));

        menu.addCommand(new RunExample("2", "int a;int b; a=2+3*5;b=a+1;Print(b)", controller2));
        menu.addCommand(new RunExample("3", "bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)", controller3));
        menu.addCommand(new RunExample("4", "int a; int b; int c; bool d; a=6; b=3; c=9; d=true; (If b>a Print(a / b) ELSE Print(c / b))", controller4));
        menu.addCommand(new RunExample("5", "string varf; varf='test.in'; openRFile(varf);int varc;readFile(varf,varc);print(varc);readFile(varf,varc);print(varc);closeRFile(varf);", controller5));
        menu.show();
    }
}
