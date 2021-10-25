package model;

import model.containers.Dict;
import model.containers.List;
import model.containers.Stack;
import model.interfaces.Statement;
import model.interfaces.Value;

public class ProgramState implements model.interfaces.ProgramState {
    final Stack<Statement> executionStack;
    final List<Value> output;
    final Dict<String, Value> symbolTable;

    public ProgramState(Stack<Statement> executionStack, List<Value> output, Dict<String, Value> symbolTable) {
        this.executionStack = executionStack;
        this.output = output;
        this.symbolTable = symbolTable;
    }

    @Override
    public Stack<Statement> getExecStack() {
        return executionStack;
    }

    @Override
    public List<Value> getOutput() {
        return output;
    }

    @Override
    public Dict<String, Value> getSymbolTable() {
        return symbolTable;
    }
}
