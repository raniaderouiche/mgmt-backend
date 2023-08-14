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
public class PurchaseOrder { // Bon de Commande

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String num;
    @Column(name="po_amount")
    private Double amount;
    @Column(name="po_limit")
    private Double limit;
    private Date startDate;
    private String type;
    private String region;
    private String validationState;
    private String rejectionMotive;

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

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Delivery> deliveries;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization organization;

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", num='" + num + '\'' +
                ", amount=" + amount +
                ", limit=" + limit +
                ", startDate=" + startDate +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", validationState='" + validationState + '\'' +
                ", rejectionMotive='" + rejectionMotive + '\'' +
                '}';
    }
}
