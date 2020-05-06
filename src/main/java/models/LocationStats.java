package models;

public class LocationStats {

	private String state;
	private String country;
	private int latestTotalCases;
	private int diffFromPrevDay;
	
	private int confirmedcases;
	private int deathsReported;
	
	public int getConfirmedcases() {
		return confirmedcases;
	}
	public void setConfirmedcases(int confirmedcases) {
		this.confirmedcases = confirmedcases;
	}
	
	public int getDeathsReported() {
		return deathsReported;
	}
	public void setDeathsReported(int deathsReported) {
		this.deathsReported = deathsReported;
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", confirmedcases=" + confirmedcases
				+ ", deathsReported=" + deathsReported + "]";
	}
	
	
	
	
	
	
	
	
}
