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
// article d'OT
public class DefinitiveOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double quantity;

    @ManyToOne
    @JoinColumn(name="workOrder_id")
    @JsonIgnore
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @Override
    public String toString() {
        return "DefinitiveOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", workOrder=" + workOrder +
                ", item=" + item +
                '}';
    }
}
