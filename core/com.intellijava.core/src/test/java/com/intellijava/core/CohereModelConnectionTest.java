package com.intellijava.core;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.intellijava.core.model.CohereLanguageResponse;
import com.intellijava.core.model.CohereLanguageResponse.Generation;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.wrappers.CohereAIWrapper;

public class CohereModelConnectionTest {
	
	/**
	 * coherKey - change the coherKey 
	 */
	private final String coherKey = Config2.getInstance().getProperty("url.cohere.testkey");

	
	@Test
	public void testLanguageWrapper() {
		
		// prepare the object
		CohereAIWrapper cohereWrapper = new CohereAIWrapper(coherKey);
		
		// prepare the prompt with training data
		String targetIndustryIdea = "electric cars";
		String prompt = "This program generates startup idea and name given the industry." +
				"\n\nIndustry: Workplace" +
				"\nStartup Idea: A platform that generates slide deck contents automatically based on a given outline" +
				"\nStartup Name: Deckerize" +
				"\n--" +
				"\nIndustry: Home Decor" +
				"\nStartup Idea: An app that calculates the best position of your indoor plants for your apartment" +
				"\nStartup Name: Planteasy" +
				"\n--" +
				"\nIndustry: Healthcare" +
				"\nStartup Idea: A hearing aid for the elderly that automatically adjusts its levels and with a battery lasting a whole week" +
				"\nStartup Name: Hearspan" +
				"\n\n--" +
				"\nIndustry: Education" +
				"\nStartup Idea: An online school that lets students mix and match their own curriculum based on their interests and goals" +
				"\nStartup Name: Prime Age" +
				"\n\n--" +
				"\nIndustry: {industry}".replace("{industry}", targetIndustryIdea);
		
		// prepare the input parameters
		Map<String, Object> params = new HashMap<>();
        params.put("prompt", prompt);
        params.put("model", "xlarge");
        params.put("max_tokens", 40);
        params.put("truncate", "END");
        params.put("return_likelihoods", "NONE");
        
        
        
        // call the API
        try {
        	if (coherKey.isBlank()) return;
        	
        	CohereLanguageResponse resModel = (CohereLanguageResponse) cohereWrapper.generateText(params);
        	
        	List<Generation> responses = resModel.getGenerations();
        	
        	
        	assert responses.size() > 0;
        	
        	for (Generation data: responses) {
        		System.out.println(data.getText().toString());
        	}
        	
        	
		} catch (IOException e) {
			if (coherKey.isBlank()) {
				System.out.print("testLanguageWrapper: set the API key to run the test case.");
			} else {
				fail("testLanguageWrapper failed with exception: " + e.getMessage());
			}
		}
	}
	
}
