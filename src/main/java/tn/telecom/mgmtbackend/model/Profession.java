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
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  name;

    @ManyToOne
    @JoinColumn(name="business_sector_id")
    private BusinessSector sector;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Type> types;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Market> markets;

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
