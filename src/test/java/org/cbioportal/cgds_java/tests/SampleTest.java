package org.cbioportal.cgds_java.tests;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cbioportal.cgds_java.api.CGDSAPI;
import org.cbioportal.cgds_java.model.Study;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {

	public static Logger logger = Logger.getLogger("SampleTest");

	@Test
	public void f() {
		logger.log(Level.INFO, "Testing getStudies()");
		List<Study> studiesList = CGDSAPI.getStudies();
		for (Study study : studiesList) {
			logger.info(study.toString());
		}
		logger.info("Number of items: " + studiesList.size() + "");
		Assert.assertTrue(!studiesList.isEmpty(), "List is empty");

		String study_id = "brca_tcga_pub";
		studiesList = CGDSAPI.getStudies(study_id);
		for (Study study : studiesList) {
			logger.info(study.toString());
		}
		logger.info("Number of items: " + studiesList.size() + "");
		Assert.assertTrue(!studiesList.isEmpty(), "List is empty");
	}

}
