package controller;

import exception.MyException;
import model.ProgramState;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository){
        this.repository = repository;
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

   List<Integer> getAllStatesAddresses(List<ProgramState> programStateList){
       List<Integer> allAddresses = new ArrayList<Integer>();
       programStateList.forEach(state -> allAddresses
                                        .addAll(this.getAllAddresses(
                                                this.getAddressesFromSymbolTable(state.getSymbolTable().getContent().values()),
                                                state.getHeap().getContent())));
       return allAddresses;
   }


   public Map<Integer, Value> garbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap){

        return heap.entrySet()
                      .stream()
                      .filter(element -> symbolTableAddresses.contains(element.getKey()))
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

   public synchronized void  garbageCollectorAllProgramStates(List<ProgramState> programStateList){
        List<Integer> allAddresses = this.getAllStatesAddresses(programStateList);

        programStateList
                .forEach(state -> state.getHeap().setContent(this.garbageCollector(allAddresses, state.getHeap().getContent())));
   }

   public void allStepsExecution(){
       this.executor = Executors.newFixedThreadPool(3);

       List<ProgramState> programStateList = removeCompletedPrograms(this.repository.getProgramStateList());

       while (programStateList.size() > 0){
           this.garbageCollectorAllProgramStates(programStateList);

           this.oneStepForAllPrograms(programStateList);

           programStateList = this.removeCompletedPrograms(this.repository.getProgramStateList());
       }

       this.executor.shutdownNow();

       this.repository.setProgramList(programStateList);
   }

    public void oneStepForAllPrograms(List<ProgramState> programStateList){
        programStateList.forEach(program -> this.repository.logProgramStateExecution(program));

        List<Callable<ProgramState>> callableList = programStateList.stream()
                                                        .map((ProgramState p) -> (Callable<ProgramState>)
                                                                p::oneStepExecution) // <=> return p.oneStepExecution
                                                        .collect(Collectors.toList());
        List<ProgramState> newProgramStateList;
        try {
            newProgramStateList = this.executor.invokeAll(callableList)
                                               .stream()
                                               .map(future -> {
                                               try {
                                                     return future.get();
                                               } catch (InterruptedException | ExecutionException e) {
                                                     e.printStackTrace();
                                               }
                                               return null; })
                                               .filter(Objects::nonNull)
                                               .collect(Collectors.toList());
        }
        catch (InterruptedException e){
            throw  new MyException(e.getMessage());
        }

        programStateList.addAll(newProgramStateList);

        programStateList.forEach(program -> this.repository.logProgramStateExecution(program));

        this.repository.setProgramList(programStateList);
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramStateList){
        return inProgramStateList.stream()
                                 .filter(ProgramState::isNotCompleted)
                                 .collect(Collectors.toList());
    }
}
