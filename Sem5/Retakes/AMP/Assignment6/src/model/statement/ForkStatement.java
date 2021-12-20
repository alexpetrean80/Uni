package model.statement;

import model.ProgramState;
import model.adt.*;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class ForkStatement implements Statement{

    private final Statement statement;

    public ForkStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) {

        var executionStack = new TLStack<Statement>();
        var newSymbolTable = new TLDict<String, Value>();
        programState.getSymbolTable().getContent()
                .forEach((key, value) -> newSymbolTable.update(key, value.deepCopy()));

        synchronized (programState.getFileTable()) {
            synchronized (programState.getHeap()) {
                synchronized (programState.getOutput()) {
                    Dict<StringValue, BufferedReader> fileTable = programState.getFileTable();
                    Heap<Value> heap = programState.getHeap();
                    List<Value> output = programState.getOutput();

                    return new ProgramState(executionStack, newSymbolTable, output, this.statement, fileTable, heap);
                }
            }
        }
    }

    @Override
    public Statement deepCopy() {
        return new ForkStatement(this.statement.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        // type doesn't need deepcopy and string neither

        this.statement.typeCheck(new TLDict<>(typeEnvironment.getContent()));

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement + ")";
    }
}
