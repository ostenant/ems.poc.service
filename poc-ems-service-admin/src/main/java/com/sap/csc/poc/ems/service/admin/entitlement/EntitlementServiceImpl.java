package com.sap.csc.poc.ems.service.admin.entitlement;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.infrastructure.app.constant.PageConstant;
import com.sap.csc.poc.ems.model.dto.entitlement.EntitlementInfo;
import com.sap.csc.poc.ems.model.dto.entitlement.SoftwareLicenseEntitlementDetail;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader_;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;
import com.sap.csc.poc.ems.persistence.repository.entitlement.EntitlementHeaderRepository;
import com.sap.csc.poc.ems.persistence.repository.entitlement.SoftwareLicenseEntitlementHeaderRepository;
import com.sap.csc.poc.ems.service.contract.entitlement.EntitlementService;

@RestController
public class EntitlementServiceImpl extends HttpApiService implements EntitlementService {

	@Autowired
	private EntitlementHeaderRepository entitlementHeaderRepository;

	@Autowired
	private SoftwareLicenseEntitlementHeaderRepository softwareLicenseEntitlementHeaderRepository;

	@Override
	@RequestMapping(value = "entitlements/search", method = RequestMethod.GET)
	public ResponseEntity<Page<EntitlementInfo>> search(
		// Keyword
		@RequestParam(value = "keyword", required = false) String keyword,
		// Page
		@RequestParam(value = "page", required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer page,
		// Size
		@RequestParam(value = "size", required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer size,
		// Sort
		@RequestParam(value = "sort", required = false, defaultValue = PageConstant.DEFAULT_SORT) String sort) {
		Page<EntitlementInfo> entitlementPage = entitlementHeaderRepository.search(keyword, new PageRequest(page, size, StringUtils.isBlank(sort)
			// Search with default sort
			? new Sort(Direction.DESC, EntitlementHeader_.updateOn.getName())
			// Search with custom sort
			: new Sort(Arrays.asList(sort.split(",")).stream().map(order -> order.split(" ")).map(order -> new Order(Direction.fromString(order[1]),
				order[0])).collect(Collectors.toList()))))
			// Convert to dto
			.map(entitlement -> new EntitlementInfo(entitlement));
		if (entitlementPage.hasContent()) {
			return new ResponseEntity<>(entitlementPage, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(entitlementPage, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@RequestMapping(value = "entitlement/{id}", method = RequestMethod.GET)
	public ResponseEntity<EntitlementInfo> findOne(@PathVariable("id") Long id) {
		EntitlementHeader entitlementHeader = entitlementHeaderRepository.findOne(id);
		if (entitlementHeader != null) {
			return new ResponseEntity<>(new EntitlementInfo(entitlementHeader), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>((EntitlementInfo) null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@RequestMapping(value = "entitlement/softwareLicense/{id}", method = RequestMethod.GET)
	public ResponseEntity<SoftwareLicenseEntitlementDetail> findOneSoftwareLicense(@PathVariable("id") Long id) {
		SoftwareLicenseEntitlementHeader entitlementHeader = softwareLicenseEntitlementHeaderRepository.findOne(id);
		if (entitlementHeader != null) {
			return new ResponseEntity<>(new SoftwareLicenseEntitlementDetail(entitlementHeader), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>((SoftwareLicenseEntitlementDetail) null, HttpStatus.NOT_FOUND);
		}
	}

}
