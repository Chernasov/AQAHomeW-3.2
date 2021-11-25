package ru.netology;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Cards;
import ru.netology.domain.Codes;
import ru.netology.domain.DataHelper;
import ru.netology.domain.Users;

import java.sql.DriverManager;

public class SQLTest {

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
        var all = runner.query(conn, usersSQL, new BeanListHandler<>(Users.class));
            System.out.println(all);
        }
    }

    @SneakyThrows
    @Test
    void shouldDataFromCards() {
        var cardsSQL = "SELECT * FROM cards;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
        )
        {
            var all = runner.query(conn, cardsSQL, new BeanListHandler<>(Cards.class));
            System.out.println(all);
        }
    }

    @SneakyThrows
    @Test
    void shouldDataFromAuthCodes() {
        var codesSQL = "SELECT code FROM auth_codes ORDER BY created DESC;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
        )
        {
            var all = runner.query(conn, codesSQL, new ScalarHandler<>());
            System.out.println(all);
        }
    }
}
