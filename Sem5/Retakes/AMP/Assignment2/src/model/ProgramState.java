package model;

import model.adt.*;
import model.statement.Statement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class ProgramState {

    private IStack<Statement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> output;
    private Statement originalProgram;

    public ProgramState(IStack<Statement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output,
                        Statement originalProgram){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    public IStack<Statement> getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public IList<Value> getOutput() {
        return output;
    }

    public Statement getOriginalProgram() {
        return originalProgram;
    }

    @Override
    public String toString() {
         //"Original\n" + this.originalProgram.toString() + "\n"
        return  "Stack\n" + this.executionStack.toString() + "\n"
                + "SymbolTable\n" + this.symbolTable.toString() + "\n"
                + "Output\n" + this.output.toString()+"\n"
                + "------------";
          }

}
