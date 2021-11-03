package model.statement;

import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IList;
import model.expression.Expression;
import model.type.Type;
import model.value.Value;

public class PrintStatement implements Statement {
    private final Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IList<Value> output = programState.getOutput();
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        synchronized (programState.getOutput()) {
            output.addToEnd(this.expression.evaluate(symbolTable, heap));
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnvironment) {
        this.expression.typeCheck(typeEnvironment);

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "print(" + this.expression.toString() + ")";
    }

}
