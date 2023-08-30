package com.csmtech.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuText implements Serializable {
	private String iconImage;
	private String iconText;
	private String iconLink;
}
