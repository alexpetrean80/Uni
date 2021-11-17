package view;

import model.adt.TLStack;
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

import javax.swing.plaf.nimbus.State;

public class Examples {
    // int v; v=2;Print(v)
    static Statement getExample1() {
        var statements = new TLStack<Statement>();

        statements.push(new VariableDeclaration(new IntType(), "v"));
        statements.push(new AssignmentStatement("v", new ValueExpression(new IntValue(2))));
        statements.push(new PrintStatement(new VariableExpression("v")));

        return new CompoundStatement(statements);
    }

    //    int a;int b; a=2+3*5;b=a+1;Print(b)
    static Statement getExample2() {
        var statements = new TLStack<Statement>();
        statements.push(new VariableDeclaration(new IntType(), "a"));
        statements.push(new VariableDeclaration(new IntType(), "b"));
        statements.push(new AssignmentStatement("a",
                new ArithmeticExpression('+',
                        new ValueExpression(new IntValue(2)),
                        new ArithmeticExpression('*',
                                new ValueExpression(new IntValue(3)),
                                new ValueExpression(new IntValue(5))))));
        statements.push(new AssignmentStatement("b",
                new ArithmeticExpression('+',
                        new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)))));
        statements.push(
                new PrintStatement(new VariableExpression("b")));

        return new CompoundStatement(statements);
    }

    // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
    static Statement getExample3() {
        var statements = new TLStack<Statement>();
        statements.push(new VariableDeclaration(new BoolType(), "a"));
        statements.push(new VariableDeclaration(new IntType(), "v"));
        statements.push(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))));
        statements.push(new IfStatement(
                new VariableExpression("a"),
                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))));
        statements.push(new PrintStatement(new VariableExpression("v")));

        return new CompoundStatement(statements);
    }

    //int a; int b; int c; bool d; a=6; b=3; c=9; d=true; (If b>a Print(a / b) ELSE Print(c / b))
    static Statement getExample4() {
        var statements = new TLStack<Statement>();

        statements.push(new VariableDeclaration(new IntType(), "a"));
        statements.push(new VariableDeclaration(new IntType(), "b"));
        statements.push(new VariableDeclaration(new IntType(), "c"));
        statements.push(new VariableDeclaration(new BoolType(), "d"));
        statements.push(new AssignmentStatement("a", new ValueExpression(new IntValue(6))));
        statements.push(new AssignmentStatement("b", new ValueExpression(new IntValue(3))));
        statements.push(new AssignmentStatement("c", new ValueExpression(new IntValue(9))));
        statements.push(new AssignmentStatement("d", new ValueExpression(new BoolValue(false))));
        statements.push(
                new IfStatement(new RelationalExpression(">", new VariableExpression("b"), new VariableExpression("a")),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("a"), new VariableExpression("b"))),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("c"), new VariableExpression("b")))
                )
        );

        return new CompoundStatement(statements);
    }

    //"5. string varf; varf='test.in'; openRFile(varf);int varc;readFile(varf,varc);print(varc);readFile(varf,varc);print(varc);closeRFile(varf);
    static Statement getExample5() {
        var statements = new TLStack<Statement>();

        statements.push(new VariableDeclaration(new StringType(), "varf"));
        statements.push(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))));
        statements.push(new OpenRFileStatement(new VariableExpression("varf")));
        statements.push(new VariableDeclaration(new IntType(), "varc"));
        statements.push(new ReadFileStatement(new VariableExpression("varf"), "varc"));
        statements.push(new PrintStatement(new VariableExpression("varc")));
        statements.push(new ReadFileStatement(new VariableExpression("varf"), "varc"));
        statements.push(new PrintStatement(new VariableExpression("varc")));
        statements.push(new CloseRFileStatement(new VariableExpression("varf")));

        return new CompoundStatement(statements);
    }
}
