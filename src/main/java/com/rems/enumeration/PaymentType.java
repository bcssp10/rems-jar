package com.rems.enumeration;

import java.util.Map;

import com.rems.util.EnumUtil;

public enum PaymentType implements EnumType<String, String>{

	CASH('N'), CHEQUE('N');

	char deleted;
	
	private static final Map<String, String> map = EnumUtil.generateLinkedHashMap(PaymentType.class);

	PaymentType(char deleted) {
		this.deleted = deleted;
	}

	public static Map<String, String> findAll() {
		return map;
	}

	@Override
	public String getKey() {
		return this.toString();
	}

	@Override
	public String getValue() {
		return this.toString().substring(0, 1) + this.toString().substring(1).toLowerCase();
	}

	@Override
	public boolean isDeleted() {
		return 'Y' == this.deleted;
	}
	

}
