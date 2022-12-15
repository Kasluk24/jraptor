package ch.lugis.jraptor;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import ch.lugis.jraptor.gtfs.model.GtfsAgency;

public class Starter {

	public static void main(String[] args) {
		// Test GtfsReader agency
		GtfsReader reader = new GtfsReader("data/gtfs_fp2022_2022-08-17_04-15");
		
		try {
			reader.readAgenciesToMemory();
		} catch (CsvValidationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (GtfsAgency agency : reader.getGtfsAgencies()) {
			System.out.println(agency.toString());
		}
		
	}
}
