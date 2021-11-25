package ru.netology.domain;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
            }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

//    @Value
//    public static class CardInfo {
//        private String number;
//    }
//
//    public static CardInfo getNumberFirstCard() {
//        return new CardInfo("5559 0000 0000 0001");
//    }
//
//    public static CardInfo getNumberSecondCard() {
//        return new CardInfo("5559 0000 0000 0002");
//    }

    @Value
    public static class VerificationCode {
        private String code;
    }


    @SneakyThrows
    public static VerificationCode getVerificationCodeFor() {
        var codesSQL = "SELECT code FROM auth_codes ORDER BY created DESC;";
        var runner = new QueryRunner();
        String code;
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
        )
        {
            code = runner.query(conn, codesSQL, new ScalarHandler<>());
        }
        return new VerificationCode(code);
    }

    @SneakyThrows
    public static void clearAllData() {
        var clearCodes = "DELETE FROM auth_codes;";
        var clearTransactions = "DELETE FROM card_transactions;";
        var clearCards = "DELETE FROM cards;";
        var clearUsers = "DELETE FROM users;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app_db", "user", "password"
                );
            )
        {
            runner.execute(conn, clearCodes);
            runner.execute(conn, clearTransactions);
            runner.execute(conn, clearCards);
            runner.execute(conn, clearUsers);
        }
    }
}
