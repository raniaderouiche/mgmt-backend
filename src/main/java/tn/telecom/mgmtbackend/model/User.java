package tn.telecom.mgmtbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // account information
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean isActive;

    // personal information
    private String firstName;
    private String lastName;
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dob;
    private String email;
    private String phone;
    private String address;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
}
