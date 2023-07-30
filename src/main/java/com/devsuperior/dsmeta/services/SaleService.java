package com.devsuperior.dsmeta.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.projections.SellerMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReportAll(Pageable pageable) {
		LocalDate startDate = LocalDate.now().minusYears(1);
		Page<Sale> result = repository.getReportAll(startDate, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	public Page<SaleMinDTO> getReportWithParams(String minDate, String maxDate, String name,Pageable pageable) {
		Page<Sale> result = repository.getReportWithParams(LocalDate.parse(minDate), LocalDate.parse(maxDate), name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	public List<SellerMinDTO> getSummaryAll() {
		LocalDate startDate = LocalDate.now().minusYears(1);
		List<SellerMinProjection> projections = repository.getSummaryAll(startDate);
		return projections.stream().map(projection -> new SellerMinDTO(projection.getName(), projection.getSales())).collect(Collectors.toList());
	}

	public List<SellerMinDTO> getSummaryWithParams(String minDate, String maxDate) {
		List<SellerMinProjection> projections = repository.getSummaryWithParams(LocalDate.parse(minDate), LocalDate.parse(maxDate));
		return projections.stream().map(projection -> new SellerMinDTO(projection.getName(), projection.getSales())).collect(Collectors.toList());
	}
}
