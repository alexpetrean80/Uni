package controller;

import exception.EmptyStackException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IStack;
import model.statement.Statement;
import model.value.IntValue;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository){
        this.repository = repository;
    }

    public ProgramState oneStepExecution(ProgramState programState){
        IStack<Statement> executionStack = programState.getExecutionStack();
        if (executionStack.isEmpty()){
            throw new EmptyStackException("The execution stack is empty");
        }
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(programState);
    }

   public List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues){
        return symbolTableValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {return ((RefValue)value).getAddress();})
                .collect(Collectors.toList());
   }

   public List<Integer> getAllAddresses(List<Integer> symbolTableAddresses, Map<Integer, Value> heap){
        var entrySet = heap.entrySet();
        boolean foundAllAddresses = false;
        List<Integer> allAddresses = new ArrayList<Integer>(symbolTableAddresses);

        var addrAndRefValues = entrySet.stream()
                                        .filter(entry -> allAddresses.contains(entry.getKey()))
                                        .filter(pair -> pair.getValue() instanceof RefValue)
                                        .map(pair -> {RefValue value = (RefValue)pair.getValue();
                                                        return Map.entry(value.getAddress(),value);})
                                        .filter(pair -> !(allAddresses.contains(pair.getKey())))
                                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

       if (!addrAndRefValues.isEmpty()){
           allAddresses.addAll(addrAndRefValues.keySet());
       }
       else {
           foundAllAddresses = true;
       }

       while (!foundAllAddresses){
           foundAllAddresses = true;
           addrAndRefValues = addrAndRefValues.entrySet()
                                              .stream()
                                              .filter(entry -> allAddresses.contains(entry.getKey()))
                                              .map(pair -> {RefValue value = (RefValue)pair.getValue();
                                                                return Map.entry(value.getAddress(),value);})
                                              .filter(pair ->!(allAddresses.contains(pair.getKey())))
                                              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (!(addrAndRefValues.isEmpty())){
                allAddresses.addAll(addrAndRefValues.keySet());
                foundAllAddresses = false;
            }
            else{
                foundAllAddresses = true;
            }
       }
        return allAddresses;
   }


   public Map<Integer, Value> garbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap){

        return heap.entrySet()
                      .stream()
                      .filter(element -> symbolTableAddresses.contains(element.getKey()))
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

    public void allStepsExecution(){
        ProgramState programState = this.repository.getCurrentProgramState();
        this.repository.logProgramStateExecution();
        while(!programState.getExecutionStack().isEmpty()){
            this.oneStepExecution(programState);
            this.repository.logProgramStateExecution();
            programState.getHeap()
                    .setContent(this.garbageCollector(
                                  this.getAllAddresses(
                                      this.getAddressesFromSymbolTable(programState.getSymbolTable().getContent().values()),
                                      programState.getHeap().getContent()),
                                  programState.getHeap().getContent()));
            this.repository.logProgramStateExecution();
        }
    }



    public void addProgramState(ProgramState programState){
        this.repository.addProgramState(programState);
    }

    public void displayCurrentState(){
        System.out.println(this.repository.getCurrentProgramState().toString());
    }
}
