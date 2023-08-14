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
// article de BC
public class ItemUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name="purchaseOrder_id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @Override
    public String toString() {
        return "ItemUsed{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", purchaseOrder=" + purchaseOrder +
                '}';
    }
}
