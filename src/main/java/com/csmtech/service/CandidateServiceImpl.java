package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.ExamSet;
import com.csmtech.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Override
	public Candidate saveCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	@Override
	public List<Candidate> getCandidateList() {
		return candidateRepository.findAll();
	}
	@Override
	public void deleteCandidateId(Integer candId) {
		candidateRepository.deleteById(candId);
	}
	@Override
	public Candidate getCandidateInfo(Integer candId) {
		return candidateRepository.findById(candId).get();
	}
	@Override
	public Candidate getCandidateByUserNameAndPassword(String userName, String password) {
		return candidateRepository.findByCandUserNameAndCandPassword(userName,password);
	}
	@Override
	public Integer uncompletecandNotGetExamSet() {
		List<Candidate> findAll = candidateRepository.findAll();
		Integer count = 0;
		for (Candidate candidate : findAll) {
			ExamSet examSet = candidate.getExamSet();
			if(examSet != null && examSet.getExamSetQuestion().size() == 0) {
				count++;	
			}
			if(examSet == null) {
				count++;	
			}
		}
		return count;
	}


}
