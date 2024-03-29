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
@ToString // article d'attachement
public class ItemRealised {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double quantity;

    @ManyToOne
    @JoinColumn(name="attachment_id")
    @JsonIgnore
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}
