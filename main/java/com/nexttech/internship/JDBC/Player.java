package com.nexttech.internship.JDBC;

import java.util.Date;

public class Player {
        // Player: player_id-integer, player_name-varchar(20), position, birth_date-date, nationalityvarchar(20), salary-decimal(10,2), expires_date-date,team_id-integer;
        int id;
        int teamId;
        String name;
        String position;
        Date date;
        String nationality;
        double salary;
        Date expireDate;

    }
