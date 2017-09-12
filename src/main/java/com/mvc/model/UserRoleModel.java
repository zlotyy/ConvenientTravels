//package com.mvc.model;
//
//import org.hibernate.validator.constraints.NotEmpty;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "UserRole")
//public class UserRoleModel {
//    @Id
//    @GeneratedValue
//    @Column(name = "RoleId")
//    private long roleId;
//
//    @Column(name = "Role")
//    @NotEmpty(message = "Pole nie moze byc puste")
//    private String role;
//
//    @OneToOne
//    @JoinColumn(name = "UserId")
//    private UserModel user;
//}
