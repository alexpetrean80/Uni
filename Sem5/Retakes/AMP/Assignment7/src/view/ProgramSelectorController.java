package view;

import controller.Controller;
import exception.MyException;
import exception.TypeCheckException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ProgramState;
import model.adt.*;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.Repository;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramSelectorController {

    @FXML
    public Button startProgramButton;
    @FXML
    private ListView<Statement> listView;

    public void initialize(){
        try {
            this.listView.setItems(this.getStatementsListObs(this.getStatementsList()));
            this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        catch (Exception e){
            System.out.println(e.getMessage().toString());
        }
    }

    @FXML
    public void handleStartProgramButtonAction(ActionEvent event) {

        Statement st = listView.getSelectionModel().getSelectedItem();

        if (st != null) {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();

            ProgramState programState = new ProgramState(new MyStack<Statement>(), new MyDictionary<String, Value>(), new MyList<Value>(),
                    st, new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Value>());
            Repository repository = new Repository(programState, "log" + String.valueOf(selectedIndex + 1) + ".txt");
            Controller controller = new Controller(repository);

            try {
                controller.typeCheckOriginalProgram();

                FXMLLoader loader = new FXMLLoader();
                ProgramExecutionController executionController = new ProgramExecutionController(controller);
                loader.setController(executionController);

                loader.setLocation(getClass().getResource("ProgramExecution.fxml"));

                StackPane root = (StackPane) loader.load();
                Scene programExecutionScene = new Scene(root, 900, 700);
                Stage programExecutionWindow = new Stage();
                programExecutionWindow.setScene(programExecutionScene);
                programExecutionWindow.setTitle("Program Execution - Example " + String.valueOf(selectedIndex + 1));
                programExecutionWindow.show();
            } catch (TypeCheckException exception) {
                //System.out.println(exception.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Typecheck exception");
                alert.setContentText("Error: " + exception.getMessage());

                alert.showAndWait();
            } catch (Exception exception) {
                //System.out.println(exception.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Exception");
                alert.setContentText("Error: " + exception.getMessage());

                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Error: " + "no program was selected!");

            alert.showAndWait();
        }
    }

    private IList<Statement> getStatementsList(){
        IList<Statement> statementsList= new MyList<Statement>();

        // int v; v=2;Print(v)
        Statement example1 =  new CompoundStatement(
                new CompoundStatement(new VariableDeclaration(new IntType(), "v"),
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2)))),
                new PrintStatement(new VariableExpression("v")));

        statementsList.addToEnd(example1);

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        Statement example2 = new CompoundStatement(
                new VariableDeclaration(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "b"),
                        new CompoundStatement(
                                new AssignmentStatement("a",
                                        new ArithmeticExpression('+',
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression('*',
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(
                                        new AssignmentStatement("b",
                                                new ArithmeticExpression('+',
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));

        statementsList.addToEnd(example2);

        // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        Statement example3 = new CompoundStatement(
                new VariableDeclaration(new BoolType(), "a"),
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "v"),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        statementsList.addToEnd(example3);

        //int a; int b; int c; bool d; a=6; b=3; c=9; d=true; (If d Print(a / b) ELSE Print(c / b))
        Statement example4 =  new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "a"),
                        new CompoundStatement(new VariableDeclaration(new IntType(), "b"),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "c"),
                                        new CompoundStatement(new VariableDeclaration(new BoolType(), "d"),
                                                new CompoundStatement(
                                                        new AssignmentStatement("a", new ValueExpression(new IntValue(6))),
                                                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(9))),
                                                                        new AssignmentStatement("d", new ValueExpression(new BoolValue(false)))))))))),
                new IfStatement(new VariableExpression("d"),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("a"),new VariableExpression("b"))),
                        new PrintStatement(new ArithmeticExpression('/', new VariableExpression("c"), new VariableExpression("b")))));

        statementsList.addToEnd(example4);

        Statement example5 =  new CompoundStatement(new VariableDeclaration(new StringType(), "varf"),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "varc"),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))))))))));

        statementsList.addToEnd(example5);

        //int b = 10; int c = 8; bool d = (b > c)   (If d Print(b) ELSE Print(c))
        Statement example6 =
                new CompoundStatement(new VariableDeclaration(new IntType(), "b"),
                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new VariableDeclaration(new IntType(), "c"),
                                        new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(8))),
                                                new CompoundStatement(new VariableDeclaration(new BoolType(), "d"),
                                                        new CompoundStatement(new AssignmentStatement("d",
                                                                new RelationalExpression(">", new VariableExpression("b"),
                                                                        new VariableExpression("c"))),
                                                                new IfStatement(new VariableExpression("d"),
                                                                        new PrintStatement(new VariableExpression("b")),
                                                                        new PrintStatement(new VariableExpression("c")))))))));

        statementsList.addToEnd(example6);

        //Ref int v; new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        Statement example7 = new CompoundStatement(new VariableDeclaration(new RefType(new IntType()), "v"),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclaration(new RefType(new RefType(new IntType())),"a"),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));

        statementsList.addToEnd(example7);

        Statement example8 = new CompoundStatement(new VariableDeclaration(new RefType(new IntType()), "v"),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclaration(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(
                                                new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)))))))));

        statementsList.addToEnd(example8);

        Statement example9 = new CompoundStatement(new VariableDeclaration(new RefType(new IntType()), "v"),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+',
                                                new HeapReadingExpression(new VariableExpression("v")),
                                                new ValueExpression(new IntValue(5))))))));

        statementsList.addToEnd(example9);

        Statement example10 = new CompoundStatement(new VariableDeclaration(new RefType(new IntType()), "v"),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclaration(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));

        statementsList.addToEnd(example10);

        Statement example11 = new CompoundStatement(new VariableDeclaration(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(">",
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement("v",
                                                        new ArithmeticExpression('-',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        statementsList.addToEnd(example11);

        Statement example12 =
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "v"),
                        new CompoundStatement(
                                new VariableDeclaration(new RefType(new IntType()), "a"),
                                new CompoundStatement(
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("v")),
                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));

        statementsList.addToEnd(example12);

        Statement example13 =
                new CompoundStatement(
                        new VariableDeclaration(new IntType(), "a"),
                        new CompoundStatement(
                                new VariableDeclaration(new RefType(new IntType()), "b"),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("b", new ValueExpression(new IntValue(10))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("a", new ValueExpression(new IntValue(2))),
                                                                        new CompoundStatement(
                                                                                new HeapWritingStatement("b", new ValueExpression(new IntValue(20))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("a")),
                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("b"))))))),
                                                        new CompoundStatement(
                                                                new ForkStatement(
                                                                        new CompoundStatement(
                                                                                new AssignmentStatement("a", new ValueExpression(new IntValue(3))),
                                                                                new CompoundStatement(
                                                                                        new HeapWritingStatement("b", new ValueExpression(new IntValue(30))),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new VariableExpression("a")),
                                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("b"))))))),
                                                                new PrintStatement(new VariableExpression("a"))))))));

        statementsList.addToEnd(example13);

        // simple BAD example
        Statement example14 =  new CompoundStatement(
                new CompoundStatement(new VariableDeclaration(new IntType(), "v"),
                        new AssignmentStatement("v", new ValueExpression(new BoolValue(true)))),
                new PrintStatement(new VariableExpression("v")));

        statementsList.addToEnd(example14);

        return statementsList;
    }

    private ObservableList<Statement> getStatementsListObs(IList<Statement> statementList){
        List <Statement> statementsList = new ArrayList<Statement>();
        for (int i = 0; i < statementList.size(); i++){
            statementsList.add(statementList.get(i));
        }

        return FXCollections.observableArrayList(statementsList);
    }
}
