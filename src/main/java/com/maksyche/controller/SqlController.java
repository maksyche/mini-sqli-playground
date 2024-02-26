package com.maksyche.controller;

import com.maksyche.dto.GeneralResponse;
import com.maksyche.dto.LoginRequest;
import com.maksyche.dto.UpdateRequest;
import com.maksyche.dto.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"SqlSourceToSinkFlow", "CallToPrintStackTrace"})
@Controller
public class SqlController {

    private final JdbcTemplate jdbcTemplate;
    private final AtomicInteger currentId = new AtomicInteger(3);

    public SqlController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ExceptionHandler
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("response", new GeneralResponse("Something went really wrong!"));
        return "response";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(loginRequest.getPassword()));
        String passHash = String.format("%032x", new BigInteger(1, md5.digest()));
        Optional<UserResponse> user = jdbcTemplate.query("SELECT * FROM users WHERE username='%s' and pass_hash = '%s';"
                .formatted(loginRequest.getUsername(), passHash), (rs, rowNum) ->
                new UserResponse(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getBoolean("is_admin"),
                        rs.getString("info"))
        ).stream().findFirst();
        if (user.isEmpty()) {
            model.addAttribute("response", new GeneralResponse("Wrong credentials!"));
            return "response";
        }
        model.addAttribute("userResponse", user.get());
        return "show_data";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam("register-username") String username, Model model) {
        jdbcTemplate.execute(("INSERT INTO users (id, username, pass_hash, is_admin, info) " +
                              "VALUES (%d, '%s', 'c21f969b5f03d33d43e04f8f136e7682', false, 'This is a new user');")
                .formatted(currentId.incrementAndGet(), username));
        model.addAttribute("response", new GeneralResponse("Executed!"));
        return "response";
    }

    @PostMapping(value = "/get")
    public String get(@RequestParam("get-username") String username, Model model) {
        Optional<UserResponse> user = jdbcTemplate.query("SELECT * FROM users WHERE username='%s';"
                .formatted(username), (rs, rowNum) ->
                new UserResponse(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getBoolean("is_admin"),
                        rs.getString("info"))
        ).stream().findFirst();
        if (user.isEmpty()) {
            model.addAttribute("response", new GeneralResponse("Not found!"));
            return "response";
        }
        model.addAttribute("userResponse", user.get());
        return "show_data";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute UpdateRequest updateRequest, Model model) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(updateRequest.getPassword()));
        String passHash = String.format("%032x", new BigInteger(1, md5.digest()));
        jdbcTemplate.execute("UPDATE users SET info = '%s' WHERE username = '%s' and pass_hash = '%s';"
                .formatted(updateRequest.getInfo(), updateRequest.getUsername(), passHash));

        model.addAttribute("response", new GeneralResponse("Executed!"));
        return "response";
    }
}
