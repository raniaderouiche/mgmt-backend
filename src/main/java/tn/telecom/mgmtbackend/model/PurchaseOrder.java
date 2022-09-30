package tn.telecom.mgmtbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    @Column(name="po_amount")
    private Double amount;
    @Column(name="po_limit")
    private Double limit;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name="market_id")
    @JsonIgnore
    private Market market;


    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<ItemUsed> itemsUsed;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<WorkOrder> workOrders;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attachment> attachments;
}
