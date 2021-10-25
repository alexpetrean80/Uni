package model.expressions;

import model.containers.Dict;
import model.interfaces.Expression;
import model.interfaces.Value;
import model.operations.ArithmeticOperation;

public class Arithmetic implements Expression {
    final Expression first, second;
    final ArithmeticOperation op;

    public Arithmetic(Expression first, Expression second, ArithmeticOperation op) {
        this.first = first;
        this.second = second;
        this.op = op;
    }

    @Override
    public Value evaluate(Dict<String, Value> symbolTable) throws Exception {
        Value firstVal = first.evaluate(symbolTable);
        if (!firstVal.getType().isOf(new model.types.Int())) {
            throw new Exception("first operand is not an integer");
        }
        Value secondVal = second.evaluate(symbolTable);
        if (!secondVal.getType().isOf(new model.types.Int())) {
            throw new Exception("second operand is not an integer");
        }

        int firstNumber = ((model.values.Int) firstVal).getValue();
        int secondNumber = ((model.values.Int) secondVal).getValue();
        int result = 0;
        switch (op) {
            case ADD -> result = firstNumber + secondNumber;
            case SUB -> result = firstNumber - secondNumber;
            case MUL -> result = firstNumber * secondNumber;
            case DIV -> result = firstNumber / secondNumber;
            case MOD -> result = firstNumber % secondNumber;
        }
        return new model.values.Int(result);
    }

    @Override
    public String toString() {
        char opSymbol;
        switch (op) {

            case ADD -> opSymbol = '+';
            case SUB -> opSymbol = '-';
            case MUL -> opSymbol = '*';
            case DIV -> opSymbol = '/';
            case MOD -> opSymbol = '%';
            default -> throw new IllegalStateException("Unexpected value: " + op);
        }
        return first.toString() + opSymbol + second.toString();
    }
}