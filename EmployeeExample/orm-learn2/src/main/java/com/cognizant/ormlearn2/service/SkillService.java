package com.cognizant.ormlearn2.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn2.model.Skill;
import com.cognizant.ormlearn2.repository.SkillRepository;

@Service
public class SkillService
{
	@Autowired
	SkillRepository skillRepository;
	
	@Transactional
	public Skill get(int id)
	{
		return skillRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Skill skill)
	{
		skillRepository.save(skill);
	}
}
