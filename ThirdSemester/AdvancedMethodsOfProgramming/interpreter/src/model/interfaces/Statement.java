package model.interfaces;

public interface Statement {
    void execute(ProgramState state) throws Exception;
}
