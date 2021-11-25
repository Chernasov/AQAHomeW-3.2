package ru.netology;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import ru.netology.domain.DataHelper;

import java.sql.DriverManager;

public class AuthTest {

    @SneakyThrows
    @Test
    void shouldDataFromUsers() {
        var usersSQL = "SELECT * FROM users;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
            )
        {
        var all = runner.query(conn, usersSQL, new BeanListHandler<>(DataHelper.AuthInfo.class));
            System.out.println(all);
        }
    }
}
