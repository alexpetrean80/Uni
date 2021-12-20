package model.statement;

import exception.NotBooleanException;
import exception.TypeCheckException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.adt.Stack;
import model.adt.TLDict;
import model.expression.Expression;
import model.type.BoolType;
import model.type.Type;
import model.value.Value;

import static model.value.Value.toBoolean;

public class IfStatement implements Statement{

    private final Statement thenStatement;
    private final Statement elseStatement;
    private final  Expression expression;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement){
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
        this.expression = expression;
    }

    public Statement getThenStatement(){
        return this.thenStatement;
    }

    public Statement getElseStatement(){
        return this.elseStatement;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return "(IF(" + this.expression.toString() + ") THEN(" + this.thenStatement.toString() + ") ELSE ("
                + this.elseStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        Stack<Statement> stack = programState.getExecutionStack();
        Heap<Value> heap = programState.getHeap();

        Value condition = this.expression.evaluate(programState.getSymbolTable(), heap);
        if (!(condition.getType() instanceof BoolType))
            throw new NotBooleanException();

        if (toBoolean(condition))
            stack.push(this.thenStatement);
        else
            stack.push(this.elseStatement);

        return null;
    }

    @Override
    public Statement deepCopy() {
        return new IfStatement(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        if (!(this.expression.typeCheck(typeEnvironment).equals(new BoolType()))){
            throw new TypeCheckException("The condition of IF is not a boolean.");
        }

        this.thenStatement.typeCheck(new TLDict<String, Type>(typeEnvironment.getContent()));
        this.elseStatement.typeCheck(new TLDict<String, Type>(typeEnvironment.getContent()));

         return typeEnvironment;
    }
}
