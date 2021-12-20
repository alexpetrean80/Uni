package view;

import controller.Controller;
import model.ProgramState;
import model.adt.TLDict;
import model.adt.TLHeap;
import model.adt.TLList;
import model.adt.TLStack;
import model.expression.*;
import model.statement.*;
import model.type.*;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.Repository;

import java.io.BufferedReader;

public class Interpretor {
    public static ProgramState getProgState(Statement s) {
        return new ProgramState(new TLStack<>(), new TLDict<>(), new TLList<>(), s, new TLDict<>(), new TLHeap<>());
    }

    public static Statement getExample1() {
        return new CompoundStatement(
                new VariableDeclaration(new IntType(), "v"),
                new CompoundStatement(
                        new VariableDeclaration(new RefType(new IntType()), "a"),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
    }

    public static Statement getExample2() {
        return new CompoundStatement(
                new VariableDeclaration(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclaration(new RefType(new IntType()), "b"),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("b", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new AssignmentStatement("a", new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(
                                                                        new HeapWritingStatement("b", new ValueExpression(new IntValue(20))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("a")),
                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("b"))))))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("a", new ValueExpression(new IntValue(3))),
                                                                        new CompoundStatement(
                                                                                new HeapWritingStatement("b", new ValueExpression(new IntValue(30))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("a")),
                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("b"))))))),
                                                        new PrintStatement(new VariableExpression("a"))))))));
    }

    public static Statement getExample3() {
        return new CompoundStatement(
                new VariableDeclaration(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclaration(new StringType(), "b"),
                        new AssignmentStatement("a", new VariableExpression("b"))
                )
        );
    }
    public static void main(String[] argv) {

        //(int v; (Ref int  a; (v = 10; (new(a, 22); (fork((wH(a, 30); (v = 32; (print(v); print(rH(a)))))); (print(v); print(rH(a))))))))
        var ex1 = getExample1();
        var progState1 = getProgState(ex1);
        var repo1 = new Repository(progState1, "log1.txt");
        var ctrl1 = new Controller(repo1);

        /// int a; Ref int b; a = 1; new(b,10); fork(a = 2; wH(b,20); print(a); print(rH(b))); fork(a=3; wH(b,30); print(a); print(rH(b))); print(a)
        var ex2 = getExample2();
        var progState2 = getProgState(ex2);
        var repo2 = new Repository(progState2, "log2.txt");
        var ctrl2 = new Controller(repo2);

        var ex3 = getExample3();
        var progState3 = getProgState(ex3);
        var repo3 = new Repository(progState3, "log3.txt");
        var ctrl3 = new Controller(repo3);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));

        menu.show();
    }
}
