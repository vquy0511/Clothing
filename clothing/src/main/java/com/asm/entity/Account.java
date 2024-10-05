package com.asm.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Accounts")
public class Account {
    
    @Id
    @NotBlank(message = "Không được để trống username")
    String username;
    
    @NotBlank(message = "Không được để trống mật khẩu")
    @Length(min = 3, message = "Mật khẩu phải chứa ít nhất 3 ký tự")
    String password;
    
    @NotBlank(message = "Không được để trống đầy đủ họ tên")
    String fullname;
    
    @NotBlank(message = "Không được để trống email")
    @Email(message = "Email không hợp lệ")
    String email;
    String photo;
    String addres;
    
    @NotBlank(message = "Không được để trống số điện thoại")
    String mobile;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy="account",cascade = CascadeType.ALL)
    List<Authority> authorities;
    @JsonIgnore
    @OneToMany(mappedBy="account")
    List<Order> orders;
}