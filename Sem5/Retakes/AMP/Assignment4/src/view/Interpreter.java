package view;

import controller.Controller;
import model.ProgramState;
import model.adt.TLDict;
import model.adt.TLHeap;
import model.adt.TLList;
import model.adt.TLStack;
import model.statement.Statement;
import repository.Repository;

public class Interpreter {
    private static ProgramState getProgramState(Statement stmt) {
        return new ProgramState(new TLStack<>(), new TLDict<>(), new TLList<>(),
                stmt, new TLDict<>(), new TLHeap<>());
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

        var example6 = Examples.getExample6();
        var programStateExample6 = getProgramState(example6);
        var repository6 = new Repository("log6.txt");
        var controller6 = new Controller(repository6);
        controller6.addProgramState(programStateExample6);

        var example7 = Examples.getExample7();
        var programStateExample7 = getProgramState(example7);
        var repository7 = new Repository("log7.txt");
        var controller7 = new Controller(repository7);
        controller7.addProgramState(programStateExample7);

        var example8 = Examples.getExample8();
        var programStateExample8 = getProgramState(example8);
        var repository8 = new Repository("log8.txt");
        var controller8 = new Controller(repository8);
        controller8.addProgramState(programStateExample8);

        var example9 = Examples.getExample9();
        var programStateExample9 = getProgramState(example9);
        var repository9 = new Repository("log9.txt");
        var controller9 = new Controller(repository9);
        controller9.addProgramState(programStateExample9);

        var example10 = Examples.getExample10();
        var programStateExample10 = getProgramState(example10);
        var repository10 = new Repository("log10.txt");
        var controller10 = new Controller(repository10);
        controller10.addProgramState(programStateExample10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.addCommand(new RunExample("7", example7.toString(), controller7));
        menu.addCommand(new RunExample("8", example8.toString(), controller8));
        menu.addCommand(new RunExample("9", example9.toString(), controller9));
        menu.addCommand(new RunExample("10", example10.toString(), controller10));
        menu.show();
    }
}
