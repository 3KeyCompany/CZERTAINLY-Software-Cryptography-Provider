package com.czertainly.cp.soft.attribute;

import com.czertainly.api.model.common.attribute.v2.*;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeCallback;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeCallbackMapping;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeValueTarget;
import com.czertainly.api.model.common.attribute.v2.content.AttributeContentType;
import com.czertainly.api.model.common.attribute.v2.content.IntegerAttributeContent;
import com.czertainly.api.model.common.attribute.v2.content.StringAttributeContent;
import com.czertainly.api.model.common.attribute.v2.properties.DataAttributeProperties;
import com.czertainly.api.model.common.attribute.v2.properties.MetadataAttributeProperties;
import com.czertainly.cp.soft.collection.KeyAlgorithm;
import com.czertainly.cp.soft.collection.FalconDegree;
import com.czertainly.cp.soft.collection.RSAKeySize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyAttributes {

    /////////////////////////////////////////////////
    // Cryptographic Key Attributes
    /////////////////////////////////////////////////

    public static final String ATTRIBUTE_DATA_KEY_ALIAS = "data_keyAlias";
    public static final String ATTRIBUTE_DATA_KEY_ALIAS_UUID = "61a228de-c54e-461e-b0d7-ad156a547b51";
    public static final String ATTRIBUTE_DATA_KEY_ALIAS_LABEL = "Cryptographic Key Alias";
    public static final String ATTRIBUTE_DATA_KEY_ALIAS_DESCRIPTION = "Alias for the Key that should be unique within the Token";

    public static final String ATTRIBUTE_DATA_KEY_ALGORITHM = "data_keyAlgorithm";
    public static final String ATTRIBUTE_DATA_KEY_ALGORITHM_UUID = "72159c04-d1a9-4703-8b23-469224425d5f";
    public static final String ATTRIBUTE_DATA_KEY_ALGORITHM_LABEL = "Cryptographic Key Algorithm";
    public static final String ATTRIBUTE_DATA_KEY_ALGORITHM_DESCRIPTION = "Select one of the supported cryptographic key algorithms";


    public static final String ATTRIBUTE_GROUP_KEY_SPEC = "group_keySpec";
    public static final String ATTRIBUTE_GROUP_KEY_SPEC_UUID = "dfcfb71f-a161-4aa7-8b1f-726b477b3492";
    public static final String ATTRIBUTE_GROUP_KEY_SPEC_LABEL = "Cryptographic Key Specification";


    // RSA Attributes
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE = "data_rsaKeySize";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_UUID = "aa7df6ff-1d64-4a1a-96d6-6c7aeadfbdf3";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_LABEL = "RSA Key Size";
    public static final String ATTRIBUTE_DATA_RSA_KEY_SIZE_DESCRIPTION = "Size of the RSA Key in bits";

    // Falcon Attributes
    public static final String ATTRIBUTE_DATA_FALCON_DEGREE = "data_falconDegree";
    public static final String ATTRIBUTE_DATA_FALCON_DEGREE_UUID = "d4d86b9a-b5df-4a1b-8d9d-1671cfb4b496";
    public static final String ATTRIBUTE_DATA_FALCON_DEGREE_LABEL = "Falcon Key Degree";
    public static final String ATTRIBUTE_DATA_FALCON_DEGREE_DESCRIPTION = "Degree (n) of the Falcon Key";

    /////////////////////////////////////////////////
    // Cryptographic Key METADATA
    /////////////////////////////////////////////////

    public static final String ATTRIBUTE_META_KEY_ALIAS = "meta_keyAlias";
    public static final String ATTRIBUTE_META_KEY_ALIAS_UUID = "a5575bb8-dd88-4b60-bb73-75b862da78aa";
    public static final String ATTRIBUTE_META_KEY_ALIAS_LABEL = "Key Alias";
    public static final String ATTRIBUTE_META_KEY_ALIAS_DESCRIPTION = "Alias of the Key";

    public static final String ATTRIBUTE_META_RSA_KEY_SIZE = "meta_rsaKeySize";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_UUID = "6b8c8b9d-2712-4f9e-ab60-007cf19ac1d4";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_LABEL = "RSA Key Size";
    public static final String ATTRIBUTE_META_RSA_KEY_SIZE_DESCRIPTION = "Size of the RSA Key in bits";

    public static final String ATTRIBUTE_META_FALCON_DEGREE = "meta_falconDegree";
    public static final String ATTRIBUTE_META_FALCON_DEGREE_UUID = "bd9b2826-f7fc-4bc3-b817-66bc231f1ee2";
    public static final String ATTRIBUTE_META_FALCON_DEGREE_LABEL = "Falcon Key Degree";
    public static final String ATTRIBUTE_META_FALCON_DEGREE_DESCRIPTION = "Degree (n) of the Falcon Key";

    public static BaseAttribute buildDataKeyAlias() {
        // define Data Attribute
        DataAttribute attribute = new DataAttribute();
        attribute.setUuid(ATTRIBUTE_DATA_KEY_ALIAS_UUID);
        attribute.setName(ATTRIBUTE_DATA_KEY_ALIAS);
        attribute.setDescription(ATTRIBUTE_DATA_KEY_ALIAS_DESCRIPTION);
        attribute.setType(AttributeType.DATA);
        attribute.setContentType(AttributeContentType.STRING);
        // create properties
        DataAttributeProperties attributeProperties = new DataAttributeProperties();
        attributeProperties.setLabel(ATTRIBUTE_DATA_KEY_ALIAS_LABEL);
        attributeProperties.setRequired(true);
        attributeProperties.setVisible(true);
        attributeProperties.setList(false);
        attributeProperties.setMultiSelect(false);
        attributeProperties.setReadOnly(false);
        attribute.setProperties(attributeProperties);
        // content provided by client

        return attribute;
    }

    public static BaseAttribute buildDataKeyAlgorithmSelect() {
        // define Data Attribute
        DataAttribute attribute = new DataAttribute();
        attribute.setUuid(ATTRIBUTE_DATA_KEY_ALGORITHM_UUID);
        attribute.setName(ATTRIBUTE_DATA_KEY_ALGORITHM);
        attribute.setDescription(ATTRIBUTE_DATA_KEY_ALGORITHM_DESCRIPTION);
        attribute.setType(AttributeType.DATA);
        attribute.setContentType(AttributeContentType.STRING);
        // create properties
        DataAttributeProperties attributeProperties = new DataAttributeProperties();
        attributeProperties.setLabel(ATTRIBUTE_DATA_KEY_ALGORITHM_LABEL);
        attributeProperties.setRequired(true);
        attributeProperties.setVisible(true);
        attributeProperties.setList(true);
        attributeProperties.setMultiSelect(false);
        attributeProperties.setReadOnly(false);
        attribute.setProperties(attributeProperties);
        // set content
        attribute.setContent(KeyAlgorithm.asStringAttributeContentList());

        return attribute;
    }

    public static BaseAttribute buildGroupKeyAttributesBasedOnSelectedAlgorithm() {
        // define Group Attribute
        GroupAttribute attribute = new GroupAttribute();
        attribute.setUuid(ATTRIBUTE_GROUP_KEY_SPEC_UUID);
        attribute.setName(ATTRIBUTE_GROUP_KEY_SPEC);
        attribute.setType(AttributeType.GROUP);
        attribute.setDescription(ATTRIBUTE_GROUP_KEY_SPEC_LABEL);
        // prepare mappings for callback
        Set<AttributeCallbackMapping> mappings = new HashSet<>();
        mappings.add(new AttributeCallbackMapping(ATTRIBUTE_DATA_KEY_ALGORITHM + ".reference", "algorithm", AttributeValueTarget.PATH_VARIABLE));
        // create attribute callback
        AttributeCallback attributeCallback = new AttributeCallback();
        attributeCallback.setCallbackContext("/v1/cryptographyProvider/callbacks/keyspec/{algorithm}/attributes");
        attributeCallback.setCallbackMethod("GET");
        attributeCallback.setMappings(mappings);
        // set attribute callback
        attribute.setAttributeCallback(attributeCallback);

        return attribute;
    }

    public static List<BaseAttribute> getRsaKeySpecAttributes() {
        return List.of(
                buildDataRsaKeySize()
        );
    }

    public static List<BaseAttribute> getFalconKeySpecAttributes() {
        return List.of(
                buildDataFalconDegree()
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
        attribute.setContent(RSAKeySize.asIntegerAttributeContentList());

        return attribute;
    }

    public static BaseAttribute buildDataFalconDegree() {
        // define Data Attribute
        DataAttribute attribute = new DataAttribute();
        attribute.setUuid(ATTRIBUTE_DATA_FALCON_DEGREE_UUID);
        attribute.setName(ATTRIBUTE_DATA_FALCON_DEGREE);
        attribute.setDescription(ATTRIBUTE_DATA_FALCON_DEGREE_DESCRIPTION);
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
        attribute.setContent(FalconDegree.asIntegerAttributeContentList());

        return attribute;
    }


    // METADATA

    public static MetadataAttribute buildAliasMetadata(String alias) {
        // define Metadata Attribute
        MetadataAttribute metadataAttribute = new MetadataAttribute();
        metadataAttribute.setUuid(ATTRIBUTE_META_KEY_ALIAS_UUID);
        metadataAttribute.setName(ATTRIBUTE_META_KEY_ALIAS);
        metadataAttribute.setType(AttributeType.META);
        metadataAttribute.setContentType(AttributeContentType.STRING);
        metadataAttribute.setDescription(ATTRIBUTE_META_KEY_ALIAS_DESCRIPTION);
        // create properties
        MetadataAttributeProperties metadataAttributeProperties = new MetadataAttributeProperties();
        metadataAttributeProperties.setLabel(ATTRIBUTE_META_KEY_ALIAS_LABEL);
        metadataAttributeProperties.setVisible(true);
        metadataAttributeProperties.setGlobal(false);
        metadataAttribute.setProperties(metadataAttributeProperties);
        // create StringAttributeContent
        StringAttributeContent stringAttributeContent = new StringAttributeContent();
        stringAttributeContent.setReference("alias");
        stringAttributeContent.setData(alias);
        metadataAttribute.setContent(List.of(stringAttributeContent));

        return metadataAttribute;
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

    public static MetadataAttribute buildFalconDegreeMetadata(int degree) {
        // define Metadata Attribute
        MetadataAttribute metadataAttribute = new MetadataAttribute();
        metadataAttribute.setUuid(ATTRIBUTE_META_FALCON_DEGREE_UUID);
        metadataAttribute.setName(ATTRIBUTE_META_FALCON_DEGREE);
        metadataAttribute.setType(AttributeType.META);
        metadataAttribute.setContentType(AttributeContentType.STRING);
        metadataAttribute.setDescription(ATTRIBUTE_META_FALCON_DEGREE_DESCRIPTION);
        // create properties
        MetadataAttributeProperties metadataAttributeProperties = new MetadataAttributeProperties();
        metadataAttributeProperties.setLabel(ATTRIBUTE_META_FALCON_DEGREE_LABEL);
        metadataAttributeProperties.setVisible(true);
        metadataAttributeProperties.setGlobal(false);
        metadataAttribute.setProperties(metadataAttributeProperties);
        // create IntegerAttributeContent
        IntegerAttributeContent integerAttributeContent = new IntegerAttributeContent();
        integerAttributeContent.setReference("degree");
        integerAttributeContent.setData(degree);
        metadataAttribute.setContent(List.of(integerAttributeContent));

        return metadataAttribute;
    }

}