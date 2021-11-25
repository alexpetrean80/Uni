package tests;

public class TestStatements {

    // @Test
//    public void testCompoundStatement(){
//        IStack<Statement> stack = new MyStack<Statement>(Collections.emptyList());
//        IDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        IList<Value> out = new MyList<Value>();
//        Statement out1 = new PrintStatement(new ValueExpression(new IntValue(10)));
//        Statement out2 = new PrintStatement(new ValueExpression(new IntValue(100)));
//
//        Statement ms = new CompoundStatement(out1, out2);
//
//        ProgramState pst = new ProgramState(stack, dict, out, out2);
//
//        ms.execute(pst);
//        assertEquals(stack.top(), out1);
//    }
//
//    @Test
//    public void testPrintStatement(){
//        IStack<Statement> stack = new MyStack<Statement>(Collections.emptyList());
//        IDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        IList<Value> out = new MyList<Value>();
//        Statement out1 = new PrintStatement(new ValueExpression(new IntValue(10)));
//        Statement out2 = new PrintStatement(new ValueExpression(new IntValue(100)));
//        Statement compSt = new CompoundStatement(out1, out2);
//        ProgramState pst = new ProgramState(stack, dict, out, compSt);
//
//        out1.execute(pst);
//        out2.execute(pst);
//
//        assertEquals(toInteger(pst.getOutput().get(0)), 10);
//    }
//
//    @Test
//    public void testVariableDeclaration(){
//        IStack<Statement> stack = new MyStack<Statement>(Collections.emptyList());
//        IDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        IList<Value> out = new MyList<Value>();
//        Statement statement1 = new VariableDeclaration(new IntType(), "a");
//        Statement statement2 = new VariableDeclaration(new BoolType(), "b");
//        Statement comp = new CompoundStatement(statement1,statement2);
//        ProgramState pst = new ProgramState(stack, dict, out, comp);
//        stack.push(statement1);
//        stack.push(statement2);
//        stack.pop().execute(pst);
//        stack.pop().execute(pst);
//
//        assertTrue(dict.containsKey("a"));
//        assertTrue(dict.containsKey("b"));
//    }
//
//    @Test
//    public void testIfStatement(){
//        IStack<Statement> stack = new MyStack<Statement>(Collections.emptyList());
//        IDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        IList<Value> out = new MyList<Value>();
//        Statement st1 = new PrintStatement(new ValueExpression(new IntValue(20)));
//        Statement st2 = new PrintStatement(new ValueExpression(new IntValue(25)));
//        Statement comp = new CompoundStatement(st1,st2);
//        ProgramState pst = new ProgramState(stack, dict, out, comp);
//
//        Expression e = new LogicalExpression("and", new ValueExpression(new BoolValue(true)),
//                new ValueExpression(new BoolValue(false)));
//        Statement statement = new IfStatement(e, st1, st2);
//
//        statement.execute(pst);
//        assertEquals(toInteger(stack.top().execute(pst).getOutput().get(0)), 25);
//    }
//
//    @Test
//    public void testAssignmentStatement(){
//        IStack<Statement> stack = new MyStack<Statement>(Collections.emptyList());
//        IDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        IList<Value> out = new MyList<Value>();
//        Statement stvar = new VariableDeclaration(new IntType(), "bia");
//        Statement assignStVar = new AssignmentStatement("bia", new ValueExpression(new IntValue(20)));
//        Statement op = new CompoundStatement(stvar,assignStVar);
//        ProgramState pst = new ProgramState(stack, dict, out, op);
//
//        stvar.execute(pst);
//        assignStVar.execute(pst);
//
//        assertEquals(toInteger(dict.lookUp("bia")), 20);
//    }
}
