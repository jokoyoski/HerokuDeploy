package com.laundryservice.laundryservice.sql;

import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class CreateUser {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Create_User")
                .returningResultSet("result", new BeanPropertyRowMapper<>(UserRegistrationRequest.class))
        ;

    }


    public String InsertUser(UserRegistrationRequest user) {

        //    SqlParameterSource in = new MapSqlParameterSource();
        //     in.addValue()
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();


        namedParameters.addValue("fir", user.getFirstName());
        namedParameters.addValue("las", user.getLastName());
        namedParameters.addValue("ema", user.getEmail());
        namedParameters.addValue("pas", user.getPassword());
        namedParameters.addValue("pho", user.getPhoneNumber());
        namedParameters.addValue("addr", user.getAddress());





        try {

            Map out = simpleJdbcCall.execute(namedParameters);

            if (out != null) {


                return "User saved Successfully";
            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());

            return e.getMessage();
        }
        return "User saved successfully";
    }
}
