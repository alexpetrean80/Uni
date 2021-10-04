package repository;

import domain.adapters.db.DbAdapter;
import domain.baseEntities.BaseEntity;
import domain.validators.Validator;
import exceptions.DatabaseException;
import exceptions.ValidatorException;
import utils.DbConfigs;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class DbRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private final String url;
    private final String user;
    private final String password;
    private final Class<T> constructor;
    private final DbAdapter<ID, T> adapter;
    private final Validator<T> validator;

    public DbRepository(DbConfigs conf, Class<T> constructor, DbAdapter<ID, T> adapter, Validator<T> validator) {
        this.url = conf.getUrl();
        this.user = conf.getUserName();
        this.password = conf.getPasswd();
        this.constructor = constructor;
        this.adapter = adapter;
        this.validator = validator;
    }

    @Override
    public Optional<T> findOne(ID id) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try  {
            var connection = DriverManager.getConnection(url, user, password);

            var sparse = this.constructor.getDeclaredConstructor().newInstance();
            sparse.setId(id);
            this.adapter.setEntity(sparse);

            var preparedStatement = this.adapter.getSqlFindOneCommand(connection);
            var resultSet = preparedStatement.executeQuery();

//            while(resultSet.next()){
            resultSet.next();
                var entity = this.adapter.getFromResultSet(resultSet);
                this.adapter.setEntity(entity);

//            }
//            resultSet.next();
//            T entity = constructor.getDeclaredConstructor().newInstance();
//            System.out.println(entity);
//            this.adapter.setEntity(entity);
//
            connection.close();
            return Optional.of(entity);
        } catch (Exception e) {
            throw new DatabaseException("Error loading data from database");
        }
    }

    @Override
    public Iterable<T> findAll() throws ClassNotFoundException {

            Class.forName("org.postgresql.Driver");

        try {
            var connection = DriverManager.getConnection(url, user, password);
            var entities = new ArrayList<T>();
            var preparedStatement = this.adapter.getSqlFindManyCommand(connection);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var entity = adapter.getFromResultSet(resultSet);
                entities.add(entity);
            }
            connection.close();
            return entities;
        } catch (Exception e) {
            throw new RuntimeException("Error loading data from database");
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        validator.validate(entity);
        //var newEntity = this.findOne(entity.getId()).get();
        adapter.setEntity(entity);
        try {
            var connection = DriverManager.getConnection(url, user, password);
            var ps = adapter.getSqlInsertCommand(connection);
            ps.executeUpdate();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException("Error writing to database");
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        var entity = this.findOne(id).get();
        adapter.setEntity(entity);
        try {
            var connection = DriverManager.getConnection(url, user, password);
            var ps = adapter.getSqlDeleteCommand(connection);
            ps.executeUpdate();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException("Error writing to database");
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        adapter.setEntity(entity);
        try {
            var connection = DriverManager.getConnection(url, user, password);
            var preparedStatement = adapter.getSqlUpdateCommand(connection);
            preparedStatement.executeUpdate();
            connection.close();
            return this.findOne(entity.getId());
        } catch (SQLException | ClassNotFoundException err) {
            throw new RuntimeException("Error updating database");
        }
    }
}


