package model.expressions;

import model.values.Value;

public interface Expression {
    Value<?> eval();
}
