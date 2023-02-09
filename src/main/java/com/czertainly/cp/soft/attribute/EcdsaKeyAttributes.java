package com.czertainly.cp.soft.attribute;

import com.czertainly.api.model.common.attribute.v2.AttributeType;
import com.czertainly.api.model.common.attribute.v2.BaseAttribute;
import com.czertainly.api.model.common.attribute.v2.DataAttribute;
import com.czertainly.api.model.common.attribute.v2.content.AttributeContentType;
import com.czertainly.api.model.common.attribute.v2.properties.DataAttributeProperties;
import com.czertainly.cp.soft.collection.EcdsaCurveName;

import java.util.List;

public class EcdsaKeyAttributes {

    public static final String ATTRIBUTE_DATA_ECDSA_CURVE = "data_ecdsaCurve";
    public static final String ATTRIBUTE_DATA_ECDSA_CURVE_UUID = "08730b36-90f3-4046-9f13-3cf827ad6cc7";
    public static final String ATTRIBUTE_DATA_ECDSA_CURVE_LABEL = "Named Curve";
    public static final String ATTRIBUTE_DATA_ECDSA_CURVE_DESCRIPTION = "Select one of the supported named curves";

    /////////////////////////////////////////////////
    // ECDSA signature Attributes
    /////////////////////////////////////////////////

    public static final String ATTRIBUTE_DATA_SIG_DIGEST = "data_sigDigest";

    public static List<BaseAttribute> getEcdsaKeySpecAttributes() {
        return List.of(
                buildDataEscdaNamedCurves()
        );
    }

    public static BaseAttribute buildDataEscdaNamedCurves() {
        // define Data Attribute
        DataAttribute attribute = new DataAttribute();
        attribute.setUuid(ATTRIBUTE_DATA_ECDSA_CURVE_UUID);
        attribute.setName(ATTRIBUTE_DATA_ECDSA_CURVE);
        attribute.setDescription(ATTRIBUTE_DATA_ECDSA_CURVE_DESCRIPTION);
        attribute.setType(AttributeType.DATA);
        attribute.setContentType(AttributeContentType.STRING);
        // create properties
        DataAttributeProperties attributeProperties = new DataAttributeProperties();
        attributeProperties.setLabel(ATTRIBUTE_DATA_ECDSA_CURVE_LABEL);
        attributeProperties.setRequired(true);
        attributeProperties.setVisible(true);
        attributeProperties.setList(true);
        attributeProperties.setMultiSelect(false);
        attributeProperties.setReadOnly(false);
        attribute.setProperties(attributeProperties);
        // set content
        attribute.setContent(EcdsaCurveName.asStringAttributeContentList());

        return attribute;
    }
}
