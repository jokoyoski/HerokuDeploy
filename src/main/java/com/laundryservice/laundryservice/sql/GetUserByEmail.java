package com.laundryservice.laundryservice.sql;

import com.laundryservice.laundryservice.domain.User;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class GetUserByEmail {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Get_User_By_Email")
                .returningResultSet("result", new BeanPropertyRowMapper<>(UserRegistrationRequest.class))
        ;

    }


    public User GetUserInfo(String email) {

        //    SqlParameterSource in = new MapSqlParameterSource();
        //     in.addValue()
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();



        namedParameters.addValue("ema", email);





        try {

            Map out = simpleJdbcCall.execute(namedParameters);

            if (out!=null) {
                var result2 = ((List)out.get("result")).get(0);

                return  (User)result2;




            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());
        }

        return new User();
    }
}
