package TestingDbConnection;
import Dbconnection.DatabaseConnections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
//Test passed 100% so the connection is created successfully
@Execution(ExecutionMode.CONCURRENT) // this is basically run multiple test at the same time
//it does using multithreading so it will be faster

// // So to add Parallel Execution we need to  add a junit-platform-properties
// (basically a file called that) in the Resources file
////and say this in the file
////junit.jupiter.execution.parallel.enabled = true
////     junit.jupiter.execution.parallel.config.strategy = dynamic

public class TestingtheConnection {


        @Test
        @DisplayName("Test to check if the connection is created to Teeth department")
        public void testCreateConnectiontoTeethTreatment() throws SQLException {
            Connection connection = DatabaseConnections.createconnectiontoTeethTreatment();

            assertNotNull(connection);// check if the connection is not null if it is null then
            // it will fail but if it not then there is a connection
        }
        @Test
    @DisplayName("Test to check if the connection is created to User Details -? this contain things" +
            "like the password and the id")
    public void testConnectionToUserDetails() throws SQLException {
            Connection connection = DatabaseConnections.CreatetoConnectionTouserdetails();
          assertNotNull(connection);
        }

    }


