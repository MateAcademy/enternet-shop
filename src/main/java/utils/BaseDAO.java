//package utils;
//
////import com.trackensure.AppConstants;
////import com.trackensure.TESystemException;
////import com.trackensure.integration.util.ClassAndObjectHelper;
////import com.trackensure.integration.valueobject.IRecordValueObject;
////import com.trackensure.integration.valueobject.RecordValueObjectField;
////import org.json.JSONException;
////import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.*;
//
//public abstract class BaseDAO {
//
//	public enum CrudAction {
//		CREATE,
//		READ,
//		UPDATE,
//		DELETE
//	}
//
//	/**
//	 * A convenience method for DAO implementations to finalize ResultSets and Statements.
//	 * @param rs
//	 * @param stmt
//	 */
////	protected void finalize(ResultSet rs, Statement stmt) {
////		if (rs != null) {
////			try {
////				rs.close();
////			} catch (SQLException ex) {
////				throw new DAOSysException(ex.getMessage());
////			}
////		}
////		if (stmt != null) {
////			try {
////				stmt.close();
////			} catch (SQLException e) {
////				throw new DAOSysException(e.getMessage());
////			}
////		}
////	}
////	/**
////	 * Populates record data from the provided result set into
////	 * a new instance of the provided value object class.
////	 * @param rs	ResultSet record.
////	 * @param valueObjectClass	Value Object Class.
////	 * @param caseTypeId	Case Type ID.
////	 * @return	Value Object instance populated with result set data.
////	 * @throws DAOAppException	thrown on value object instantiation errors and data population errors.
////	 */
////	protected IRecordValueObject populateValueObject(ResultSet rs, Class valueObjectClass //, Integer caseTypeId
////			) throws DAOAppException {
////		IRecordValueObject caseValueObject =
////			ClassAndObjectHelper.getRecordValueObjectInstance(valueObjectClass);
////		if (!caseValueObject.hasVOFields()) {
////			caseValueObject.mapFields();
////		}
////		//caseValueObject.setPluginId(caseTypeId);
////		IRecordValueObject populatedValueObject = populateValueObject(rs, caseValueObject);
////		populatedValueObject.clearVOFields(); // this method will get all meta-data to GC
////		return populatedValueObject;
////
////	}
////	/**
////	 * Populates record data from the provided result set into
////	 * the provided value object.  The data is populated by reflectively
////	 * reading the value object fields and finding corresponding fields in the result set.
////	 * @param rs	Result Set data.
////	 * @param vo	Value Object instance.
////	 * @return	Populated Value Object.
////	 * @throws DAOAppException	thrown on data population errors.
////	 */
////	private IRecordValueObject populateValueObject(ResultSet rs, IRecordValueObject vo) throws DAOAppException {
////		Set setColNames = new HashSet();
////		try {
////			ResultSetMetaData meta = rs.getMetaData();
////			int numberOfColumns = meta.getColumnCount();
////			for(int i=1;i<=numberOfColumns;i++) {
////				setColNames.add(meta.getColumnName(i).toLowerCase());
////			}
////		} catch (SQLException e) {
////			throw new DAOSysException("Cannot retrieve result set meta data ");
////		}
////		Class c = vo.getClass();
////		RecordValueObjectField voField = null;
////		Method method = null;
////		try {
////			while(vo.hasNextField()) {
////				voField = vo.nextField();
//////				if (!voField.isIdentifiable) {
//////					continue;
//////				}
////				Class[] parameterTypes = new Class[] {voField.setterType};
////				method = c.getMethod(voField.setterMethodName, parameterTypes);
////				// if this column is not selected by the query, don't try to retrieve it.
////				if (!setColNames.contains(voField.databaseFieldName.toLowerCase())) {
////					continue;
////				}
////				if (voField.databaseFieldType==Types.VARCHAR) {
////					String value = rs.getString(voField.databaseFieldName);
////					Object[] arguments = new Object[] {value};
////					if (value==null || value.length()==0) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==String.class) {
////						arguments = new Object[] {value};
////					} else if (voField.setterType==JSONObject.class) {
////						try {
////							arguments = new Object[] {new JSONObject(value)};
////						} catch (JSONException e) {
////							throw new DAOAppException("BaseDAO: bad JSONObject for field: "+voField.databaseFieldName+" for class: " + c.getClass().getName()+", exception: " + e.toString() );
////						}
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.DATE) {
////					Timestamp timestamp = rs.getTimestamp(voField.databaseFieldName);
////					java.util.Date value = null;
////					if (timestamp!=null) {
////						value = new Date(timestamp.getTime());
////					}
////					Object[] arguments = null;
////					if (value!=null) {
////						arguments = new Object[] {value};
////					} else {
////						arguments = new Object[] {null};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.INTEGER) {
////					Integer value = new Integer(rs.getInt(voField.databaseFieldName));
////					if (rs.wasNull()) {
////						// TODO find out if this is going to cause problems for any plug-ins.
////						// Otherwise leave the field value null.
////					} else {
////						Object[] arguments = new Object[] {value};
////						method.invoke(vo, arguments);
////					}
////				} else if (voField.databaseFieldType==Types.CHAR) {
////					String value = rs.getString(voField.databaseFieldName);
////					Object[] arguments = null;
////					if (value==null || value.length()==0) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==String.class) {
////						arguments = new Object[] {value};
////					} else if (voField.setterType==Character.class) {
////						arguments = new Object[] {new Character(value.charAt(0))};
////					} else if (voField.setterType==Boolean.class) {
////						Boolean bool = null;
////						if (AppConstants.TE_BOOLEAN_CHAR_YES.equals(value)) {
////							bool = new Boolean(true);
////						} else {
////							bool = new Boolean(false);
////						}
////						arguments = new Object[] {bool};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.TIMESTAMP) {
////					Timestamp timestamp = rs.getTimestamp(voField.databaseFieldName);
////					java.util.Date value = null;
////					if (timestamp!=null) {
////						value = new Date(timestamp.getTime());
////					}
////					Object[] arguments = null;
////					if (value!=null) {
////						arguments = new Object[] {value};
////					} else {
////						arguments = new Object[] {null};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.TIME) {
////					java.sql.Time value = rs.getTime(voField.databaseFieldName);
////					Object[] arguments = null;
////					if (value!=null) {
////						try {
////							arguments = new Object[]{new java.util.Date(value.getTime())};
////							method.invoke(vo, arguments);
////						} catch (Exception e) {
////							arguments = new Object[] {new Time(value.getTime())};
////							method.invoke(vo, arguments);
////						}
////					} else {
////						arguments = new Object[]{null};
////						method.invoke(vo, arguments);
////					}
////				} else if (voField.databaseFieldType==Types.NUMERIC) {
////					Object[] arguments = null;
////					if (voField.setterType==BigDecimal.class) {
////						BigDecimal value = rs.getBigDecimal(voField.databaseFieldName);
////						if(rs.wasNull() || value==null) {
////							arguments = new Object[] {null};
////						} else {
////							arguments = new Object[] {value};
////						}
////					} else if (voField.setterType==Float.class) {
////						Float value = new Float(rs.getFloat(voField.databaseFieldName));
////						if(rs.wasNull() || value==null) {
////							arguments = new Object[] {null};
////						} else {
////							arguments = new Object[] {value};
////						}
////					} else if (voField.setterType==Long.class) {
////						Long value = new Long(rs.getLong(voField.databaseFieldName));
////						if(rs.wasNull() || value==null) {
////							arguments = new Object[] {null};
////						} else if (voField.setterType==Long.class) {
////							arguments = new Object[] {value};
////						} else if (voField.setterType==Integer.class) {
////							arguments = new Object[] {new Integer(value.intValue())};
////						}
////					} else if (voField.setterType==Double.class) {
////						Double value = new Double(rs.getDouble(voField.databaseFieldName));
////						if(rs.wasNull() || value==null) {
////							arguments = new Object[] {null};
////						} else {
////							arguments = new Object[] {value};
////						}
////					} else {
////						throw new DAOAppException("BaseDAO: undefined NUMERIC type " + voField.setterType + " for class " + c.getClass().getName());
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.BIGINT) {
////					Long value = new Long(rs.getLong(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Long.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.DOUBLE) {
////					Double value = new Double(rs.getDouble(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Double.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.FLOAT) {
////					Float value = new Float(rs.getFloat(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Float.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.DECIMAL) {
////					Double value = new Double(rs.getDouble(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Double.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.SMALLINT) {
////					Short value = new Short(rs.getShort(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Short.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.TINYINT) {
////					Byte value = new Byte(rs.getByte(voField.databaseFieldName));
////					Object[] arguments = null;
////					if(rs.wasNull() || value==null) {
////						arguments = new Object[] {null};
////					} else if (voField.setterType==Byte.class) {
////						arguments = new Object[] {value};
////					}
////					method.invoke(vo, arguments);
////				} else if (voField.databaseFieldType==Types.BINARY) {
////					// http://jdbc.postgresql.org/documentation/84/binary-data.html
////					byte[] bytes = rs.getBytes(voField.databaseFieldName);
////					if (bytes!=null) {
////						Object[] arguments = new Object[] {bytes};
////						method.invoke(vo, arguments);
////					} else {
////						Object[] arguments = new Object[] {null};
////						method.invoke(vo, arguments);
////					}
////				} else if (voField.databaseFieldType==Types.BLOB) {
////					Blob blob = rs.getBlob(voField.databaseFieldName);
////					if (blob!=null) {
////						byte[] bytes = blob.getBytes(1, (int)blob.length());
////						Object[] arguments = new Object[] {bytes};
////						method.invoke(vo, arguments);
////					} else {
////						Object[] arguments = new Object[] {null};
////						method.invoke(vo, arguments);
////					}
////				} else if (voField.databaseFieldType==Types.CLOB) {
////					Clob clob = rs.getClob(voField.databaseFieldName);
////					if (clob!=null) {
////						InputStream is = clob.getAsciiStream();
////						ByteArrayOutputStream baos = new ByteArrayOutputStream(is.available());
////						byte b[] = new byte[1024];
////						int intReadBytes;
////						while((intReadBytes=is.read(b, 0,is.available() )) != -1) {
////							baos.write(b, 0, intReadBytes);
////						}
////						Object[] arguments = new Object[] {baos.toString()};
////						method.invoke(vo, arguments);
////					} else {
////						Object[] arguments = new Object[] {null};
////						method.invoke(vo, arguments);
////					}
////				} else if (voField.databaseFieldType==Types.BIT) {
////					Byte value = rs.getByte(voField.databaseFieldName);
////					Object[] arguments = null;
////					if (voField.setterType==Boolean.class) {
////						Boolean bool = false;
////						if (value == 1){
////							bool = new Boolean(true);
////						}
////						arguments = new Object[] {bool};
////					}
////					method.invoke(vo, arguments);
////				}
////				/*
////				Types.ARRAY; Types.BINARY; Types.BIT; Types.BLOB; Types.BOOLEAN; Types.CLOB; Types.DATALINK;
////				Types.STRUCT; Types.VARBINARY; Types.DISTINCT; Types.JAVA_OBJECT; Types.LONGVARBINARY;
////				Types.LONGVARCHAR; Types.OTHER; Types.REAL; Types.REF;
////				*/
////			}
////		} catch (NoSuchMethodException e) {
////			throw new DAOAppException("No such value object setter method " +
////					voField.setterMethodName + " in " + vo.getClass().getName());
////		} catch (IllegalAccessException e) {
////			throw new DAOAppException("Cannot access value object setter method " +
////					voField.setterMethodName + " in " + vo.getClass().getName());
////		} catch (InvocationTargetException e) {
////			throw new DAOAppException("Cannot invoke value object setter method " +
////					voField.setterMethodName + " in " + vo.getClass().getName());
////		} catch (SQLException e) {
////			throw new DAOSysException("Cannot retrieve database value from " +
////					voField.databaseFieldName + " field");
////		} catch (IOException e) {
////			throw new DAOSysException("Cannot retrieve database value from " +
////					voField.databaseFieldName + " field");
////		}
////		return vo;
////	}
////
////	/**
////	 * This method sets a prepared statement dynamic parameter
////	 * of the provided SQL type to the provided value.  If the provided
////	 * parameter value is null then the prepared statement parameter is
////	 * set to NULL with the provided SQL parameter type.
////	 * @param ps	Prepared Statement.
////	 * @param i	Dynamic parameter index.
////	 * @param paramStrValue	Parameter value as a String.
////	 * @param parameterType	Parameter SQL type.
////	 * @return i	modified dynamic parameter index.
////	 * @throws SQLException
////	 * @throws ParseException
////	 */
////	protected int setSQLParameter(PreparedStatement ps, int i, String paramStrValue,
////			int parameterType) throws SQLException, ParseException {
////		if (paramStrValue==null || paramStrValue.length()==0) {
////			ps.setNull(i, parameterType);
////		} else {
////
////			SimpleDateFormat TE_formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", AppConstants.TE_locale);
////
////			if (parameterType==Types.VARCHAR) {
////				if (paramStrValue==null || paramStrValue.length()==0) {
////					ps.setNull(i++, Types.VARCHAR);
////				} else {
////					ps.setString(i, paramStrValue);
////				}
////			} else if (parameterType==Types.DATE) {
////				Date date = TE_formatter.parse(paramStrValue);
////				Timestamp value = new Timestamp(date.getTime());
////				ps.setTimestamp(i, value);
////			} else if (parameterType==Types.INTEGER) {
////				Integer value = new Integer(paramStrValue);
////				ps.setInt(i, value.intValue());
////			} else if (parameterType==Types.CHAR) {
////				ps.setString(i, paramStrValue);
////			} else if (parameterType==Types.TIMESTAMP) {
////				java.sql.Date value = new java.sql.Date(
////						(TE_formatter.parse(paramStrValue)).getTime());
////				ps.setDate(i, value);
////			} else if (parameterType==Types.TIME) {
////				java.sql.Date value = new java.sql.Date(
////						(TE_formatter.parse(paramStrValue)).getTime());
////				ps.setDate(i, value);
////			} else if (parameterType==Types.NUMERIC) {
////				Long value = new Long(paramStrValue);
////				ps.setLong(i, value.longValue());
////			} else if (parameterType==Types.BIGINT) {
////				Long value = new Long(paramStrValue);
////				ps.setLong(i, value.longValue());
////			} else if (parameterType==Types.DOUBLE) {
////				Double value = new Double(paramStrValue);
////				ps.setDouble(i, value.doubleValue());
////			} else if (parameterType==Types.FLOAT) {
////				Float value = new Float(paramStrValue);
////				ps.setFloat(i, value.floatValue());
////			} else if (parameterType==Types.DECIMAL) {
////				Double value = new Double(paramStrValue);
////				ps.setDouble(i, value.doubleValue());
////			} else if (parameterType==Types.SMALLINT) {
////				Short value = new Short(paramStrValue);
////				ps.setShort(i, value.shortValue());
////			} else if (parameterType==Types.TINYINT) {
////				Byte value = new Byte(paramStrValue);
////				ps.setByte(i, value.byteValue());
////			}
////		}
////		/*
////		Types.ARRAY; Types.BINARY; Types.BIT; Types.BLOB; Types.BOOLEAN; Types.CLOB; Types.DATALINK;
////		Types.STRUCT; Types.VARBINARY; Types.DISTINCT; Types.JAVA_OBJECT; Types.LONGVARBINARY;
////		Types.LONGVARCHAR; Types.OTHER; Types.REAL; Types.REF;
////		*/
////		i++;
////		return i;
////	}
//
//	/**
//	 * This method sets a prepared statement dynamic parameter
//	 * of the provided SQL type to the provided value.  If the provided
//	 * parameter value is null then the prepared statement parameter is
//	 * set to NULL with the provided SQL parameter type.
//	 * @param ps	Prepared Statement.
//	 * @param i	Dynamic parameter index.
//	 * @param paramStrValue	Parameter value as a String.
//	 * @param parameterType	Parameter SQL type.
//	 * @return i	modified dynamic parameter index.
//	 * @throws SQLException
//	 * @throws ParseException
//	 */
//	protected int setSQLParameter(PreparedStatement ps, int i, Object paramValueObject,
//			int parameterType) throws SQLException, ParseException {
//		if (paramValueObject==null) {
//			ps.setNull(i, parameterType);
//		} else {
//			if (parameterType==Types.VARCHAR) {
//				String paramStrValue = null;
//				if (paramValueObject instanceof String) {
//					paramStrValue = (String)paramValueObject;
//				} else if (paramValueObject instanceof JSONObject) {
//					paramStrValue = ((JSONObject)paramValueObject).toString();
//				}
//
//				if (paramStrValue==null || paramStrValue.length()==0) {
//					//ps.setNull(i++, Types.VARCHAR);
//					ps.setNull(i, Types.VARCHAR);
//				} else {
//					ps.setString(i, paramStrValue);
//				}
//
//			} else if (parameterType==Types.DATE) {
//				Date date = (Date)paramValueObject;
//				//Timestamp value = new Timestamp(date.getTime());
//				//ps.setTimestamp(i, value);
//				ps.setDate(i, new java.sql.Date(date.getTime()));
//			} else if (parameterType==Types.INTEGER) {
//				Integer value = (Integer)paramValueObject;
//				ps.setInt(i, value.intValue());
//			} else if (parameterType==Types.CHAR) {
//				if (paramValueObject instanceof String) {
//					ps.setString(i, (String)paramValueObject);
//				} else if (paramValueObject instanceof Character) {
//					ps.setString(i, ((Character)paramValueObject).toString());
//				} else if (paramValueObject instanceof Boolean) {
//					Boolean bool = (Boolean)paramValueObject;
//					if (bool.booleanValue()) {
//						ps.setString(i, AppConstants.TE_BOOLEAN_CHAR_YES);
//					} else {
//						ps.setString(i, AppConstants.TE_BOOLEAN_CHAR_NO);
//					}
//				} else {
//					throw new TESystemException("Unsupported DAO type");
//				}
//			} else if (parameterType==Types.TIMESTAMP) {
//				//java.sql.Date value = new java.sql.Date(((java.util.Date)paramValueObject).getTime());
//				//ps.setDate(i, value);
//				//ps.setTimestamp(i,new java.sql.Timestamp(gendDate.getTime()));
//				java.sql.Timestamp value = new java.sql.Timestamp(((java.util.Date)paramValueObject).getTime());
//				ps.setTimestamp(i, value);
//			} else if (parameterType==Types.TIME) {
//				if (paramValueObject instanceof Time) {
//					ps.setTime(i, (Time)paramValueObject);
//				} else {
//					java.sql.Date value = new java.sql.Date(((java.util.Date) paramValueObject).getTime());
//					ps.setDate(i, value);
//				}
//			} else if (parameterType==Types.NUMERIC) {
//				if (paramValueObject instanceof Long) {
//					Long value = (Long)paramValueObject;
//					ps.setLong(i, value.longValue());
//				} else if (paramValueObject instanceof Integer) {
//					Integer value = (Integer)paramValueObject;
//					ps.setInt(i, value.intValue());
//				} else if (paramValueObject instanceof BigDecimal) {
//					BigDecimal value = (BigDecimal)paramValueObject;
//					ps.setBigDecimal(i, value);
//				} else if (paramValueObject instanceof Float) {
//					Float value = (Float)paramValueObject;
//					ps.setFloat(i, value.floatValue());
//				} else if (paramValueObject instanceof Double) {
//					Double value = (Double)paramValueObject;
//					ps.setDouble(i, value);
//				}
//			} else if (parameterType==Types.BIGINT) {
//				Long value = (Long)paramValueObject;
//				ps.setLong(i, value.longValue());
//			} else if (parameterType==Types.DOUBLE) {
//				Double value = (Double)paramValueObject;
//				ps.setDouble(i, value.doubleValue());
//			} else if (parameterType==Types.FLOAT) {
//				Float value = (Float)paramValueObject;
//				ps.setFloat(i, value.floatValue());
//			} else if (parameterType==Types.DECIMAL) {
//				Double value = (Double)paramValueObject;
//				ps.setDouble(i, value.doubleValue());
//			} else if (parameterType==Types.SMALLINT) {
//				Short value = (Short)paramValueObject;
//				ps.setShort(i, value.shortValue());
//			} else if (parameterType==Types.TINYINT) {
//				Byte value = (Byte)paramValueObject;
//				ps.setByte(i, value.byteValue());
//			} else if (parameterType==Types.BINARY) {
//				// http://jdbc.postgresql.org/documentation/84/binary-data.html
//				byte[] bytes = (byte[])paramValueObject;
//				ps.setBytes(i, bytes);
//			} else if (parameterType==Types.BLOB) {
//				//byte[] bytes = (byte[])paramValueObject;
//				//ps.setBytes(i, bytes);
//			}
//		}
//		/*
//		Types.ARRAY; Types.BINARY; Types.BIT; Types.BLOB; Types.BOOLEAN; Types.CLOB; Types.DATALINK;
//		Types.STRUCT; Types.VARBINARY; Types.DISTINCT; Types.JAVA_OBJECT; Types.LONGVARBINARY;
//		Types.LONGVARCHAR; Types.OTHER; Types.REAL; Types.REF;
//		*/
//		i++;
//		return i;
//	}
//
////	/**
////	 * This method determines if the WHERE clause should contain 'LIKE' or '=' based on
////	 * the presence of the '*' wildcard character.  Date types will not allow wildcards.
////	 * @param paramValueObject The WHERE comparison value
////	 * @param parameterType The type of comparison value.
////	 * @return Either ' LIKE(?)' or ' = ?'
////	 */
////	protected String getWildcardWhereClause(String paramValueObject, int parameterType) {
////		if(parameterType != Types.DATE
////				&& parameterType != Types.TIMESTAMP
////				&& parameterType != Types.TIME
////				&& paramValueObject != null) {
////			return " LIKE(?)"; // ILIKE is same as LIKE but in POSTGRESQL it is case insensitive.
////		} else {
////			return " = ?";
////		}
////	}
////
////	/**
////	 * This method determines if the WHERE clause should contain 'LIKE' or '=' based on
////	 * the presence of the '*' wildcard character.  Date types will not allow wildcards.
////	 * @param paramValueObject The WHERE comparison value
////	 * @param parameterType The type of comparison value.
////	 * @return Either ' LIKE(?)' or ' = ?'
////	 */
////	protected String getWildcardCaseInsensitiveWhereClause(String paramValueObject, int parameterType) {
////		if(parameterType != Types.DATE
////				&& parameterType != Types.TIMESTAMP
////				&& parameterType != Types.TIME
////				&& paramValueObject != null) {
////			return " ILIKE(?)"; // ILIKE is same as LIKE but in POSTGRESQL it is case insensitive.
////		} else {
////			return " = ?";
////		}
////	}
////
////	/**
////	 * This method sets a prepared statement dynamic parameter
////	 * of the provided SQL type to the provided value with wildcards replaced.  If the provided
////	 * parameter value is null then the prepared statement parameter is
////	 * set to NULL with the provided SQL parameter type.
////	 * Date types will not allow wildcards.
////	 * @param paramValueObject The WHERE comparison value
////	 * @param parameterType The type of comparison value.
////	 * @return Either ' LIKE(?)' or ' = ?'
////	 */
////	protected int setWildcardSQLParameter(PreparedStatement ps, int i, String paramValue,
////		int parameterType) throws SQLException, ParseException {
////		if(parameterType != Types.DATE
////				&& parameterType != Types.TIMESTAMP
////				&& parameterType != Types.TIME
////				&& paramValue != null) {
////			//String paramString = paramValue.toString().replaceAll("\\*", "\\%");
////			String paramString = paramValue.toString();
////			paramString = paramString.replaceAll("\\%", "\\\\%");
////			paramString = "%" + paramString + "%";
////			return setSQLParameter(ps, i, paramString, Types.VARCHAR);
////		} else {
////			Object paramValueObject = null;
////			if (parameterType==Types.INTEGER) {
////				paramValueObject = Integer.valueOf(paramValue);
////			} else if (parameterType==Types.NUMERIC) {
////				paramValueObject = Long.valueOf(paramValue);
////			} else if (parameterType==Types.BIGINT) {
////				paramValueObject = Long.valueOf(paramValue);
////			} else if (parameterType==Types.DOUBLE) {
////				paramValueObject = Double.valueOf(paramValue);
////			} else if (parameterType==Types.FLOAT) {
////				paramValueObject = Float.valueOf(paramValue);
////			} else if (parameterType==Types.DECIMAL) {
////				paramValueObject = Double.valueOf(paramValue);
////			} else if (parameterType==Types.SMALLINT) {
////				paramValueObject = Short.valueOf(paramValue);
////			} else if (parameterType==Types.TINYINT) {
////				paramValueObject = Byte.valueOf(paramValue);
////			} else {
////				paramValueObject = paramValue;
////			}
////
////			return setSQLParameter(ps, i, paramValueObject, parameterType);
////		}
////	}
////
////	protected String generateCommaSeparatedListOfLongs(List<Long> dataList) {
////		StringBuilder sb = new StringBuilder();
////		Iterator<Long> it = dataList.iterator();
////		while (it.hasNext()) {
////			Long dataItem = it.next();
////			sb.append(dataItem);
////			if (it.hasNext()) {
////				sb.append(",");
////			}
////		}
////		return sb.toString();
////	}
////
////    protected Long getLong(ResultSet rs, String columnLabel) throws SQLException {
////		long columnValue = rs.getLong(columnLabel);
////		return rs.wasNull() ? null : columnValue;
////	}
////
////    protected int setSQLParameter(PreparedStatement ps, int i, String paramStrValue, int parameterType, int count) throws SQLException, ParseException {
////        for (;count > 0;count--) {
////            i =  setSQLParameter(ps, i, paramStrValue, parameterType);
////        }
////        return i;
////    }
//}
