package com.czertainly.cp.soft.dao.converter;

import com.czertainly.api.model.common.enums.cryptography.KeyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class KeyTypeConverter implements AttributeConverter<KeyType, String> {

    @Override
    public String convertToDatabaseColumn(KeyType keyType) {
        if (keyType == null) {
            return null;
        }
        return keyType.getCode();
    }

    @Override
    public KeyType convertToEntityAttribute(String keyType) {
        if (keyType == null) {
            return null;
        }
        return KeyType.findByCode(keyType);
    }

}
