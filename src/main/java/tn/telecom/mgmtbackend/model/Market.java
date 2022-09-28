package tn.telecom.mgmtbackend.model;

import com.sun.istack.NotNull;
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
@ToString
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String code;
    private String budget;
    @Column(name="market_type")
    private String type;
    @Column(name="market_unit")
    private String unit;
    @Column(name="market_amount")
    private Double amount;
    @Column(name="market_limit")
    private Double limit;

    @ManyToOne
    @JoinColumn(name="profession_id")
    private Profession profession;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<PurchaseOrder> purchaseOrders;
}
