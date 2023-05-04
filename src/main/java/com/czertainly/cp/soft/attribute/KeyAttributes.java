package com.czertainly.cp.soft.attribute;

import com.czertainly.api.model.common.attribute.v2.*;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeCallback;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeCallbackMapping;
import com.czertainly.api.model.common.attribute.v2.callback.AttributeValueTarget;
import com.czertainly.api.model.common.attribute.v2.content.AttributeContentType;
import com.czertainly.api.model.common.attribute.v2.content.StringAttributeContent;
import com.czertainly.api.model.common.attribute.v2.properties.DataAttributeProperties;
import com.czertainly.api.model.common.attribute.v2.properties.MetadataAttributeProperties;
import com.czertainly.api.model.common.enums.cryptography.KeyAlgorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /////////////////////////////////////////////////
    // Cryptographic Key METADATA
    /////////////////////////////////////////////////

    public static final String ATTRIBUTE_META_KEY_ALIAS = "meta_keyAlias";
    public static final String ATTRIBUTE_META_KEY_ALIAS_UUID = "a5575bb8-dd88-4b60-bb73-75b862da78aa";
    public static final String ATTRIBUTE_META_KEY_ALIAS_LABEL = "Key Alias";
    public static final String ATTRIBUTE_META_KEY_ALIAS_DESCRIPTION = "Alias of the Key";

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
        attribute.setContent(
                Stream.of(KeyAlgorithm.values())
                        .map(item -> new StringAttributeContent(item.getLabel(), item.getCode()))
                        .collect(Collectors.toList())
        );

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

}
