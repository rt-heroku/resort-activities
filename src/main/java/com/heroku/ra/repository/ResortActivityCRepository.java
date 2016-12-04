/*
 * Created on 2 Dec 2016 ( Time 21:46:15 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.heroku.ra.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.ra.entities.ResortActivityC ;



/**
 * Spring JPA Repository for ResortActivityC
 * 
 * @author Telosys Tools Generator
 *
 */
public interface ResortActivityCRepository extends PagingAndSortingRepository<ResortActivityC, Integer> {
		ResortActivityC findBySfid(String sfid);
		List<ResortActivityC> getToday();
		
}