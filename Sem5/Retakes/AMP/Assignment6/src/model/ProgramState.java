package model;

import exception.EmptyStackException;
import model.adt.Dict;
import model.adt.Heap;
import model.adt.List;
import model.adt.Stack;
import model.statement.Statement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState {

    private Stack<Statement> executionStack;
    private Dict<String, Value> symbolTable;
    private List<Value> output;
    private Statement originalProgram;
    private final Dict<StringValue, BufferedReader> fileTable;
    private final Heap<Value> heap;

    private final int id;

    private static AtomicInteger lastId = new AtomicInteger(0);

    public ProgramState(Stack<Statement> executionStack, Dict<String, Value> symbolTable, List<Value> output,
                        Statement originalProgram, Dict<StringValue, BufferedReader> fileTable, Heap<Value> heap){
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

    public Dict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public Stack<Statement> getExecutionStack() {
        return executionStack;
    }

    public Dict<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public List<Value> getOutput() {
        return output;
    }

    public Statement getOriginalProgram() {
        return originalProgram;
    }

    public Heap<Value> getHeap() {
        return heap;
    }

    public void setExecutionStack(Stack<Statement> newExecutionStack){
        this.executionStack = newExecutionStack;
    }

    public void setSymbolTable(Dict<String, Value> newSymbolTable){
        this.symbolTable = newSymbolTable;
    }

    public void setOutput(List<Value> newOutput){
        this.output = newOutput;
    }

    public boolean isNotCompleted(){
        return !(this.executionStack.isEmpty());
    }

    public ProgramState oneStepExecution(){
        Stack<Statement> executionStack = this.executionStack;
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
