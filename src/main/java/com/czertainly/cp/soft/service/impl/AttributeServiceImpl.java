package com.czertainly.cp.soft.service.impl;

import com.czertainly.api.exception.NotFoundException;
import com.czertainly.api.interfaces.connector.AttributesController;
import com.czertainly.api.model.client.attribute.RequestAttributeDto;
import com.czertainly.api.model.common.attribute.v2.BaseAttribute;
import com.czertainly.core.util.AttributeDefinitionUtils;
import com.czertainly.cp.soft.attribute.KeyAttributes;
import com.czertainly.cp.soft.attribute.TokenInstanceActivationAttributes;
import com.czertainly.cp.soft.attribute.TokenInstanceAttributes;
import com.czertainly.cp.soft.service.AttributeService;
import com.czertainly.cp.soft.service.TokenInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttributeServiceImpl implements AttributeService {
    private static final Logger logger = LoggerFactory.getLogger(AttributesController.class);

    private TokenInstanceService tokenInstanceService;

    @Autowired
    public void setTokenInstanceService(TokenInstanceService tokenInstanceService) {
        this.tokenInstanceService = tokenInstanceService;
    }

    @Override
    public List<BaseAttribute> getAttributes(String kind) {
        logger.debug("Getting the attributes for {}", kind);

        // when we do not have Tokens, return attributes to create new
        if (tokenInstanceService.listTokenInstances() == null) {
            return TokenInstanceAttributes.getNewTokenAttributes();
        } else {
            List<BaseAttribute> attrs = new ArrayList<>();

            // first attribute is to select from existing tokens, or create a new one
            attrs.add(TokenInstanceAttributes.buildInitialInfo());
            // create options to add new Token
            attrs.add(TokenInstanceAttributes.buildOptions());
            // load additional Attributes for the selected Token
            attrs.add(TokenInstanceAttributes.buildGroupBasedOnSelect());

            return attrs;
        }
    }

    @Override
    public boolean validateAttributes(String kind, List<RequestAttributeDto> attributes) {
        if (attributes == null) {
            return false;
        }

        AttributeDefinitionUtils.validateAttributes(getAttributes(kind), attributes);
        return true;
    }

    @Override
    public List<BaseAttribute> getTokenInstanceActivationAttributes(String uuid) {
        logger.debug("Getting Token instance activation attributes for {}", uuid);

        List<BaseAttribute> attrs = new ArrayList<>();

        attrs.add(TokenInstanceActivationAttributes.buildDataTokenActivationCode());

        return attrs;
    }

    @Override
    public boolean validateTokenInstanceActivationAttributes(String uuid, List<RequestAttributeDto> attributes) {
        if (attributes == null) {
            return false;
        }

        AttributeDefinitionUtils.validateAttributes(getTokenInstanceActivationAttributes(uuid), attributes);
        return true;
    }

    @Override
    public List<BaseAttribute> getCreateKeyAttributes(String uuid) throws NotFoundException {
        tokenInstanceService.getTokenInstance(UUID.fromString(uuid));

        List<BaseAttribute> attrs = new ArrayList<>();

        attrs.add(KeyAttributes.buildDataKeyAlias());
        attrs.add(KeyAttributes.buildDataKeyAlgorithmSelect());
        attrs.add(KeyAttributes.buildGroupKeyAttributesBasedOnSelectedAlgorithm());

        return attrs;
    }

    @Override
    public boolean validateCreateKeyAttributes(String uuid, List<RequestAttributeDto> attributes) throws NotFoundException {
        if (attributes == null) {
            return false;
        }

        tokenInstanceService.getTokenInstance(UUID.fromString(uuid));

        AttributeDefinitionUtils.validateAttributes(getCreateKeyAttributes(uuid), attributes);
        return true;
    }

}
