package com.rems.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rems.enumeration.EnumType;

public abstract class EnumUtil {

	public static <K, V, E extends EnumType<K, V>> Map<K, V> generateLinkedHashMap(Class<E> clazz) {
		E[] enumTypes = clazz.getEnumConstants();

		Map<K, V> map = new LinkedHashMap<>(enumTypes.length);

		Arrays.stream(enumTypes).forEach(enumType -> {
			if (!enumType.isDeleted())
				map.put(enumType.getKey(), enumType.getValue());
		});

		return map;
	}

}
