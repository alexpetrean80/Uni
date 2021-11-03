package view;

import controller.Controller;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.expression.ArithmeticExpression;
import model.expression.RelationalExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String []argv){
        // int v; v=2;Print(v)
        Statement example1 =  new CompoundStatement(
                new CompoundStatement(new VariableDeclaration(new IntType(), "v"),
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2)))),
                new PrintStatement(new VariableExpression("v")));
        ProgramState programStateExample1 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example1, new MyDictionary<StringValue, BufferedReader>());
        Repository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgramState(programStateExample1);

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        Statement example2 = new CompoundStatement(
                        new VariableDeclaration(new IntType(), "a"),
                        new CompoundStatement(
                                new VariableDeclaration(new IntType(), "b"),
                                new CompoundStatement(
                                        new AssignmentStatement("a",
                                                new ArithmeticExpression('+',
                                                        new ValueExpression(new IntValue(2)),
                                                        new ArithmeticExpression('*',
                                                                new ValueExpression(new IntValue(3)),
                                                                new ValueExpression(new IntValue(5))))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b",
                                                        new ArithmeticExpression('+',
                                                                new VariableExpression("a"),
                                                                new ValueExpression(new IntValue(1)))),
                                                new PrintStatement(new VariableExpression("b"))))));

        ProgramState programStateExample2 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example2, new MyDictionary<StringValue, BufferedReader>());
        Repository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgramState(programStateExample2);

        // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        Statement example3 = new CompoundStatement(
                        new VariableDeclaration(new BoolType(), "a"),
                        new CompoundStatement(
                                new VariableDeclaration(new IntType(), "v"),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(
                                                new IfStatement(
                                                        new VariableExpression("a"),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                                new PrintStatement(new VariableExpression("v"))))));

        ProgramState programStateExample3 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example3, new MyDictionary<StringValue, BufferedReader>());
        Repository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgramState(programStateExample3);

        //int a; int b; int c; bool d; a=6; b=3; c=9; d=true; (If d Print(a / b) ELSE Print(c / b))
        Statement example4 =  new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "a"),
                        new CompoundStatement(new VariableDeclaration(new IntType(), "b"),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "c"),
                                        new CompoundStatement(new VariableDeclaration(new BoolType(), "d"),
                                                new CompoundStatement(
                                                        new AssignmentStatement("a", new ValueExpression(new IntValue(6))),
                                                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(0))),
                                                                new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(9))),
                                                                        new AssignmentStatement("d", new ValueExpression(new BoolValue(false)))))))))),
                new IfStatement(new VariableExpression("d"),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("a"),new VariableExpression("b"))),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("c"), new VariableExpression("b")))));

        ProgramState programStateExample4 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example4, new MyDictionary<StringValue, BufferedReader>());
        Repository repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgramState(programStateExample4);

        //"5. string varf; varf='test.in'; openRFile(varf);int varc;readFile(varf,varc);print(varc);readFile(varf,varc);print(varc);closeRFile(varf);
        Statement example5 =  new CompoundStatement(new VariableDeclaration(new StringType(), "varf"),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "varc"),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))))))))));

        ProgramState programStateExample5 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example5, new MyDictionary<StringValue, BufferedReader>());
        Repository repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgramState(programStateExample5);

        //int b = 10; int c = 8; bool d = (b > c)   (If d Print(b) ELSE Print(c))
        Statement example6 =
                new CompoundStatement(new VariableDeclaration(new IntType(), "b"),
                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "c"),
                                        new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(8))),
                                                new CompoundStatement(new VariableDeclaration(new BoolType(), "d"),
                                                        new CompoundStatement(new AssignmentStatement("d",
                                                                new RelationalExpression(">", new VariableExpression("b"),
                                                                        new VariableExpression("c"))),
                                                                new IfStatement(new VariableExpression("d"),
                                                                        new PrintStatement(new VariableExpression("b")),
                                                                        new PrintStatement(new VariableExpression("c")))))))));

        ProgramState programStateExample6 = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                example6, new MyDictionary<StringValue, BufferedReader>());
        Repository repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6);
        controller6.addProgramState(programStateExample6);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.show();
    }
}
