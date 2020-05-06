package io.trackerapp.coronavirustracker;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import models.LocationStats;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CoronaVirusDataService {

	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/05-02-2020.csv";
	
	
	 public List<LocationStats> getAllStats() {
		return allStats;
	}
	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}
	private final OkHttpClient httpClient = new OkHttpClient();
	 
	 private List<LocationStats> allStats=new ArrayList<>();
	@PostConstruct
	@Scheduled(cron="* * * * * *")
	public void fetchVirusData() throws IOException
	{
		List<LocationStats> newStats=new ArrayList<>();
		
		Request request = new Request.Builder()
                .url(VIRUS_DATA_URL)
              //  .addHeader("custom-key", "mkyong")  // add request headers
                //.addHeader("User-Agent", "OkHttp Bot")
                .build();

        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
        //System.out.println(response.body().string());

        StringReader csvBodyReader=new StringReader(response.body().string());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records)
        {
        	try {
        		LocationStats locationStats=new LocationStats();
            	locationStats.setState(record.get("Province_State"));
            	locationStats.setCountry(record.get("Country_Region"));
            	locationStats.setConfirmedcases(Integer.parseInt(record.get("Confirmed")));
            	locationStats.setDeathsReported(Integer.parseInt(record.get("Deaths")));
            	String latestCases=(record.get(record.size()-2));
            	System.out.println(latestCases);
				/*
				 * int latestCases=Integer.parseInt(record.get(record.size()-1)); int
				 * prevDayCases=Integer.parseInt(record.get(record.size()-2));
				 * locationStats.setLatestTotalCases(latestCases);
				 * locationStats.setDiffFromPrevDay(latestCases-prevDayCases);
				 */ 
            	
              	System.out.println(locationStats);
            	newStats.add(locationStats);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        }
        this.allStats=newStats;
	}
}