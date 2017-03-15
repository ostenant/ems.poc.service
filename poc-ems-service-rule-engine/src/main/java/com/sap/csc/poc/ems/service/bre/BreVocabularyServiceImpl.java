package com.sap.csc.poc.ems.service.bre;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.model.dto.bre.vacabulary.BreVocabulary;
import com.sap.csc.poc.ems.model.dto.bre.vacabulary.BreVocabularySuffix;
import com.sap.csc.poc.ems.service.bre.component.BreRepoComponent;
import com.sap.csc.poc.ems.service.contract.bre.BreVocabularyService;

@RestController
public class BreVocabularyServiceImpl extends HttpApiService implements BreVocabularyService {

	@Autowired
	private BreVocabularySuffix breVocabularySuffix;

	@Autowired
	private BreRepoComponent breRepoComponent;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String retrieve(String name) {
		return breRepoComponent.retrieve(name, breVocabularySuffix.getSuffix());
	}

	@Override
	public Pair<HttpStatus, String> create(@RequestBody String body) {
		try {
			BreVocabulary vocabulary = objectMapper.readValue(body, BreVocabulary.class);
			return breRepoComponent.create(vocabulary);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Pair<HttpStatus, String> activate(String name) {
		return breRepoComponent.activate(name, breVocabularySuffix.getSuffix());
	}

}
