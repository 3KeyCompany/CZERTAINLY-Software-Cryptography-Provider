package com.czertainly.cp.soft.service;

import com.czertainly.api.exception.NotFoundException;
import com.czertainly.api.model.client.attribute.RequestAttributeDto;
import com.czertainly.api.model.common.attribute.v2.BaseAttribute;

import java.util.List;

public interface AttributeService {

    List<BaseAttribute> getAttributes(String kind);

    boolean validateAttributes(String kind, List<RequestAttributeDto> attributes);

    List<BaseAttribute> getTokenInstanceActivationAttributes(String uuid);

    boolean validateTokenInstanceActivationAttributes(String uuid, List<RequestAttributeDto> attributes);

    List<BaseAttribute> getCreateKeyAttributes(String uuid) throws NotFoundException;

    boolean validateCreateKeyAttributes(String uuid, List<RequestAttributeDto> attributes) throws NotFoundException;

}
