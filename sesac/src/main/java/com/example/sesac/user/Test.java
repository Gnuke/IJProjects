package com.example.sesac.user;

public class Test { // role이 어떻게 찍히는 지 확인함
    public static void main(String[] args) {
        for(Role role : Role.values()) {
            System.out.println(role.ADMIN.getValue() + " : " + role.name() + " : " + role.ordinal() + " : " + role.toString()  );
            //ROLE_ADMIN : ADMIN : 0 : ADMIN
            //ROLE_ADMIN : USER : 1 : USER
        }
    }
}
