package com.discovery.category.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "seq_id")
    private int seqId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "upper_category_id")
    private String upperCategoryId;

    @Column(name = "name")
    private String categoryName;

    @Column(name = "desc")
    private String categoryDesc;

    @Column(name = "level")
    private String categoryLevel;

    @Column(name = "priorty")
    private Integer categoryOrder;

    @Column(name = "crt_id")
    private String crtId;

    @Column(name = "upd_id")
    private String updId;

    @Column(name = "upd_dt")
    private LocalDateTime updDt;

    @Column(name = "crt_dt")
    private LocalDateTime crtDt;

}
