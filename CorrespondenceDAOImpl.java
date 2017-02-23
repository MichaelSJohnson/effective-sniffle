package com.securian.claims.correspondenceServices.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minnesotamutual.group.common.util.ValidationUtils;
import com.securian.claims.correspondence.webservice.vo.read.CorrespondenceReadInformation;
import com.securian.claims.correspondenceServices.dao.CorrespondenceDAO;
import com.securian.claims.correspondenceServices.dao.CorrespondenceRepository;
import com.securian.claims.correspondenceServices.hibernate.CorrespondenceDTO;
import com.securian.claims.correspondenceServices.services.ServiceFacade;

@Service
public class CorrespondenceDAOImpl implements CorrespondenceDAO {
	
	@Autowired ServiceFacade serviceFacade;
	@Autowired public CorrespondenceRepository repo;
	
	//DO NOT ADD TO THIS CLASS - This should be moved to the end point / controller level. - JDB/LJC/and Brad Garry for those interested... 12/12/16
	@Override
	public List<CorrespondenceDTO> readCorrespondenceInformation(CorrespondenceReadInformation correspondenceReadInformation){
		List<CorrespondenceDTO> correspondenceSQLResult = new ArrayList<>();
		if(correspondenceReadInformation != null){
			if(!ValidationUtils.isEmpty(correspondenceReadInformation.getClaimID())){
				correspondenceSQLResult = repo.findByClaimId(Integer.parseInt(correspondenceReadInformation.getClaimID()));
			} else if(!ValidationUtils.isEmpty(correspondenceReadInformation.getCorrespondenceID())){
				CorrespondenceDTO dto = repo.findOne(Integer.parseInt(correspondenceReadInformation.getCorrespondenceID()));
				correspondenceSQLResult.add(dto);
			}
		}
		return correspondenceSQLResult;
	}
	
	

}
