package client.service;

import client.TcpClient;
import rpc.DroidRpc;
import rpc.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class DroidRpcService implements DroidRpc {
    private final ExecutorService execsrv;
    private final TcpClient tcli;

    public DroidRpcService(ExecutorService execsrv, TcpClient tcli) {
        this.execsrv = execsrv;
        this.tcli = tcli;
    }

    @Override
    public CompletableFuture<String> addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.ADD_DROID, Double.toString(powerUsage)+","+Double.toString(price)+
                    ","+Integer.toString(batteryTime)+","+model+","+Boolean.toString(driver));
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> deleteDroid(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.DELETE_DROID,id.toString());
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getAllDroids() {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.GET_ALL_DROIDS,"");
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getDroidsByFilter(int filter) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.GET_DROIDS_BY_FILTER,Integer.toString(filter));
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getDroidsById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.GET_DROIDS_BY_ID,id.toString());
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getDroidsByModel(String model) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(DroidRpc.GET_DROIDS_BY_MODEL,model);
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return CompletableFuture.supplyAsync(() -> {
            var req  = new Message(DroidRpc.UPDATE_DROID,id.toString()+","+Double.toString(powerUsage)+","+
                    Double.toString(price)+","+Integer.toString(batteryTime)+","+model+","+Boolean.toString(driver));
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }
}
