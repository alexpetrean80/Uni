package repo;

import domain.baseEntities.Droid;
import domain.baseEntities.StorableEntity;
import domain.validators.IValidator;
import exceptions.ValidatorException;

import java.sql.*;
import java.util.*;

public class DatabaseRepository<ID, T extends StorableEntity<ID, T>> extends InMemoryRepository<ID, T> {
    private final String url;
    private final String user;
    private final String password;
    private final Class<T> constructor;

    public DatabaseRepository(IValidator<T> validator, String url, String user, String password, Class<T> constructor) {
        super(validator);
        this.url = url;
        this.user = user;
        this.password = password;
        this.constructor = constructor;
    }

    @Override
    public Optional<T> findOne(ID id) {
        String className = constructor.getName();
        String sqlCommand = "select * from \"DroidDealership\".\"className\" where id = " + id.toString();
        try(
                var connection = DriverManager.getConnection(url, user, password);
                var preparedStatement = connection.prepareStatement(sqlCommand);
                var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T entity = constructor.getDeclaredConstructor().newInstance();

                return (Optional<T>) Optional.of(entity.getFromResultSet(resultSet));
            }
        }
        catch(Exception e){
            throw new RuntimeException("Error loading data from database");
        }
        return Optional.empty();
    }

    @Override
    public Iterable<T> findAll(){
        String className = constructor.getName();
        String sqlCommand = "select * from \"DroidDealership\".\"className\"";
        try(
                var connection = DriverManager.getConnection(url, user, password);
                var preparedStatement = connection.prepareStatement(sqlCommand);
                var resultSet = preparedStatement.executeQuery()) {
            Set<T> entities = new HashSet<>();
            while (resultSet.next()) {
                T entity = constructor.getDeclaredConstructor().newInstance().getFromResultSet(resultSet);

                 entities.add(entity);
            }
            return (Iterable<T>) entities;
        }
        catch(Exception e){
            throw new RuntimeException("Error loading data from database");
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        //validator.validate(entity);
        String className = constructor.getName();

        T newEntity = this.findOne(entity.getId()).get();

        String sqlCommand = entity.getSqlInsertCommand();
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sqlCommand)) {

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error writing to database");
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) {
        T entity = this.findOne(id).get();
        String sqlCommand = entity.getSqlDeleteCommand();
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sqlCommand)) {

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error writing to database");
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        String sqlCommand = entity.getSqlUpdateCommand();
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sqlCommand)) {

            entity = this.findOne(entity.getId()).get();

            var newPreparedStatement = entity.getPreparedStatement(preparedStatement);

            newPreparedStatement.executeUpdate();

            return (Optional<T>) this.findOne(entity.getId());
        }
        catch(SQLException throwables){
            throw new RuntimeException("Error updating database");
        }
    }


}
