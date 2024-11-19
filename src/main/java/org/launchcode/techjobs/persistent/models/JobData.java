package org.launchcode.techjobs.persistent.models;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class JobData {

    /**
     * Returns the results of searching the Jobs data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Job field that should be searched.
     * @param value Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     * Search jobs by column and field
     * Iterable: allows iteration of elements
     */

    public static ArrayList<Job> findByColumnAndValue(
            String column, // field
            String value, // term
            Iterable<Job> allJobs) { // collection

        // Store any matches in ArrayList<>
        ArrayList<Job> results = new ArrayList<>();

        // Search "all"
        if (value.toLowerCase().equals("all")){
            return (ArrayList<Job>) allJobs; // return "all"
        }

        // Search "all columns"... sep method findByValue()...
        if (column.equals("all")){
            results = findByValue(value, allJobs);
            return results;
        }
        for (Job job : allJobs) {

            String aValue = getFieldValue(job, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            }
        }

        return results;
    }

    public static String getFieldValue(Job job, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = job.getName();
        } else if (fieldName.equals("employer")){
            theValue = job.getEmployer().toString();
        } else {
            theValue = job.getSkills().toString();
        }

        return theValue;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return      List of all jobs with at least one field containing the value.
     */
    public static ArrayList<Job> findByValue(String value, Iterable<Job> allJobs) {


        ArrayList<Job> results = new ArrayList<>();

        for (Job job : allJobs) {

            if (job.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else if (job.getEmployer().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else if (job.getSkills().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            }

        }
        return results;
    }
}

