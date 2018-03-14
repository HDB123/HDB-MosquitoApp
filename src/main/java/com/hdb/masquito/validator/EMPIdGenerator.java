package com.hdb.masquito.validator;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class EMPIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
		String prefix="HDB";
		Connection connection=session.connection();
		
		
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select count(EmpId) as Id from  ResourceEMPTable");
			if(resultSet.next()) {
				int id=resultSet.getInt(1)+101;
				String generatedId=prefix+new Integer(id).toString();
				System.out.println("Generarated Emp Id : "+generatedId);
				return generatedId;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "0000";
	}

}
