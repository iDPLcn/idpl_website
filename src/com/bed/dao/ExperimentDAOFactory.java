package com.bed.dao;

public class ExperimentDAOFactory {
	public static ExperimentDAO getExperimentDAOInstance(){
		return new ExperimentImpl();
	}
}

