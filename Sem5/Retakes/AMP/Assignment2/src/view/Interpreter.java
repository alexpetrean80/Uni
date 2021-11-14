package view;

import controller.Controller;
import model.ProgramState;
import model.adt.TLDict;
import model.adt.TLList;
import model.adt.TLStack;
import model.expression.ArithmeticExpression;
import model.expression.RelationalExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import repository.Repository;

public class Interpreter {
	private static Statement getExample1() {
        /*
        int v;
        v=2;
        Print(v)
        */

		var declaration = new VariableDeclaration(new IntType(), "v");
		var assignment = new AssignmentStatement("v", new ValueExpression(new IntValue(2)));

		return new CompoundStatement(
			new CompoundStatement(declaration, assignment),
			new PrintStatement(new VariableExpression("v"))
		);

	}

	private static Statement getExample2() {
        /*
        int a;
        int b;
        a=2+3*5;
        b=a+1;
        Print(b)
        */

		var declarationA = new VariableDeclaration(new IntType(), "a");
		var declarationB = new VariableDeclaration(new IntType(), "b");

		var assignmentA = new AssignmentStatement("a",
			new ArithmeticExpression('+',
				new ValueExpression(new IntValue(2)),
				new ArithmeticExpression('*',
					new ValueExpression(new IntValue(3)),
					new ValueExpression(new IntValue(5)))));
		var assignmentB = new AssignmentStatement("b",
			new ArithmeticExpression('+',
				new VariableExpression("a"),
				new ValueExpression(new IntValue(1))));

		return new CompoundStatement(declarationA,
			new CompoundStatement(declarationB,
				new CompoundStatement(assignmentA,
					new CompoundStatement(assignmentB,
						new PrintStatement(new VariableExpression("b"))
					)
				)
			)
		);
	}

	private static Statement getExample3() {
        /*
        bool a;
        int v;
        a=true;
        (If a
            Then v=2
        Else
        v=3);
        Print(v)
        */

		var declarationA = new VariableDeclaration(new BoolType(), "a");
		var declarationV = new VariableDeclaration(new IntType(), "v");

		var assignmentA = new AssignmentStatement("a", new ValueExpression(new BoolValue(true)));

		var ifStmt = new IfStatement(
			new VariableExpression("a"),
			new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
			new AssignmentStatement("v", new ValueExpression(new IntValue(3))));

		return new CompoundStatement(declarationA,
			new CompoundStatement(declarationV,
				new CompoundStatement(assignmentA,
					new CompoundStatement(ifStmt,
						new PrintStatement(new VariableExpression("v"))
					)
				)
			)
		);
	}

	private static Statement getExample4() {
        /*
        int a;
        int b;
        int c;
        bool d;

        a=5;
        b=3;
        c=9;
        d=true;

        (If d
            Print(a / b)
        ELSE
            Print(c / b))
        */

		var declarationA = new VariableDeclaration(new IntType(), "a");
		var declarationB = new VariableDeclaration(new IntType(), "b");
		var declarationC = new VariableDeclaration(new IntType(), "c");
		var declarationD = new VariableDeclaration(new BoolType(), "d");

		var assignmentA = new AssignmentStatement("a", new ValueExpression(new IntValue(5)));
		var assignmentB = new AssignmentStatement("b", new ValueExpression(new IntValue(3)));
		var assignmentC = new AssignmentStatement("c", new ValueExpression(new IntValue(9)));
		var assignmentD = new AssignmentStatement("d", new ValueExpression(new BoolValue(false)));

		var ifStmt = new IfStatement(new VariableExpression("d"),
			new PrintStatement(new ArithmeticExpression('/', new VariableExpression("a"), new VariableExpression("b"))),
			new PrintStatement(new ArithmeticExpression('/', new VariableExpression("c"), new VariableExpression("b")))
		);

		return new CompoundStatement(
			new CompoundStatement(declarationA,
				new CompoundStatement(declarationB,
					new CompoundStatement(declarationC,
						new CompoundStatement(declarationD,
							new CompoundStatement(assignmentA,
								new CompoundStatement(assignmentB,
									new CompoundStatement(assignmentC, assignmentD)
								)
							)
						)
					)
				)
			), ifStmt
		);
	}

	private static Statement getExample5() {
		/*
		int b = 10;
		int c = 8;
		bool d = (b > c);

		(If d
			Print(b)
		ELSE
		Print(c))
		*/

		var declarationB = new VariableDeclaration(new IntType(), "b");
		var assignmentB = new AssignmentStatement("b", new ValueExpression(new IntValue(10)));

		var declarationC = new VariableDeclaration(new IntType(), "c");
		var assignmentC = new AssignmentStatement("c", new ValueExpression(new IntValue(8)));

		var declarationD = new VariableDeclaration(new BoolType(), "d");
		var assignmentD = new AssignmentStatement("d",
			new RelationalExpression(">", new VariableExpression("b"),
				new VariableExpression("c")));

		var ifStmt = new IfStatement(
			new VariableExpression("d"),
			new PrintStatement(new VariableExpression("b")),
			new PrintStatement(new VariableExpression("c"))
		);

		return new CompoundStatement(declarationB,
			new CompoundStatement(assignmentB,
				new CompoundStatement(declarationC,
					new CompoundStatement(assignmentC,
						new CompoundStatement(declarationD,
							new CompoundStatement(assignmentD, ifStmt)
						)
					)
				)
			)
		);
	}

	private static ProgramState createProgramState(Statement stmt) {
		return new ProgramState(new TLStack<>(), new TLDict<>(), new TLList<>(),
			stmt);
	}

	public static void main(String[] argv) {
		var example1 = getExample1();
		var programStateExample1 = createProgramState(example1);
		var repository1 = new Repository();
		var controller1 = new Controller(repository1);
		controller1.addProgramState(programStateExample1);

		var example2 = getExample2();
		var programStateExample2 = createProgramState(example2);
		var repository2 = new Repository();
		var controller2 = new Controller(repository2);
		controller2.addProgramState(programStateExample2);

		var example3 = getExample3();
		var programStateExample3 = createProgramState(example3);
		var repository3 = new Repository();
		var controller3 = new Controller(repository3);
		controller3.addProgramState(programStateExample3);

		var example4 = getExample4();
		var programStateExample4 = createProgramState(example4);
		var repository4 = new Repository();
		var controller4 = new Controller(repository4);
		controller4.addProgramState(programStateExample4);


		Statement example5 = getExample5();
		var programStateExample5 = createProgramState(example5);
		var repository5 = new Repository();
		var controller5 = new Controller(repository5);
		controller5.addProgramState(programStateExample5);

		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1", example1.toString(), controller1));
		menu.addCommand(new RunExample("2", example2.toString(), controller2));
		menu.addCommand(new RunExample("3", example3.toString(), controller3));
		menu.addCommand(new RunExample("4", example4.toString(), controller4));
		menu.addCommand(new RunExample("5", example5.toString(), controller5));
		menu.show();
	}
}
