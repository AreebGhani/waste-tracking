package org.aacounty.wastetrackingng.deprecating;

import org.aacounty.wastetrackingng.entity.wastebatch.WasteBatch;

public class BatchRepositoryImpl {

	public WasteBatch findMostRecentBatchByUserId(String userId) {

		WasteBatch mostRecentBatch = new WasteBatch();
		try {

			// TODO: implement using jpa's criteria query instead of the @Query named query
			// used in the interface
			return mostRecentBatch;
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		return mostRecentBatch;
	}

}