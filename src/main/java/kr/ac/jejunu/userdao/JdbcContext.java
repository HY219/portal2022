package kr.ac.jejunu.userdao;

import kr.ac.jejunu.userdao.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("select * from userdao2 where id = ?");
//            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return user;
    }

    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            //User user = new User();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        PreparedStatement preparedStatement = null;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();


        } finally {

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    User get(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContextForGet(statementStrategy);
    }

    void insert(User user, Object[] params, String sql) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };

        jdbcContextForInsert(user, statementStrategy);
    }

    void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        jdbcContextForUpdate(statementStrategy);
    }
}