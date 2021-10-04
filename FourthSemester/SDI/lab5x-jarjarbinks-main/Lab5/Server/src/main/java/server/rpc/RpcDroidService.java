package server.rpc;

import domain.baseEntities.Droid;
import exceptions.ExistingException;
import exceptions.NotFoundException;
import rpc.DroidRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RpcDroidService implements DroidRpc {
    private final ExecutorService execSrv;
    private final service.DroidService srv;

    public RpcDroidService(ExecutorService execSrv, service.DroidService srv) {
        this.execSrv = execSrv;
        this.srv = srv;
    }

    public CompletableFuture<String> addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.addDroid(new Droid(powerUsage, price, batteryTime, model, driver));
                return "Droid added successfully.";
            } catch (ExistingException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> deleteDroid(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.deleteDroid(id);
                return "Droid deleted successfully.";
            } catch (NotFoundException e) {
                return e.getMessage();
            }
        });
    }

    public CompletableFuture<String> getAllDroids() {
        return CompletableFuture.supplyAsync(() -> srv.getDroids().toString(), execSrv);
    }

    public CompletableFuture<String> getDroidsByFilter(int filter) {
        return CompletableFuture.supplyAsync(() -> srv.filterDroidsByMinimumPowerUsage(filter).toString(), execSrv);
    }

    public CompletableFuture<String> getDroidsById(Long id) {
        return CompletableFuture.supplyAsync(() -> srv.filterDroidsById(id.toString()).toString(), execSrv);
    }

    public CompletableFuture<String> getDroidsByModel(String model) {
        return CompletableFuture.supplyAsync(() -> srv.filterDroidsByModel(model).toString(), execSrv);
    }

    public CompletableFuture<String> updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return CompletableFuture.supplyAsync(() -> {
            var dr = new Droid(powerUsage, price, batteryTime, model, driver);
            dr.setId(id);
            srv.updateDroid(dr);
            return "Droid updated successfully.";
        });
    }
}
