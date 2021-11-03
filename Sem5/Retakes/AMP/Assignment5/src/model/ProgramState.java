package model;

import exception.EmptyStackException;
import model.adt.*;
import model.statement.Statement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState {

    private IStack<Statement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> output;
    private Statement originalProgram;
    private final IDictionary<StringValue, BufferedReader> fileTable;
    private final IHeap<Value> heap;

    private final int id;

    private static AtomicInteger lastId = new AtomicInteger(0);

    public ProgramState(IStack<Statement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output,
                        Statement originalProgram, IDictionary<StringValue, BufferedReader> fileTable, IHeap<Value> heap){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = lastId.incrementAndGet();
    }
    public int getId() {
        return id;
    }

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

    public boolean isNotCompleted(){
        return !(this.executionStack.isEmpty());
    }

    public ProgramState oneStepExecution(){
        IStack<Statement> executionStack = this.executionStack;
        if (executionStack.isEmpty()){
            throw new EmptyStackException("The execution stack is empty");
        }

        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public void setOriginalProgram(Statement newOriginalProgram){
        this.originalProgram = newOriginalProgram;
    }

    @Override
    public String toString() {
        return  "Thread id: " + this.getId() + "\n"
                +"Stack\n" + this.executionStack.toString() + "\n"
                + "Heap" + this.heap.toString() + "\n"
                + "SymbolTable\n" + this.symbolTable.toString() + "\n"
                + "Output\n" + this.output.toString()+"\n"
                + "FileTable\n" + this.fileTable.toString() + "\n"
                + "------------\n";
          }

}
