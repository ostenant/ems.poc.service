package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementAttribute;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementItemAttribute;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementType;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementItem;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.EntitlementItemAttributeRepository;

@Component
public class EntitlementItemAttributeDataInitializer extends JpaDataInitializer<EntitlementItemAttribute> {

	private static final List<Class<?>> EXCLUSION_PROPERTY_ATTRIBUTES = Arrays.asList(new Class<?>[] { ManyToOne.class, OneToMany.class,
		OneToOne.class, Class.class });

	@Autowired
	private EntitlementItemAttributeRepository entitlementItemAttributeRepository;

	@Autowired
	private EntitlementTypeDataInitializer entitlementTypeDataInitializer;

	@Override
	public JpaRepository<EntitlementItemAttribute, Long> getRepository() {
		return entitlementItemAttributeRepository;
	}

	@Override
	public Collection<EntitlementItemAttribute> create() {
		ArrayList<EntitlementItemAttribute> attributes = new ArrayList<>();
		// Software License
		EntitlementType sl = entitlementTypeDataInitializer.getByName("SL");
		// Software License - Item Attributes
		Field[] itemFields = FieldUtils.getAllFields(SoftwareLicenseEntitlementItem.class);
		sl.setItemAttributes(new ArrayList<>(itemFields.length));
		for (Field itemField : itemFields) {
			if (!Modifier.isStatic(itemField.getModifiers()) &&
			// Exclusion from JPA annotations
				CollectionUtils.isEmpty(CollectionUtils.intersection(
					// Annotations
					Arrays.asList(itemField.getAnnotations()).stream().map(annotation -> annotation.annotationType()).collect(Collectors.toList()),
					// Exclusions
					EXCLUSION_PROPERTY_ATTRIBUTES)))
				sl.getItemAttributes().add(generateEntitlementAttributes(itemField, new EntitlementItemAttribute()));
		}
		for (EntitlementItemAttribute itemAttribute : sl.getItemAttributes()) {
			itemAttribute.setEntitlementType(sl);
			attributes.add(itemAttribute);
		}

		return attributes;
	}

	public EntitlementItemAttribute getByName(String name) {
		return get().stream().filter(type -> StringUtils.equalsIgnoreCase(type.getName(), name)).findFirst().get();
	}

	private <TEntitlementAttribute extends EntitlementAttribute> TEntitlementAttribute generateEntitlementAttributes(Field field,
		TEntitlementAttribute entitlementAttribute) {
		entitlementAttribute.setName(field.getName());
		entitlementAttribute.setType(field.getType().getSimpleName());
		entitlementAttribute.setLength(50);
		entitlementAttribute.setValidation("NOT_NULL");
		return entitlementAttribute;
	}
}
