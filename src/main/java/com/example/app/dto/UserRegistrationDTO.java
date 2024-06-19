package com.example.app.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @NotEmpty(message = "ユーザー名は必須です")
    private String userName;

    @NotEmpty(message = "パスワードは必須です")
    @Size(min = 6, message = "パスワードは6文字以上必要です")
    private String password;

    @NotEmpty(message = "確認用パスワードは必須です")
    private String confirmPassword;

    
    // パスワードが一致するかを確認するメソッド
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}

