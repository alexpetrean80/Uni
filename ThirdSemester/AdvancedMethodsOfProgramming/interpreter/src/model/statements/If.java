package model.statements;

import model.interfaces.Expression;
import model.interfaces.ProgramState;
import model.interfaces.Statement;
import model.types.Bool;

public class If implements Statement {
    final private Expression expression;
    final private Statement then, otherwise;

    public If(Expression expression, Statement then, Statement otherwise) {
        this.expression = expression;
        this.then = then;
        this.otherwise = otherwise;
    }

    @Override
    public void execute(ProgramState state) throws Exception {
        var stack = state.getExecStack();
        var value = expression.evaluate(state.getSymbolTable());

        if (!value.getType().isOf(new Bool())) {
            throw new Exception("Type of expression not boolean");
        }
        var isTrue = ((model.values.Bool) value).getValue();
        Statement result;
        if (isTrue) {
            result = then;
        } else {
            result = otherwise;
        }
        stack.push(result);
    }

    @Override
    public String toString() {
        return "if(" + expression.toString() + ") then {" + then.toString() + "} else }" + otherwise.toString() + "}";
    }
}
