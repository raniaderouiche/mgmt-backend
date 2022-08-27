package tn.telecom.mgmtbackend.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name="business_sector_id")
    private BusinessSector sector;

    @NotNull
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    private Boolean status;

    @NotNull
    private String directorFirstName;

    @NotNull
    private String directorLastName;

    @NotNull
    private String directorPhone;

    @NotNull
    private String directorEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="document_id", referencedColumnName = "id")
    private CustomFile document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image_id", referencedColumnName = "id")
    private CustomFile image;

    @OneToOne(mappedBy = "organization")
    private User admin_org;

    public Organization(Long id, String name, String code, BusinessSector sector, String email,
                        String country, String region, String address, String phone,
                        String directorFirstName, String directorLastName, String directorPhone,
                        String directorEmail){
        this.id = id;
        this.name = name;
        this.code = code;
        this.sector = sector;
        this.email = email;
        this.country = country;
        this.region = region;
        this.address = address;
        this.phone = phone;
        this.directorFirstName = directorFirstName;
        this.directorLastName = directorLastName;
        this.directorPhone = directorPhone;
        this.directorEmail = directorEmail;
    }

    public Organization(String name, String code, BusinessSector sector, String email,
                        String country, String region, String address, String phone,
                        String directorFirstName, String directorLastName, String directorPhone,
                        String directorEmail){
        this.name = name;
        this.code = code;
        this.sector = sector;
        this.email = email;
        this.country = country;
        this.region = region;
        this.address = address;
        this.phone = phone;
        this.directorFirstName = directorFirstName;
        this.directorLastName = directorLastName;
        this.directorPhone = directorPhone;
        this.directorEmail = directorEmail;
    }

}
