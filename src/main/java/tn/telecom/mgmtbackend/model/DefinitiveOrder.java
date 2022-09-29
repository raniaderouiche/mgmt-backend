package tn.telecom.mgmtbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DefinitiveOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name="workOrder_id")
    @JsonIgnore
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}
