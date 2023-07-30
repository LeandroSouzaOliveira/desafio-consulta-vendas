package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReportAll(Pageable pageable) {
		Page<SaleMinDTO> dto = service.getReportAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report", params = {"minDate", "maxDate", "name"})
	public ResponseEntity<Page<SaleMinDTO>> getReportWithParams(@RequestParam String minDate, @RequestParam String maxDate, @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
		Page<SaleMinDTO> dto = service.getReportWithParams(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerMinDTO>> getSummaryAll() {
		List<SellerMinDTO> dto = service.getSummaryAll();
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary", params = {"minDate", "maxDate"})
	public ResponseEntity<List<SellerMinDTO>> getSummaryWithParams(@RequestParam String minDate, @RequestParam String maxDate) {
		List<SellerMinDTO> dto = service.getSummaryWithParams(minDate, maxDate);
		return ResponseEntity.ok(dto);
	}
}
