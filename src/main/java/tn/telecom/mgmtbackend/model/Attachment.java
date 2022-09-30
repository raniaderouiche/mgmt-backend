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
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date attachmentDate;

    @ManyToOne
    @JoinColumn(name="purchaseOrder_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name="workOrder_id")
    private WorkOrder workOrder;

    @OneToMany(mappedBy = "attachment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemRealised> itemsRealised;

}
