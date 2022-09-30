package tn.telecom.mgmtbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//Ordre de Traveaux
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date orderDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate;

    @Column(name="workOrder_limit")
    private Long limit;

    @Column(name="workOrder_amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "purchaseOrder_id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;


    @OneToMany(mappedBy = "workOrder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DefinitiveOrder> definitiveOrders;

    @OneToMany(mappedBy = "workOrder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attachment> attachments;

}
