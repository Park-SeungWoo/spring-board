package com.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER_ROLE", "일반 사용자"),
    ADMIN("ADMIN_ROLE", "관리자");


    private final String role;
    private final String title;
}
