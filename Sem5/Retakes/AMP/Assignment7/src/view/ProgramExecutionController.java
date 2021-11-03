package view;

import controller.Controller;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IList;
import model.adt.IStack;
import model.dto.*;
import model.statement.Statement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


public class ProgramExecutionController {
    @FXML
    private TextField programStatesNumberTextField;

    @FXML
    private TableView<HeapTableViewEntity> heapTableView;

    @FXML
    private TableColumn<HeapTableViewEntity, String> heapAddress;

    @FXML
    private TableColumn<HeapTableViewEntity, String> heapValue;

    @FXML
    private ListView<Value> outputListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<String> programStateIdentListView;

    @FXML
    private TableView<SymbolTableViewEntity> symbolTableView;

    @FXML
    private TableColumn<SymbolTableViewEntity, String> symbolTableVariableName;

    @FXML
    private TableColumn<SymbolTableViewEntity, String> symbolTableVariableValue;

    @FXML
    private ListView<String> exeStackListView;

    @FXML
    private Button runOneStepButton;

    private ProgramState parentProgramState;


    Controller controller;
    ProgramExecutionController(Controller controller){
        this.controller = controller;
    }

    @FXML
    public void initialize(){
        // for heap table
        heapAddress.setCellValueFactory(new PropertyValueFactory<HeapTableViewEntity, String>("addressProperty"));
        heapValue.setCellValueFactory(new PropertyValueFactory<HeapTableViewEntity, String>("valueProperty"));

        symbolTableVariableName.setCellValueFactory(new PropertyValueFactory<SymbolTableViewEntity, String>("name"));
        symbolTableVariableValue.setCellValueFactory(new PropertyValueFactory<SymbolTableViewEntity, String>("value"));

        this.runOneStepButton.setOnMouseClicked(this::runOneStep);
        this.programStateIdentListView.setOnMouseClicked(this::changeProgramStateHandler);

        this.parentProgramState = null;

        try {
            this.fillWidgets();
        }
        catch (Exception exception){
           // System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Typecheck exception");
            alert.setContentText("Error: " + exception.getMessage());

            alert.showAndWait();
        }
    }

    private void fillWidgets(){
        List<ProgramState> programStates = this.controller.getProgramStateList();
        ProgramState currentProgramState;
        if (programStates.size() == 0){
            programStatesNumberTextField.setText(String.valueOf(0));
            if (this.parentProgramState == null){
                throw new MyException("There is no program state left!");
            }
            else{
                currentProgramState = this.parentProgramState;
                parentProgramState = null;
            }
        }
        else {
            currentProgramState = programStates.get(0);
            parentProgramState = programStates.get(0);
        }

            // number of program states
            programStatesNumberTextField.setText(String.valueOf(programStates.size()));

            try {
                this.programStateIdentListView.getSelectionModel().selectIndices(0);
                this.fillHeapTable(currentProgramState);
                this.fillOutputListView(currentProgramState);
                this.fillFileTableListView(currentProgramState);
                this.fillProgramStateIdentListView(programStates);
                this.fillExeStackListView(currentProgramState);
                this.fillSymbolTableView(currentProgramState);
            } catch (MyException exception){
                System.out.println(exception.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Exception");
                alert.setContentText("Error: " + exception.getMessage());

                alert.showAndWait();
            }

    }


    private void fillSymbolTableView(ProgramState programState){
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        ArrayList<SymbolTableViewEntity> symbolTableEntities = new ArrayList<>();
        symbolTable.getContent().forEach((key, value) -> {
            SymbolTableViewEntity entity = new SymbolTableViewEntity(key, value);
            symbolTableEntities.add(entity);
        });

        symbolTableView.setItems(FXCollections.observableArrayList(symbolTableEntities));
    }

    private void fillExeStackListView(ProgramState programState){
        IStack<Statement> stackCopy = programState.getExecutionStack();
        List<Statement> stackList = stackCopy.toList();
        ArrayList<String> stackAsString = new ArrayList<String>();
        stackList.forEach(statement -> {
            stackAsString.add(statement.toString());
        });
        this.exeStackListView.setItems(FXCollections.observableArrayList(stackAsString));
    }

    private void fillProgramStateIdentListView(List<ProgramState> programStates){
        ArrayList<String> programStatesIdentifierList = new ArrayList<String>();
        for (ProgramState programState: programStates){
            String programStateIdentifier = String.valueOf(programState.getId());
            programStatesIdentifierList.add(programStateIdentifier);
        }

        this.programStateIdentListView.setItems(FXCollections.observableArrayList(programStatesIdentifierList));
    }

    private void fillFileTableListView(ProgramState programState){
        IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        ArrayList<String> fileTableAsString = new ArrayList<String>();
        for (StringValue key: fileTable.getContent().keySet()){
            String fileString = key.toString() + " -> " + fileTable.lookUp(key).toString();
            fileTableAsString.add(fileString);
        }
        this.fileTableListView.setItems(FXCollections.observableArrayList(fileTableAsString));
    }

    private void fillOutputListView(ProgramState programState){
        IList<Value> output = programState.getOutput();
        ArrayList<Value> outputArrayList = new ArrayList<Value>();
        for (int i = 0; i < output.size(); i++){
            outputArrayList.add(output.get(i));
        }
        this.outputListView.setItems(FXCollections.observableArrayList(outputArrayList));
    }

    private void fillHeapTable(ProgramState programState){
        IHeap<Value> heap = programState.getHeap();
        ArrayList<HeapTableViewEntity> heapEntities = new ArrayList<HeapTableViewEntity>();
        heap.getContent().forEach((key, value) -> {
            HeapTableViewEntity entity = new HeapTableViewEntity(key, value);
            heapEntities.add(entity);
        });

        heapTableView.setItems(FXCollections.observableArrayList(heapEntities));
    }

    @FXML
    public void changeProgramStateHandler(MouseEvent event){
        try {
            int id = this.programStateIdentListView.getSelectionModel().getSelectedIndex();
            ProgramState programState = this.controller.getProgramStateList().get(id);
            this.fillSymbolTableView(programState);
            this.fillExeStackListView(programState);
        } catch (MyException exception){
            //System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Typecheck exception");
            alert.setContentText("Error: " + exception.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    public void runOneStep(MouseEvent event){
        try {
            List<ProgramState> programStates = this.controller.getProgramStateList();
            if (programStates.size() > 0 || this.parentProgramState != null){
                this.controller.evaluateOneStepForAll(programStates);
            }
        } catch (MyException exception) {
            //System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Exception");
            alert.setContentText("Error: " + exception.getMessage());

            alert.showAndWait();
        }
        try{
            this.fillWidgets();
        } catch (MyException exception){
            //System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Exception");
            alert.setContentText("Error: " + exception.getMessage());

            alert.showAndWait();
        }
    }
}
