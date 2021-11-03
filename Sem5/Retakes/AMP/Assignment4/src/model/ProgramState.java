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
    private final IDictionary<StringValue, BufferedReader> fileTable;
    private final IHeap<Value> heap;

    public ProgramState(IStack<Statement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output,
                        Statement originalProgram, MyDictionary<StringValue, BufferedReader> fileTable, MyHeap<Value> heap){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
        this.fileTable = fileTable;
        this.heap = heap;    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
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

    public IHeap<Value> getHeap() {
        return heap;
    }

    public void setExecutionStack(IStack<Statement> newExecutionStack){
        this.executionStack = newExecutionStack;
    }

    public void setSymbolTable(IDictionary<String, Value> newSymbolTable){
        this.symbolTable = newSymbolTable;
    }

    public void setOutput(IList<Value> newOutput){
        this.output = newOutput;
    }

    public void setOriginalProgram(Statement newOriginalProgram){
        this.originalProgram = newOriginalProgram;
    }

    @Override
    public String toString() {
        return  "Stack\n" + this.executionStack.toString() + "\n"
                + "Heap" + this.heap.toString() + "\n"
                + "SymbolTable\n" + this.symbolTable.toString() + "\n"
                + "Output\n" + this.output.toString()+"\n"
                + "FileTable\n" + this.fileTable.toString() + "\n"
                + "------------";
          }

}
