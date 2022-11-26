package tn.telecom.mgmtbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessSector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  name;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Profession> professions;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Organization> organizations;

    @Override
    public String toString() {
        return "BusinessSector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
