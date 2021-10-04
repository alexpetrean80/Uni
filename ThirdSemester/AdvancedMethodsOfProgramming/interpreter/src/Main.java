import View.Runner;
import controller.Controller;
import model.ProgramState;
import model.containers.Dict;
import model.containers.List;
import model.containers.Stack;
import repo.Repository;

public class Main {

    public static void main(String[] args) {
        var programState = new ProgramState(new Stack<>(), new Dict<>(), new List<>());
        var repo = new Repository(programState);
        var controller = new Controller(repo);
        var view = new Runner(repo, controller);
        view.run();
    }
}
