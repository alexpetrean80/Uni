package tests;
import model.adt.MyDictionary;
import model.expression.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import org.junit.Test;

import static model.value.Value.*;
import static org.junit.Assert.*;


public class TestExpressions {


//    @Test
//    public void testVariableExpression(){
//
//        MyDictionary<String, Value> dict = new MyDictionary<String, Value>();
//        dict.update("a", new IntValue(2));
//        dict.update("v2", new BoolValue(true));
//
//        Expression e1 = new VariableExpression("a");
//        Expression e2 = new VariableExpression("v2");
//        Expression e3 = new VariableExpression("v4");
//
//        assertEquals(toInteger(e1.evaluate(dict)), 2);
//        assertTrue(toBoolean(e2.evaluate(dict)));
//    }
//
//    @Test
//    public void testValueExpression(){
//        MyDictionary<String, Value> dict = new MyDictionary<String, Value>();
//
//        Expression e1 = new ValueExpression(new IntValue(5));
//        Expression e2 = new ValueExpression(new BoolValue(false));
//
//        assertEquals(toInteger(e1.evaluate(dict)),5);
//        assertFalse(toBoolean(e2.evaluate(dict)));
//    }
//
//    @Test
//    public void testLogicalExpression(){
//        MyDictionary<String, Value> dict = new MyDictionary<String, Value>();
//
//        Expression e1 = new ValueExpression(new BoolValue(false));
//        Expression e2 = new ValueExpression(new BoolValue(true));
//        Expression e4 = new LogicalExpression("and",e1, e2);
//        Expression e5 = new LogicalExpression("or", e1, e2);
//
//        assertFalse(toBoolean(e4.evaluate(dict)));
//        assertTrue(toBoolean(e5.evaluate(dict)));
//    }
//
//    @Test
//    public void testArithmeticExpression(){
//        MyDictionary<String, Value> dict = new MyDictionary<String, Value>();
//
//        Expression e1 = new ValueExpression(new IntValue(9));
//        Expression e2 = new ValueExpression(new IntValue(3));
//
//        dict.update("n1", e1.evaluate(dict));
//        dict.update("n3", e2.evaluate(dict));
//
//
//        Expression e3 = new VariableExpression("n1");
//        Expression e4 = new VariableExpression("n3");
//
//        Expression e5 = new ArithmeticExpression('+', e3, e4);
//        Expression e6 = new ArithmeticExpression('-', e3, e4);
//        Expression e7 = new ArithmeticExpression('*', e3, e4);
//        Expression e8 = new ArithmeticExpression('/', e3, e4);
//
//        assertEquals(toInteger(e5.evaluate(dict)), 12);
//        assertEquals(toInteger(e6.evaluate(dict)), 6);
//        assertEquals(toInteger(e7.evaluate(dict)), 27);
//        assertEquals(toInteger(e8.evaluate(dict)), 3);
//    }
}
