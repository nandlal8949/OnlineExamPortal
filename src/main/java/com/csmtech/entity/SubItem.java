package com.csmtech.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subItemId;
	private String subItemName;
	@ManyToOne
	@JoinColumn(name = "itemId", foreignKey = @ForeignKey(name = "subitem_items_foreign_key"))
	private Items items;
}
