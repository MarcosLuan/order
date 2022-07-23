package com.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "input_amount", nullable = false)
    private Integer inputAmount;

    @Column(name = "quantity_moved", nullable = false)
    private Integer quantityMoved;

    @Column(name = "current_amount", nullable = false)
    private Integer currentAmount;

    @OneToOne
    @JoinColumn(name = "ITEM_ID", unique = true, nullable = false)
    private Item item;

}
