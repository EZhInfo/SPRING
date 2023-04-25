package com.example.hw05s_total.enumm;

public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER"), SELLER("ROLE_SELLER");
    
    private String typeRole;
    
    Role(String typeRole) {
        this.typeRole = typeRole;
    }
    
    public String getTypeRole() {
        return typeRole;
    }
    
   
}
