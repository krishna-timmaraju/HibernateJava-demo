package com.learning.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DbConnector {

	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("learning");

	// DB Operations
	public static void main(String[] args) {
		
		// Create two Test Results
		CreateResult(444, "1.1.1", "Pass");     
		CreateResult(555, "1.1.1", "Fail"); 
		
		// Print all the Test Results
        List<TestResult> students = ReadAllResults();
        if (students != null) {
            for (TestResult stu : students) {
                System.out.println(stu);
            }
        }

        // Always close the entity manager factory
        ENTITY_MANAGER_FACTORY.close();
	}

		/**
		 * Create a new test result.
		 * @param tcid
		 * @param build
		 * @param result
		 */
	public static void CreateResult(int tcid, String build, String result) {
		
		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Create a new Test Result object
			TestResult stu = new TestResult();
			stu.setTcid(tcid);
			stu.setBuild(build);
			stu.setResult(result);

			// Save the Test Result object
			manager.persist(stu);

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			
			// Close the EntityManager
			manager.close();
		}
	}

	/**
	 * Read all the Students.
	 * 
	 * @return a List of Students
	 */
	public static List<TestResult> ReadAllResults() {

		List<TestResult> tcr = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get a List of Students
			tcr = manager.createQuery("SELECT s FROM TestResult s", TestResult.class).getResultList();

			// Commit the transaction
			transaction.commit();
			
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
			
		} finally {
			// Close the EntityManager
			manager.close();
		}
		return tcr;
	}
}
