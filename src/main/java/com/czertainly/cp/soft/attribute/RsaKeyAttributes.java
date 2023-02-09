package com.czertainly.cp.soft.attribute;

import com.czertainly.api.model.common.attribute.v2.AttributeType;
import com.czertainly.api.model.common.attribute.v2.BaseAttribute;
import com.czertainly.api.model.common.attribute.v2.DataAttribute;
import com.czertainly.api.model.common.attribute.v2.MetadataAttribute;
import com.czertainly.api.model.common.attribute.v2.content.AttributeContentType;
import com.czertainly.api.model.common.attribute.v2.content.IntegerAttributeContent;
import com.czertainly.api.model.common.attribute.v2.properties.DataAttributeProperties;
import com.czertainly.api.model.common.attribute.v2.properties.MetadataAttributeProperties;
import com.czertainly.cp.soft.collection.RsaKeySize;

import java.util.List;

public class RsaKeyAttributes {

    // RSA Attributes
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE = "data_rsaKeySize";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_UUID = "aa7df6ff-1d64-4a1a-96d6-6c7aeadfbdf3";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_LABEL = "RSA Key Size";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_DESCRIPTION = "Size of the RSA Key in bits";

    public static final String ATTRIBUTE_META_RSA_KEY_SIZE = "meta_rsaKeySize";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_UUID = "6b8c8b9d-2712-4f9e-ab60-007cf19ac1d4";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_LABEL = "RSA Key Size";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_DESCRIPTION = "Size of the RSA Key in bits";

    /////////////////////////////////////////////////
    // RSA signature Attributes
    /////////////////////////////////////////////////

    public static final String ATTRIBUTE_DATA_RSA_SIG_SCHEME = "data_rsaSigScheme";

    public static final String ATTRIBUTE_DATA_SIG_DIGEST = "data_sigDigest";


    public static List<BaseAttribute> getRsaKeySpecAttributes() {
        return List.of(
                buildDataRsaKeySize()
        );
    }

    public static BaseAttribute buildDataRsaKeySize() {
        // define Data Attribute
        DataAttribute attribute = new DataAttribute();
        attribute.setUuid(ATTRIBUTE_DATA_RSA_KEY_SIZE_UUID);
        attribute.setName(ATTRIBUTE_DATA_RSA_KEY_SIZE);
        attribute.setDescription(ATTRIBUTE_DATA_RSA_KEY_SIZE_DESCRIPTION);
        attribute.setType(AttributeType.DATA);
        attribute.setContentType(AttributeContentType.INTEGER);
        // create properties
        DataAttributeProperties attributeProperties = new DataAttributeProperties();
        attributeProperties.setLabel(ATTRIBUTE_DATA_RSA_KEY_SIZE_LABEL);
        attributeProperties.setRequired(true);
        attributeProperties.setVisible(true);
        attributeProperties.setList(true);
        attributeProperties.setMultiSelect(false);
        attributeProperties.setReadOnly(false);
        attribute.setProperties(attributeProperties);
        // set content
        attribute.setContent(RsaKeySize.asIntegerAttributeContentList());

        return attribute;
    }

    public static MetadataAttribute buildRsaKeySizeMetadata(int keySize) {
        // define Metadata Attribute
        MetadataAttribute metadataAttribute = new MetadataAttribute();
        metadataAttribute.setUuid(ATTRIBUTE_META_RSA_KEY_SIZE_UUID);
        metadataAttribute.setName(ATTRIBUTE_META_RSA_KEY_SIZE);
        metadataAttribute.setType(AttributeType.META);
        metadataAttribute.setContentType(AttributeContentType.STRING);
        metadataAttribute.setDescription(ATTRIBUTE_META_RSA_KEY_SIZE_DESCRIPTION);
        // create properties
        MetadataAttributeProperties metadataAttributeProperties = new MetadataAttributeProperties();
        metadataAttributeProperties.setLabel(ATTRIBUTE_META_RSA_KEY_SIZE_LABEL);
        metadataAttributeProperties.setVisible(true);
        metadataAttributeProperties.setGlobal(false);
        metadataAttribute.setProperties(metadataAttributeProperties);
        // create IntegerAttributeContent
        IntegerAttributeContent integerAttributeContent = new IntegerAttributeContent();
        integerAttributeContent.setReference("size");
        integerAttributeContent.setData(keySize);
        metadataAttribute.setContent(List.of(integerAttributeContent));

        return metadataAttribute;
    }

}
