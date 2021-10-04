package model.interfaces;

import model.containers.*;

public interface ProgramState {
    Stack<Statement> getExecStack();
    List<Value> getOutput();
    Dict<String, Value> getSymbolTable();
}
