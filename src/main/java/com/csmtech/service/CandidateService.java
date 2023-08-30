package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Candidate;

public interface CandidateService {

	Candidate saveCandidate(Candidate candidate);

	List<Candidate> getCandidateList();

	void deleteCandidateId(Integer candId);

	Candidate getCandidateInfo(Integer candId);

	Candidate getCandidateByUserNameAndPassword(String userName, String password);

	Integer uncompletecandNotGetExamSet();

}
