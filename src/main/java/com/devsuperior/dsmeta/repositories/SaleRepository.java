package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SellerMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj FROM Sale obj WHERE obj.date >= :startDate")
    Page<Sale> getReportAll(@Param("startDate") LocalDate startDate, Pageable pageable);

    @Query("SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date >= :minDate and obj.date <= :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> getReportWithParams(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT TB_SELLER.NAME, " +
            "ROUND(SUM(TB_SALES.AMOUNT), 2) AS SALES " +
            "FROM TB_SELLER " +
            "INNER JOIN TB_SALES ON TB_SALES.SELLER_ID = TB_SELLER.ID " +
            "WHERE TB_SALES.date >= :startDate " +
            "GROUP BY TB_SELLER.ID, TB_SELLER.NAME")
    List<SellerMinProjection> getSummaryAll(@Param("startDate") LocalDate startDate);

    @Query(nativeQuery = true, value = "SELECT TB_SELLER.NAME, " +
            "ROUND(SUM(TB_SALES.AMOUNT), 2) AS SALES " +
            "FROM TB_SELLER " +
            "INNER JOIN TB_SALES ON TB_SALES.SELLER_ID = TB_SELLER.ID " +
            "WHERE TB_SALES.DATE >= :minDate and TB_SALES.DATE <= :maxDate  " +
            "GROUP BY TB_SELLER.ID, TB_SELLER.NAME")
    List<SellerMinProjection> getSummaryWithParams(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);
}
